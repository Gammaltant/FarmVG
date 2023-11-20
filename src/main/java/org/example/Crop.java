package org.example;

public class Crop extends Entity{
    private String cropType;
    private int quantity;
    private static int nextCropId;



    public Crop(int id, String name, String cropType, int quantity) {
        super(id,name);
        if(id > nextCropId){
          nextCropId = id + 1;
        }
        this.cropType = cropType;
        this.quantity = quantity;
    }

    public String getCSV(){return id + "," + name + "," + cropType + "," + quantity; }


    @Override
    public void GetDescription() {
        super.GetDescription();
        System.out.println("Id:" + getId() + "." + "Name: " + getName() +"," + " Type: " + getCropType() + "," + " Quantity: "  + getQuantity() + "kg");
    }


    public void addCrop(int amount) {
        if(amount > 0) {
            quantity += amount;
            System.out.println("Added " + amount + "kg to " + cropType + ". New quantity: " + quantity + "kg.");
        }else {
            System.out.println("Invalid quantity. Please put a positive number");
        }
    }

    public boolean TakeCrop(int amount) {
        if(amount > 0 && quantity >= amount) {
            quantity -= amount;
            System.out.println("Removed " + amount + " kg of " + cropType + ". New quantity:" + quantity + "kg.");
            return true;
        }else {
            System.out.println("Not enough crops to take.");
            System.out.println("Not enough " + cropType + " available to feed");
        }
        return false;
    }


    public String getCropType() {return cropType;}
    public void setCropType(String cropType) {this.cropType = cropType;}


    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}



}
