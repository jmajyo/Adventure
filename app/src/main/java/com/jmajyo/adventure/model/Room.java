package com.jmajyo.adventure.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Room implements Serializable{
    private String description;

    private LinkedList<Item> items;
    private Monster monster;

    private Room roomNorh;
    private Room roomEast;
    private Room roomWest;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    private Room roomSouth;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // lazy getter
    public LinkedList<Item> getItems() {
        if(items == null){
            items = new LinkedList<>();
        }
        return items;
    }

    public String getRoomItems(){
        if(this.items == null)
            return  "Habitación vacía";
        String result = "";
        for (Item item : this.items) {
            result = result + "-" + item.getName() + "\n";
        }
        return result;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public Room getRoomNorh() {
        return roomNorh;
    }

    public void setRoomNorh(Room roomNorh) {
        this.roomNorh = roomNorh;
    }

    public Room getRoomEast() {
        return roomEast;
    }

    public void setRoomEast(Room roomEast) {
        this.roomEast = roomEast;
    }

    public Room getRoomWest() {
        return roomWest;
    }

    public void setRoomWest(Room roomWest) {
        this.roomWest = roomWest;
    }

    public Room getRoomSouth() {
        return roomSouth;
    }

    public void setRoomSouth(Room roomSouth) {
        this.roomSouth = roomSouth;
    }

    public void add(Item item) {
        if (items == null) {
            items = new LinkedList<>();
        }
        items.add(item);
    }

    public List<String> getItemNames() {
        ArrayList<String> result = new ArrayList<>();

        for(Item item: items){
            result.add(item.getName());
        }

        return result;
    }
}