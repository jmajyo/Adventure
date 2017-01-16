package com.jmajyo.adventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jmajyo.adventure.model.Inventory;
import com.jmajyo.adventure.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DropItemActivity extends AppCompatActivity {

    @BindView(R.id.activity_drop_item_list)     ListView itemList;

    Inventory inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_item);
        ButterKnife.bind(this);

        Intent i = getIntent();
        inventory = (Inventory) i.getSerializableExtra(Constants.KEY_INTENT_INVENTORY);//me devuelve el inventario que le pasamos a la hora de invocarlo
                                                        //La constante se ha creado para que no haya fallo a la hora de escribir la cadena de caracteres como clave
                                                        //Es mejor utilizar constantes como claves para EVITAR FALLOS

        //Ahora definimos el adaptador para poder visualizarle los datos que le hemos pasado.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventory.getItemNames());
        itemList.setAdapter(adapter);

        //Fijarse bien del metodo, no es ONCLICKLISTENER si no ONITEMCLICKLISTENER
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View row, int position, long id) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION,position);
                setResult(RESULT_OK, returnIntent);
                finish();//al cerrar la pantalla se va a disparar el metodo de onActivityResult del main
            }
        });
    }
}
