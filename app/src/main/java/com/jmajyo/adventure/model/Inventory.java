package com.jmajyo.adventure.model;

import java.util.LinkedList;

public class Inventory {

    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory() {
    }

    public String print(){
        String inventario = new String();
            for (Item item: inventory ) {
                inventario = inventario + item.getName() + "\n";
        }
        return inventario;
    }

    public LinkedList<Item> getInventory() {
        return inventory;
    }

    public void add(Item item){
        this.inventory.add(item);
    }

    public void delete(Item item){
        this.inventory.remove(item);
    }


}
