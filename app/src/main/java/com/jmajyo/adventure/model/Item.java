package com.jmajyo.adventure.model;

import java.io.Serializable;

public class Item implements Serializable{
    private String name;
    private String description;

    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    //voy a sobreescribir el metodo toString que hereda de la calse padre (Object)
    @Override
    public String toString() {
        return "" + getName() + "\t" + getDescription() + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
