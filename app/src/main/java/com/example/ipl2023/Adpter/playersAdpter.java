package com.example.ipl2023.Adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipl2023.Activity.teamPlayerName;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

public class playersAdpter extends BaseAdapter {

        teamPlayerName teamPlayerName;
        String[] name;
        int[] img;

    public playersAdpter(teamPlayerName teamPlayerName, String[] name, int[] img) {
        this.teamPlayerName = teamPlayerName;
        this.name = name;
        this.img = img;

    }

    @Override
    public int getCount() {
        return name.length;
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
        view  = LayoutInflater.from(teamPlayerName).inflate(R.layout.extra_player_item,viewGroup,false);

        ImageView imageView = view.findViewById(R.id.player_img);
        TextView textView = view.findViewById(R.id.player_text);

        imageView.setImageResource(img[i]);
        textView.setText(name[i]);

        return view;
    }
}
