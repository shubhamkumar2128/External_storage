package com.example.external_storage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.intercachebtn)
    Button intercachebtn;
    @BindView(R.id.excachebtn)
    Button excachebtn;
    @BindView(R.id.exprivatebtn)
    Button exprivatebtn;
    @BindView(R.id.epbtn)
    Button epbtn;
    @BindView(R.id.nextbtn)
    Button nextbtn;
    @BindView(R.id.etdata)
    EditText etdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1212);
        }


    }


    @OnClick(R.id.intercachebtn)
    public void saveInternalCacheData(View v) {
        File folder = getCacheDir();
        File myfile = new File(folder, "saveInternalCacheData.txt");
        writeData(myfile, etdata.getText().toString().getBytes());

    }

    @OnClick(R.id.excachebtn)
    public void saveExternalCacheData(View v) {
        File folder = getExternalCacheDir();
        File myfile = new File(folder, "saveExternalCacheData.txt");
        writeData(myfile, etdata.getText().toString().getBytes());
        // Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.exprivatebtn)
    public void saveExternalPrivateData(View v) {
        File folder = getExternalFilesDir("saveExternalPrivateData");
        File myfile = new File(folder, "saveExternalPrivateData.txt");
        writeData(myfile, etdata.getText().toString().getBytes());
    }

    @OnClick(R.id.epbtn)
    public void saveExternalPublicData(View v) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile = new File(folder, "saveExternalPublicData.txt");
        writeData(myfile, etdata.getText().toString().getBytes());
    }

    @OnClick(R.id.nextbtn)
    public void goToNextActivity(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    private void writeData(File myfile, byte data[]) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myfile);
            fileOutputStream.write(data);
            Toast.makeText(this, myfile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.d("err", e.getMessage());
        } catch (IOException e) {
            Log.d("err", e.getMessage());
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                Log.d("err", e.getMessage());
            }
        }
    }

}
