package com.jmajyo.adventure.model;

import android.content.Context;

import com.jmajyo.adventure.R;

import java.util.LinkedList;

public class MapGenerator {
    public static Room initialRoom;

    public static void generate(Context context){

        Monster monster1 = new Monster();
        monster1.setName(context.getString(R.string.monster1_name));
        monster1.setDescription(context.getString(R.string.monster1_description));
        monster1.setImageUrl(context.getString(R.string.monster1_imageUrl));


        Room room1 = new Room();
        room1.setDescription(context.getString(R.string.room_desc1));
        room1.setImageUrl(context.getString(R.string.room_img1));

        Room room2 = new Room();
        room2.setDescription(context.getString(R.string.room_desc2));
        room2.setImageUrl(context.getString(R.string.room_img2));

        Room room3 = new Room();
        room3.setDescription(context.getString(R.string.room_desc3));
        room3.setImageUrl(context.getString(R.string.room_img3));

        Room room4 = new Room();
        room4.setDescription(context.getString(R.string.room_desc4));
        room4.setImageUrl(context.getString(R.string.room_img4));
        room4.setMonster(monster1);


        Room room5 = new Room();
        room5.setDescription(context.getString(R.string.room_desc5));
        room5.setImageUrl(context.getString(R.string.room_img5));

        Room room6 = new Room();
        room6.setDescription(context.getString(R.string.room_desc6));
        room6.setImageUrl(context.getString(R.string.room_img6));

        Room room7 = new Room();
        room7.setDescription(context.getString(R.string.room_desc7));
        room7.setImageUrl(context.getString(R.string.room_img7));

        Room room8 = new Room();
        room8.setDescription(context.getString(R.string.room_desc8));
        room8.setImageUrl(context.getString(R.string.room_img8));

        //link rooms (enlazo las habitaciones)
        room1.setRoomSouth(room2);
        room1.setRoomEast(room4);

        room2.setRoomNorh(room1);
        room2.setRoomEast(room3);
        room2.setRoomWest(room5);

        room3.setRoomWest(room2);
        room3.setRoomNorh(room4);

        room4.setRoomWest(room1);
        room4.setRoomSouth(room3);

        room5.setRoomEast(room2);
        room5.setRoomSouth(room6);

        room6.setRoomNorh(room5);
        room6.setRoomWest(room7);

        room7.setRoomEast(room6);

        //Creo varios Itens en la habitación 3
        LinkedList<Item> itemsRoom3 = new LinkedList<>();
        itemsRoom3.add(new Item("Botella","Una botella de Vodka Moskovskaya"));
        itemsRoom3.add(new Item("Cuchillo", "Cuchillo jamonero"));
        itemsRoom3.add(new Item("Billete de 500 Eur", "Unicornio hecho papel moneda"));
        room3.setItems(itemsRoom3);

        initialRoom=room1;
    }
}
