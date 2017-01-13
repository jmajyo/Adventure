package com.jmajyo.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jmajyo.adventure.model.Inventory;
import com.jmajyo.adventure.model.Item;
import com.jmajyo.adventure.model.MapGenerator;
import com.jmajyo.adventure.model.Room;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ImageButton helpButton;
    TextView mainText;
    ImageButton inventoryButton;
    ImageButton dropButton;
    ImageButton takeButton;
    ImageButton northButton;
    ImageButton eastButton;
    ImageButton southButton;
    ImageButton westButton;
    ImageButton lookButton;

    Inventory inventory = new Inventory();
    Room currentRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.activity_main_scene_text);//para poner aquí la descripción de la habitación.
        helpButton = (ImageButton) findViewById(R.id.activity_main_help_button);
        inventoryButton = (ImageButton) findViewById(R.id.activity_main_inventory);
        dropButton = (ImageButton) findViewById(R.id.activity_main_drop_button);
        takeButton = (ImageButton) findViewById(R.id.activity_main_take_button);
        northButton = (ImageButton) findViewById(R.id.activity_main_north_button);
        eastButton = (ImageButton) findViewById(R.id.activity_main_east_button);
        southButton = (ImageButton) findViewById(R.id.activity_main_south_button);
        westButton = (ImageButton) findViewById(R.id.activity_main_west_button);
        lookButton = (ImageButton) findViewById(R.id.activity_main_look_button);

        helpButton.setOnClickListener(new View.OnClickListener() {//a partir de new, me creo una clase anonima que tiene el método onclick. NO aparece la etiqueta de Class ni el nombre de la clase pq al crearla dentro de la llamada no hace falta.
            @Override                                               //Del mismo se podría hacer esto creando la clase justo arriba, dentro del propio metodo oncreate. ahí si tendría que ponerle la etiqueta class y el nombre y luego pasarle a esto el nombre del objeto creado
            public void onClick(View view) {                        //otro metodo posible sería crearse la clase fuera del metodo oncreate dentro de la otra clase

                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainText.setText(inventory.print());

            }
        });
        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomNorh();
                repaintScene();
            }
        });
        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomEast();
                repaintScene();
            }
        });
        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomSouth();
                repaintScene();
            }
        });
        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomWest();
                repaintScene();
            }
        });
        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRoomsItemsAndDescription();
            }
        });
        initGame();
        repaintScene();
        //mainText.setText(currentRoom.getDescription());
    }

    private void showRoomsItemsAndDescription() {
        LinkedList<Item> itemsCurrentRoom = new LinkedList<Item>();
        String currentRoomItems = new String();
        itemsCurrentRoom = currentRoom.getItems();
        currentRoomItems = currentRoom.getDescription() + "\n";
        if(itemsCurrentRoom == null)
            currentRoomItems = currentRoomItems + "Habitación vacía.";
        else {
            for (Item item : itemsCurrentRoom) {
                currentRoomItems = currentRoomItems + item.getName() + "\n";
            }
        }
        mainText.setText(currentRoomItems);
    }


    private void  initGame(){
        Item espada = new Item("Espada", "Una hoja muy afilada");
        Item trozoDePapel = new Item("Trozo de papel", "Un trozo de papel blanco");
        Item polloDeGoma = new Item("Pollo de goma", "Toda persona debería tener uno.");


        inventory.add(espada);
        inventory.add(trozoDePapel);
        inventory.add(polloDeGoma);

        MapGenerator.generate();

        currentRoom = MapGenerator.initialRoom;
    }

    private void repaintScene() {
        //write room description on screen
        mainText.setText(currentRoom.getDescription());

        //change button visibility
        if(currentRoom.getRoomNorh() != null){
            northButton.setVisibility(View.VISIBLE);
        }else{
            northButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomSouth() != null){
            southButton.setVisibility(View.VISIBLE);
        }else{
            southButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomEast() != null){
            eastButton.setVisibility(View.VISIBLE);
        }else{
            eastButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomWest() != null){
            westButton.setVisibility(View.VISIBLE);
        }else{
            westButton.setVisibility(View.INVISIBLE);
        }
    }

}
