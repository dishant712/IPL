package com.example.ipl2023.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipl2023.Activity.IplTeam;
import com.example.ipl2023.Activity.teamPlayerName;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

public class teamAdpter extends BaseAdapter {
    IplTeam iplTeam;
    public teamAdpter(IplTeam iplTeam) {
            this.iplTeam = iplTeam;
    }

    @Override
    public int getCount() {
        return config.teams_name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(iplTeam).inflate(R.layout.extra_teams_item,viewGroup,false);

        ImageView imageView = view.findViewById(R.id.team_img);
        TextView textView = view.findViewById(R.id.team_text);

        imageView.setImageResource(config.teams_image[i]);
        textView.setText(config.teams_name[i]);

        return view;
    }
}
