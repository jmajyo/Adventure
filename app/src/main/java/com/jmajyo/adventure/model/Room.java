package com.jmajyo.adventure.model;

import java.util.LinkedList;

public class Room {
    private String description;

    private LinkedList<Item> items;

    private Room roomNorh;
    private Room roomEast;
    private Room roomWest;
    private Room roomSouth;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedList<Item> getItems() {
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
}