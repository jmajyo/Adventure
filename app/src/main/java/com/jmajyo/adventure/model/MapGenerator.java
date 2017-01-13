package com.jmajyo.adventure.model;

import java.util.LinkedList;

public class MapGenerator {
    public static Room initialRoom;

    public static void generate(){
        Room room1 = new Room();
        room1.setDescription("[Sala 1] Te encuentras en un aula con las contraventanas semicerradas. Olor a ordenador encenddio y cerebro firto impregna tus sentidos.");

        Room room2 = new Room();
        room2.setDescription("[Sala 2] Te deslumbra la luz del Sol que se filtra por las ventanas del pasillo. Sientes un escalofrío al ver a un grajo arrastrandose.");

        Room room3 = new Room();
        room3.setDescription("[Sala 3] Hay una barra de bar con tapicería roja pasada de moda. Huele a tabaco usado y lágrimas de tango.");

        Room room4 = new Room();
        room4.setDescription("[Sala 4] No sabemos lo que hay por aquí habría que investigar.");

        //link rooms (enlazo las habitaciones)
        room1.setRoomSouth(room2);
        room1.setRoomEast(room4);

        room2.setRoomNorh(room1);
        room2.setRoomEast(room3);

        room3.setRoomWest(room2);
        room3.setRoomNorh(room4);

        room4.setRoomWest(room1);
        room4.setRoomSouth(room3);

        //Creo varios Itens en la habitación 3
        LinkedList<Item> itemsRoom3 = new LinkedList<>();
        itemsRoom3.add(new Item("Botella","Una botella de Vodka Moskovskaya"));
        itemsRoom3.add(new Item("Cuchillo", "Cuchillo jamonero"));
        itemsRoom3.add(new Item("Billete de 500 Eur", "Unicornio hecho papel moneda"));
        room3.setItems(itemsRoom3);

        initialRoom=room1;
    }
}