package org.example;

public class Entity {

    protected int id;
    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void GetDescription(){
    }

    public int getId() {return id ;}
    public void setId(int id) {this.id = id;}


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
