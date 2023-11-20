package org.example;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimalManager {

    CropManager cropManager = new CropManager();
    ArrayList<String> cropList = new ArrayList<>();
    ArrayList<Animal> animalList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    File animalFile = new File("animals.txt");


    public AnimalManager() {


        ArrayList<String> pigFood = new ArrayList<>();
        ArrayList<String> horseFood = new ArrayList<>();
        ArrayList<String> catFood = new ArrayList<>();
        ArrayList<String> lamaFood = new ArrayList<>();
        ArrayList<String> chickenFood = new ArrayList<>();
        ArrayList<String> cowFood = new ArrayList<>();
        cowFood.add("grass");
        cowFood.add("grain");
        pigFood.add("forage");
        pigFood.add("vegetables");
        pigFood.add("fruit");
        horseFood.add("fruit");
        horseFood.add("grain");
        horseFood.add("grass");
        catFood.add("seafood");
        lamaFood.add("fruit");
        chickenFood.add("grain");

        animalList.add(new Animal(1, "Rosa", "cow", cowFood));
        animalList.add(new Animal(2, "Greta", "pig", pigFood));
        animalList.add(new Animal(3, "Sonette", "horse", horseFood));
        animalList.add(new Animal(4, "Tora", "cow", cowFood));
        animalList.add(new Animal(5, "Simba", "cat", catFood));
        animalList.add(new Animal(6, "Dharma", "lama", lamaFood));
        animalList.add(new Animal(7, "Egg Sheran", "chicken", chickenFood));
        animalList.add(new Animal(8, "Eggatha Christie ", "chicken", chickenFood));

        try {
            FileReader fr = new FileReader(animalFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                String[] variables = line.split(",");

                int id = Integer.parseInt(variables[0]);
                String name = variables[1];
                String species = variables[2];
                ArrayList<String> acceptableCropTypes = new ArrayList<>(Arrays.asList(variables[3].split("@")));

                try {
                    Animal animal = animalList.get(id - 1);
                } catch (Exception e) {
                    Animal animal = new Animal(id, name, species, acceptableCropTypes);
                    animalList.add(animal);
                }
                line = br.readLine();
            }
            br.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void animalMenu(CropManager cropManager){

        boolean sideMenu = true;
        while (sideMenu){
            System.out.println("\nMake youre choice in the menu\n");
            System.out.println("1. View the animals in the farm.");
            System.out.println("2. Add new animals to the farm.");
            System.out.println("3. Remove animal.");
            System.out.println("4. Feed the animals.");
            System.out.println("5. Quit and go back to the main menu.");
            String input = scanner.nextLine();

            switch(input){
                case"1":
                    ViewAnimal();
                    break;
                case"2":
                    AddAnimal();
                    break;
                case"3":
                    RemoveAnimal();
                    break;
                case"4":
                    FeedAnimal(cropManager.cropList);
                    break;
                case"5":
                    Quit();
                    sideMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        }
    }

    private void ViewAnimal() {
        for( Animal animal : animalList){
            animal.GetDescription();
        }
    }
    private void AddAnimal() {
        try {
        System.out.println("\nPut in new id.");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Name of the animal.");
        String name = scanner.nextLine();
        System.out.println("What species?.");
        String species = scanner.nextLine();

        System.out.println("Put the name of the food the animal eats.");
        String acceptableCropTypes = scanner.nextLine();
        cropList.add(acceptableCropTypes);

        Animal a = new Animal(id, name, species, cropList);
        animalList.add(a);
        System.out.println("Animal added.\n");
        }
        catch (Exception e) {
            System.out.println("Please write a number.");
        }
    }
    private void RemoveAnimal() {
        try {
            System.out.println("Wich animal would you like to remove? Write the ID.");
            ViewAnimal();
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean found = false;
            for (int i = 0; i < animalList.size(); i++) {
                if (animalList.get(i).getId() == id) {
                    animalList.remove(i);
                    found = true;
                    System.out.println("Animal " + id + " successfully removed.");
                    break;
                }
            }
            if (!found) {
                System.out.println("No animal with ID" + id + "was found.");
            }
        }catch(Exception e){
            System.out.println("Please write a number");
        }
    }
    private void FeedAnimal(ArrayList<Crop> cropList) {
        System.out.println("Chose animal from the list to feed");
        ViewAnimal();
        String id = scanner.nextLine();
        try {
            int animalId = Integer.parseInt(id);
            Animal animal = getAnimalById(animalId);

            if(animal != null) {
                System.out.println("Chose crop from the list.");
                for (Crop crop : cropList) {
                    crop.GetDescription();
                }
                String cropIdString = scanner.nextLine();
                int cropId = Integer.parseInt(cropIdString) - 1;
                Crop crop2 = cropList.get(cropId);
                animal.Feed(crop2);
            }else{
                System.out.println("Invalid animal selection.");
            }

        }catch(Exception e){
                System.out.println("Put a valid number");
        }
    }


    private Animal getAnimalById(int id) {
        for (Animal animal : animalList) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }
    private void Quit() {

    }

}
