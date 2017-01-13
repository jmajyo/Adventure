package com.jmajyo.adventure.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Inventory implements Serializable {
//Serializable es un interfaz que permite coger los datos de memoria en un objeto y ponerlos en otro
//Lo de serializable sirve para pasar cualquier cosa por un Intent
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
