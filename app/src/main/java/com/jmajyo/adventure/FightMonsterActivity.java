package com.jmajyo.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmajyo.adventure.model.Monster;
import com.jmajyo.adventure.model.Player;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FightMonsterActivity extends AppCompatActivity {

    @BindView(R.id.activity_monster_fight_button_attack)   Button fightButton;
    @BindView(R.id.activity_fight_monster_title)    TextView monsterNameText;
    @BindView(R.id.activity_monster_description) TextView monsterDescriptionText;
    @BindView(R.id.activity_fight_monster_image)    ImageView monsterImage;
    @BindView(R.id.activity_monster_fight_monster_life_text) TextView monsterLifeText;
    @BindView(R.id.activity_monster_fight_your_life_text) TextView playerLifeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_monster);
        ButterKnife.bind(this);

        Intent i = getIntent();
        final Monster monster = (Monster) i.getSerializableExtra("monster");
        final Player player = (Player) i.getSerializableExtra("player");

        monsterNameText.setText(monster.getName());
        monsterDescriptionText.setText(monster.getDescription());
        Picasso.with(this).load(monster.getImageUrl()).into(monsterImage);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monsterAttack = monster.attack();
                int playerAttack = player.attack();
                player.setHealthPoints(player.getHealthPoints()-monsterAttack);
                monster.setHealthPoints(monster.getHealthPoints()-playerAttack);

                playerLifeText.setText("HP " + player.getHealthPoints());
                monsterLifeText.setText("HP " + monster.getHealthPoints());
            }
        });

    }
}
