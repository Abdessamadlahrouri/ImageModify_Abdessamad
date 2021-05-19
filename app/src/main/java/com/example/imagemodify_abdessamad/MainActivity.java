package com.example.imagemodify_abdessamad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;



public class MainActivity extends AppCompatActivity {


    private Button btnImport;

    private ImageView selectedImg;
    static final int RESULT_LOAD_IMG = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        registerForContextMenu(img);
        btnImport = findViewById(R.id.button1);

        selectedImg = findViewById(R.id.imageView);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);


        }


        });

    }
    public Bitmap bm;

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);




            if (resultCode == RESULT_OK) {
                try {

                    final Uri imageUri = data.getData();
                    BitmapFactory.Options option = new BitmapFactory.Options();
                    option.inMutable = true;

                    /*Bitmap*/ bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, option);

                    selectedImg.setImageBitmap(bm);


                    TextView text = (TextView) findViewById(R.id.TextView);
                    text.setText("" + imageUri);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();


                }

            } else {
                Toast.makeText(getApplicationContext(), "aucune image n'a été selectionnée", Toast.LENGTH_LONG).show();

            }
    }



    public void rotHor() {

        Bitmap bitmap = ((BitmapDrawable) selectedImg.getDrawable()).getBitmap();
        Bitmap bitmaph = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());


        int w = bitmap.getWidth();//x
        int h = bitmap.getHeight();//y
        int z;


        try {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    z = w - (x + 1);
                    bitmaph.setPixel(z, y, bitmap.getPixel(x, y));
                    //bitmapv.setPixel(y,x,bitmap.getPixel(y,x));
                }
            }
            selectedImg.setImageBitmap(bitmaph);
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "aucune image n'a été selectionnée", Toast.LENGTH_LONG).show();
        }
    }




    public void rotVer() {

        Bitmap bitmap = ((BitmapDrawable) selectedImg.getDrawable()).getBitmap();
        Bitmap bitmapv = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        int h = bitmap.getWidth();//x
        int w = bitmap.getHeight();//y
        int z;
        try {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    z = w - (x + 1);
                    bitmapv.setPixel(y, z, bitmap.getPixel(y, x));
                }
            }
            selectedImg.setImageBitmap(bitmapv);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "aucune image n'a été selectionnée", Toast.LENGTH_LONG).show();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public void rotDroite(){//verticale lfou9 ywli lt7t
            Bitmap bitmap = ((BitmapDrawable) selectedImg.getDrawable()).getBitmap();
            Bitmap bitmapv = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

            int h = bitmap.getWidth();//x
            int w = bitmap.getHeight();//y
            int z;
            try {

                for (int y =0;y<h;y++){
                    for (int x =0;x<w;x++){
                        z=w-(x+1);
                        //bitmaph.setPixel(y,x,bitmap.getPixel(x,y));
                        bitmapv.setPixel(y,z,bitmap.getPixel(x,y));
                    }
                }
                selectedImg.setImageBitmap(bitmapv);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "aucune image n'a été selectionnée", Toast.LENGTH_LONG).show();
            }
        }

        public void rotGauche(){
            Bitmap bitmap = ((BitmapDrawable) selectedImg.getDrawable()).getBitmap();
            Bitmap bitmapv = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

            int h = bitmap.getWidth();//x
            int w = bitmap.getHeight();//y
            int z;
            try {

                for (int y =0;y<h;y++){
                    for (int x =0;x<w;x++){
                        z=w-(y+1);
                        //bitmaph.setPixel(y,x,bitmap.getPixel(x,y));
                        bitmapv.setPixel(z,x,bitmap.getPixel(x,y));
                    }
                }
                selectedImg.setImageBitmap(bitmapv);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "aucune image n'a été selectionnée", Toast.LENGTH_LONG).show();
            }

        }

////////////////////////////////////////////////////////////////////////////////////////////::
    public void Restaurer(){


        selectedImg.setImageBitmap(bm);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if(id== R.id.choix01) rotVer();
        if (id==R.id.choix02) rotHor();
        if (id==R.id.choix03) rotGauche();
        if (id==R.id.choix04) rotDroite();
        if (id==R.id.choix05) Restaurer();

        return super.onContextItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Inverser Couleurs");
        menu.add(0, v.getId(), 0, "Transformer en Gris");
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Inverser Couleurs") {

            Bitmap bitmap = ((BitmapDrawable)selectedImg.getDrawable()).getBitmap();
            Bitmap bitmapv =Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight() ,bitmap.getConfig());



            int h = bitmap.getWidth();//x
            int w = bitmap.getHeight();//y
            int pixel;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pixel = bitmap.getPixel(y, x);
                  //  pixel=255-pixel;
                    bitmapv.setPixel(y, x, Color.rgb(255-pixel, 255-pixel, 255-pixel));

                }
            }
            selectedImg.setImageBitmap(bitmapv);

        }
        else {
            if (item.getTitle() == "Transformer en Gris") {
                Bitmap bitmap = ((BitmapDrawable)selectedImg.getDrawable()).getBitmap();
                Bitmap bitmapv =Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight() ,bitmap.getConfig());



                int h = bitmap.getWidth();//x
                int w = bitmap.getHeight();//y
                int pixel;
                int red,green,blue,moy;

                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        pixel = bitmap.getPixel(y, x);
                         red = Color.red(pixel);
                         green = Color.green(pixel);
                         blue = Color.blue(pixel);
                         moy = (red+green+blue)/3;

                        bitmapv.setPixel(y, x, Color.rgb(moy, moy, moy));

                    }
                }
                selectedImg.setImageBitmap(bitmapv);
            }
            return  false;
        }
        return true;
    }




}





