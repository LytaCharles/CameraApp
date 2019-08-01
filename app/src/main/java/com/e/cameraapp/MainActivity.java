package com.e.cameraapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;


public class MainActivity extends AppCompatActivity {

    private ImageView mCameraImage;
    private Button btCapture;
    private Uri mPhotoUri;
    private static final int TAKE_PICK_REQUEST =100;
    private String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >22){

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},2);

        }

        mCameraImage = (ImageView) findViewById(R.id.iv_cameraimage_id);
        btCapture = (Button) findViewById(R.id._buttoncaptureimage_id);

        btCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selfie();

            }
        });
    }

    private void selfie() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICK_REQUEST);
    }

    /*** private void selfie() {

        //setting an action for the camera
        Intent captureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (captureintent.resolveActivity(getPackageManager()) != null)
        {
            File capturephotofile = null;
            capturephotofile = CreatePhotoFile();

            //saving the photo to the photofile variable
            if (capturephotofile != null){

                String pathToFile = capturephotofile.getAbsolutePath();

                mPhotoUri = FileProvider.getUriForFile(MainActivity.this, "com.cameraapp.fileprovider ", capturephotofile);
                captureintent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
            }
            startActivityForResult(captureintent,TAKE_PICK_REQUEST);




        }else{
            Toast.makeText(this, "No app available to open camera", Toast.LENGTH_SHORT).show();
        }
    }

    private File CreatePhotoFile() {
        //creating a name for the file where our photo will be stored
        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //setting a directory to allow other apps to access your photos
        File storageDirectory = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //CREATING THE ACTUAL FILE
        File imagefile = null;
        try{
            imagefile = File.createTempFile(filename,"jpg", storageDirectory);
        }catch(Exception e){
            Log.d("myLog,","Except:" + e.toString());

        }
        return imagefile;

    }*****/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICK_REQUEST &&  resultCode == RESULT_OK){


                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                mCameraImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                mCameraImage.setImageBitmap(bitmap);


        }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();

            }
        }
}
