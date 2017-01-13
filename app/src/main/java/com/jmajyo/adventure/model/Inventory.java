package com.jmajyo.adventure.model;

import java.util.LinkedList;

public class Inventory {

    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory() {
    }

    public void print(){
            for (Item item: inventory ) {
            System.out.println(item.getName());
        }
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
