package org.example;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Farm {

        Scanner scanner = new Scanner(System.in);
        CropManager cropManager = new CropManager();
        AnimalManager animalManager = new AnimalManager();
        ArrayList<Animal> animalList = animalManager.animalList;
        ArrayList<Crop> cropList = cropManager.cropList;
        File animalFile = new File("animals.txt");
        File cropFile = new File("crop.txt");

    public void menu(){

        boolean mainMenu = true;
        while (mainMenu){

            System.out.println("\nWELCOME TO FARM HÅLARPSGÅRDEN!");
            System.out.println("Please make a choice in the menu.\n");
            System.out.println("1. Crop manager, to handle the crops.");
            System.out.println("2. Animal manager, to handle the animals.");
            System.out.println("3. Save");
            System.out.println("4. Quit the menu.");
            String input = scanner.nextLine();

            switch(input) {
                case"1":
                    cropManager();
                    break;
                case"2":
                    animalManager();
                    break;
                case"3":
                    save();
                    break;
                case"4":
                    quit();
                    mainMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        }
    }
    private void cropManager() {
        cropManager.cropMenu();

    }
    private void animalManager() {
        animalManager.animalMenu(cropManager);

    }
    private void save() {
        try {
            FileWriter fw = new FileWriter(animalFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Animal animal : animalList) {
                bw.write(animal.getCSV());
                bw.newLine();
            }
            bw.close();
       // }catch (IOException e){
       // }
           // try{
            FileWriter Fw = new FileWriter(cropFile);
            BufferedWriter Bw = new BufferedWriter(Fw);
            for (Crop crop : cropList){
                Bw.write(crop.getCSV());
                Bw.newLine();
            }
            Bw.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private void quit() {
        System.out.println("Welcome back!\n");
    }





}
