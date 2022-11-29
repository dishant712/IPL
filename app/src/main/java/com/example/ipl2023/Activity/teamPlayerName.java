package com.example.ipl2023.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ipl2023.Adpter.playersAdpter;
import com.example.ipl2023.Adpter.teamAdpter;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

public class teamPlayerName extends AppCompatActivity {

    ListView list_team_player;
    int pos;
    int[] img;
    String[] name;

    //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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_player_name);


        list_team_player = findViewById(R.id.list_team_player);

            pos = getIntent().getIntExtra("pos",0);

            if (pos == 0){
                name = config.csk;
                img = config.csk_player;
            }
            if (pos == 1)
            {
                name = config.delhi;
                img = config.delhi_player;
            }
            if (pos == 2)
            {
                name = config.kkr;
                img = config.kkr_player;
            }
            if (pos == 3)
            {
                name = config.mi;
                img = config.mi_player;
            }
            if (pos == 4)
            {
                name = config.pb;
                img = config.pb_player;
            }
            if (pos == 5)
            {
                name = config.rr;
                img = config.rr_player;
            }
            if (pos == 6)
            {
                name = config.rcb;
                img = config.rcb_player;
            }
            if (pos == 7)
            {
                name = config.gt;
                img = config.gt_player;
            }
            if (pos == 8)
            {
                name = config.srh;
                img = config.srh_player;
            }
            if (pos == 9)
            {
                name = config.lsg;
                img = config.lsg_player;
            }


        playersAdpter playersAdpter = new playersAdpter(this,name,img);
        list_team_player.setAdapter(playersAdpter);

        list_team_player.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent =  new Intent(teamPlayerName.this,PlayerDetails.class);
                intent.putExtra("pos",i);
                intent.putExtra("name",name);
                intent.putExtra("img",img);
                startActivity(intent);
            }
        });


    }
}