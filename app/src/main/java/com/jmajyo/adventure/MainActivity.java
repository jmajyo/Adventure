package com.jmajyo.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmajyo.adventure.model.Inventory;
import com.jmajyo.adventure.model.Item;
import com.jmajyo.adventure.model.MapGenerator;
import com.jmajyo.adventure.model.Room;
import com.jmajyo.adventure.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //Esto es lo mismo que el findviewbyid solo que con la libreria butterknife. este metodo lo utilizan todos los programadores
    //pq es mucho más cómodo y se ve mejor.
    @BindView(R.id.activity_main_help_button)   ImageButton helpButton;
    @BindView(R.id.activity_main_scene_text)    TextView mainText;
    @BindView(R.id.activity_main_scene_image)   ImageView sceneImage;
    @BindView(R.id.activity_main_inventory)     ImageButton inventoryButton;
    @BindView(R.id.activity_main_drop_button)   ImageButton dropButton;
    @BindView(R.id.activity_main_take_button)   ImageButton takeButton;
    @BindView(R.id.activity_main_north_button)  ImageButton northButton;
    @BindView(R.id.activity_main_east_button)   ImageButton eastButton;
    @BindView(R.id.activity_main_south_button)  ImageButton southButton;
    @BindView(R.id.activity_main_west_button)   ImageButton westButton;
    @BindView(R.id.activity_main_look_button)   ImageButton lookButton;

    Inventory inventory = new Inventory();
    Room currentRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).setLoggingEnabled(true);

        helpButton.setOnClickListener(new View.OnClickListener() {//a partir de new, me creo una clase anonima que tiene el método onclick. NO aparece la etiqueta de Class ni el nombre de la clase pq al crearla dentro de la llamada no hace falta.
            @Override                                               //Del mismo se podría hacer esto creando la clase justo arriba, dentro del propio metodo oncreate. ahí si tendría que ponerle la etiqueta class y el nombre y luego pasarle a esto el nombre del objeto creado
            public void onClick(View view) {   //otro metodo posible sería crearse la clase fuera del metodo oncreate dentro de la otra clase

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
                Intent i = new Intent(MainActivity.this, DropItemActivity.class);
                i.putExtra(Constants.KEY_INTENT_INVENTORY, inventory); //le paso el inventario a la nueva ventana. Una especie de mapa, la clave es KEY_INVENTORY y el "mapa" es inventory
                                                        //Para que funcionará hemos tenido que extender la clase Inventory con serializable.
                startActivityForResult(i,1); //El número es para indicarle que actividad le devuelve los datos. Porque tu puedes lanzar actividadades desde muchos
                                             //sitios y cuando vuelvan al main entraran todas por un main, por lo que tenemos que saber de donde vienen para tratar
                                             //los datos
            }
        });
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DropItemActivity.class);
                i.putExtra(Constants.KEY_INTENT_TAKE_ITEM_FROM_ROOM, currentRoom);

                startActivityForResult(i,2);
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
                mainText.setText(currentRoom.getDescription() + "\n" + currentRoom.getRoomItems());
            }
        });
        initGame();
        repaintScene();
    }


    private void  initGame(){
        Item espada = new Item("Espada", "Una hoja muy afilada");
        Item trozoDePapel = new Item("Trozo de papel", "Un trozo de papel blanco");
        Item polloDeGoma = new Item("Pollo de goma", "Toda persona debería tener uno.");


        inventory.add(espada);
        inventory.add(trozoDePapel);
        inventory.add(polloDeGoma);

        MapGenerator.generate(this);

        currentRoom = MapGenerator.initialRoom;
    }

    private void repaintScene() {
        //write room description on screen
        mainText.setText(currentRoom.getDescription());
        if(currentRoom.getImageUrl() != null && currentRoom.getImageUrl().length()>1)
        {
            Picasso.
                    with(this).
                    load(currentRoom.getImageUrl()).
                    into(sceneImage);
        }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                int itemPosition = data.getIntExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, -1);

                Item item = inventory.getItem(itemPosition);
                //item = inventory.getInventory().get(itemPosition); igual que la linea de arriba. Puede servir esta linea sino te quieres crear el metodo getItem
                currentRoom.getItems().add(item);
                inventory.delete(itemPosition);

                Snackbar.make(mainText, getString(R.string.dropped_item_text) + item.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else if(requestCode == 2){//take
            if(resultCode == RESULT_OK){
                int itemPosition = data.getIntExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, -1);

                Item item = currentRoom.getItems().get(itemPosition);
                inventory.add(item);
                currentRoom.getItems().remove(item);

                Snackbar.make(mainText, "Taken " + item.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}
