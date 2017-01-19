package com.jmajyo.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.jmajyo.adventure.util.JetPlayerUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jmajyo.adventure.R.raw.jet;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.activity_intro_button) Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Para quitar la barra que sale arriba en azul con el titulo de la aplicación
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);//Tienen que llevar este orden específico!!!!!!!! esta linea arriba
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//Esta linea tiene que ir debajod el onCreate.
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        JetPlayerUtil.play(this, jet);

    }
}
