package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Crop> cropList = new ArrayList<>();
    File cropFile = new File("crop.txt");



    public CropManager() {
        cropList.add(new Crop(1, "Oats", "grain", 225));
        cropList.add(new Crop(2, "Apple", "fruit", 350));
        cropList.add(new Crop(3, "Hay", "grass", 1543));
        cropList.add(new Crop(4, "Soy", "forage", 382));
        cropList.add(new Crop(5, "Carrot", "vegetables", 543));
        cropList.add(new Crop(6, "Root vegetables", "vegetables", 892));
        cropList.add(new Crop(7, "Fish", "seafood", 25));

        try {
            FileReader fr = new FileReader(cropFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                String[] variables = line.split(",");

                //try {
                int id = Integer.parseInt(variables[0]);
                String name = variables[1];
                String cropType = variables[2];
                int quantity = Integer.parseInt(variables[3]);

                try {
                    Crop crop = cropList.get(id - 1);
                    crop.setQuantity(quantity);
                } catch (Exception e) {
                    Crop crop = new Crop(id, name, cropType, quantity);
                    cropList.add(crop);
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fel vid inläsning" + e);
        }
    }



    public void cropMenu() {
        boolean subMenu = true;
        while (subMenu) {
            System.out.println("\nMake youre choice in the menu.\n");
            System.out.println("1. View all crops.");
            System.out.println("2. Add crops.");
            System.out.println("3. Remove crops.");
            System.out.println("4. Quit and go back to the main menu.");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    viewCrops();
                    break;
                case "2":
                    addCrops();
                    break;
                case "3":
                    removeCrops();
                    break;
                case "4":
                    quit();
                    subMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        }
    }

    public void viewCrops() {
        for (Crop crop : cropList) {
            crop.GetDescription();

        }
    }
    private void addCrops() {
        System.out.println("Chose a crop by id");
        viewCrops();
        try {
            String selectCropType = scanner.nextLine();
            int chosenCrop = Integer.parseInt(selectCropType);
            if (chosenCrop>= 0 && chosenCrop < cropList.size()) {
                Crop selectedCrop =cropList.get(chosenCrop -1);

                System.out.println("Enter the quantity to add.");
                String addQuantity = scanner.nextLine();
                selectedCrop.addCrop(Integer.parseInt(addQuantity));

            }else{
                System.out.println("Invalid index. Please enter a valid index");
            }
        }catch(Exception e){
               System.out.println("Please write a number.");
           }
    }

    private void removeCrops() {
        System.out.println("Chose a crop by the id.");
        viewCrops();
        String selectCropType = scanner.nextLine();
        try {
             int chosenCrop = Integer.parseInt(selectCropType);
              if(chosenCrop>=0 && chosenCrop< cropList.size()) {
                Crop selectedCrop = cropList.get(chosenCrop -1);

                System.out.println("Enter the quantity to remove.");
                String removeQuantity = scanner.nextLine();
                selectedCrop.TakeCrop(Integer.parseInt(removeQuantity));

              }else{
                System.out.println("invalid input.Please put a valid number.");
              }
        }catch(Exception e){
               System.out.println("Please write a number.");
        }
    }
    private void quit() {
    }


}




