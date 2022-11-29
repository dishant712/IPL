package com.example.ipl2023.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipl2023.R;
import com.example.ipl2023.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class PlayerDetails extends AppCompatActivity {

    ImageView pencil,pre,next,copy,share,photo;
    TextView detail;

    String[] arr;
    String[] imga;
    int detailpos;
    int[] img;
    Bitmap bitmap;
    Palette palette;
    LinearLayout colors;
    ImageView download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        download = findViewById(R.id.download);
        detail = findViewById(R.id.detail);
        photo = findViewById(R.id.photo);
        pre = findViewById(R.id.pre);
        next = findViewById(R.id.next);
        copy = findViewById(R.id.copy);
        share = findViewById(R.id.share);
        colors = findViewById(R.id.colors);

        detailpos = getIntent().getIntExtra("pos",0);
        arr =  getIntent().getStringArrayExtra("name");
        img =  getIntent().getIntArrayExtra("img");

        detail.setText(arr[detailpos]);
        photo.setImageResource(img[detailpos]);

         bitmap = BitmapFactory.decodeResource(getResources(), img[detailpos]);
         palette = createPaletteSync(bitmap);
         Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();



        if(vibrantSwatch != null) {
            colors.setBackgroundColor(vibrantSwatch.getRgb());
        }


        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detailpos>0){
                    detailpos--;
                    detail.setText(arr[detailpos]);
                    photo.setImageResource(img[detailpos]);

                    bitmap = BitmapFactory.decodeResource(getResources(), img[detailpos]);
                    palette = createPaletteSync(bitmap);
                    Palette.Swatch vibrantSwatch = palette.getLightVibrantSwatch();
                    if(vibrantSwatch != null)
                    {
                        colors.setBackgroundColor(vibrantSwatch.getRgb());
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailpos< arr.length-1){
                    detailpos++;
                    detail.setText(arr[detailpos]);
                    photo.setImageResource(img[detailpos]);
                }

                bitmap = BitmapFactory.decodeResource(getResources(), img[detailpos]);
                palette = createPaletteSync(bitmap);
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if(vibrantSwatch != null){
                    colors.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("IPL");
//        builder.setMessage("message");
//        builder.setPositiveButton("yes",(dialog, i) -> {
//
//        });
//        builder.setNegativeButton("No",(dialog, i) -> {
//
//        });
//
//        copy.setOnClickListener(v -> {
//            builder.show();
//        });

        String[] arr={"Home Screen","Lock Screen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IPL");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int i) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    if(i==0)
                    {
                        myWallpaperManager.setResource(img[detailpos],WallpaperManager.FLAG_SYSTEM);

                    }
                    if(i==1)
                    {
                        myWallpaperManager.setResource(img[detailpos],WallpaperManager.FLAG_LOCK);

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        copy.setOnClickListener(v -> {
            builder.show();
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(photo);

                System.out.println("bitmap==>"+icon);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                int num = new  Random().nextInt(2000);

                File f = new File(config.file.getAbsolutePath()+ "/Shyari"+num+".jpg");
                try
                {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

//                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(f.getAbsolutePath()));
                try {
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(), "img", "Identified image")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

        download.setOnClickListener(v -> {

            Bitmap icon = getBitmapFromView(photo);

            System.out.println("bitmap==>"+icon);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            int num = new Random().nextInt(2000);

            File f = new File(config.file.getAbsolutePath()+ "/Shyari"+num+".jpg");
            try
            {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                Toast.makeText(this,"File Downloded",Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        });

    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    public Palette createPaletteSync(Bitmap bitmap)
    {
        Palette palette = Palette.from(bitmap).generate();
        return palette;
    }
}