package com.example.ipl2023.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ipl2023.Adpter.teamAdpter;
import com.example.ipl2023.R;
import com.example.ipl2023.config;

import java.io.File;

public class IplTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_item;

        ActivityCompat.requestPermissions(IplTeam.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        list_item = findViewById(R.id.list_item);
            teamAdpter ta = new teamAdpter(this);
            list_item.setAdapter(ta);

            list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent =  new Intent(IplTeam.this,teamPlayerName.class);
                    intent.putExtra("pos",i);
                    startActivity(intent);
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    config.file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/IPL ");
                    if (config.file.exists())
                    {
                        System.out.println("Folder Available");
                    }
                    else
                    {
                        System.out.println("Folder Not Available");
                        if (config.file.mkdir())
                        {
                            System.out.println("Folder Created");
                        }
                        else
                        {
                            System.out.println("Folder Not Created");
                        }
                    }
                }
                else
                {
                    ActivityCompat.requestPermissions(IplTeam.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    Toast.makeText(IplTeam.this,"Permission Denied to Read your External Storage",Toast.LENGTH_SHORT).show();
                }

                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}