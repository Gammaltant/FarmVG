package org.example;


import java.util.ArrayList;
import java.util.List;

public class Animal extends Entity {
    private String species;
    private ArrayList<String> acceptableCropTypes;
    private static int nextAnimalId = 1;

    public Animal(int id, String name, String species, ArrayList<String> acceptableCropTypes) {
        super(id, name);
        if(id > nextAnimalId){
            nextAnimalId = id + 1;
        }
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }

    public String getCSV(){return id + "," + name + "," + species + "," + getAcceptableCropTypesCSV(); }
    public String getAcceptableCropTypesCSV(){
        String name = " " ;
        for(int i = 0; i < acceptableCropTypes.size(); i++){
            name += acceptableCropTypes.get(i);
            if(i< acceptableCropTypes.size() -1){
                name += "@";
            }
        }
        return name;

    }

    @Override
    public void GetDescription() {
        super.GetDescription();
        System.out.println(getId() + "." + "Name: " + getName()  + ", Species: " + getSpecies() + ", Food: " + getAcceptableCropTypes());
    }

    public void Feed(Crop crop){
       if (acceptableCropTypes.contains(crop.getCropType())) {
           if (crop.TakeCrop(1)) {
                System.out.println(getName() + " is fed with " + crop.getCropType());
            } else {
               System.out.println("There is not enough " + crop.getCropType() + " to give " + name + " the " + species + ".");
           }
       } else {
           System.out.println(getName() + " does not eat " + crop.getCropType());
       }
    }

    public String getSpecies() {return species;}
    public void setSpecies(String species) {this.species = species;}


    public List<String> getAcceptableCropTypes() {return acceptableCropTypes;}
    public void setAcceptableCropTypes(ArrayList<String> acceptableCropTypes)
                                      {this.acceptableCropTypes = acceptableCropTypes;}


}



