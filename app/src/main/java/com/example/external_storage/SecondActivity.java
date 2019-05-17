package com.example.external_storage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.loadintercachebtn)
    Button loadintercachebtn;
    @BindView(R.id.loadexcachebtn)
    Button loadexcachebtn;
    @BindView(R.id.loadexprivatetn)
    Button loadexprivatetn;
    @BindView(R.id.loadepbtn)
    Button loadepbtn;
    @BindView(R.id.prevbtn)
    Button prevbtn;
    @BindView(R.id.etdata)
    EditText etdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loadintercachebtn)
    public void loadInternalCacheData(View v) {
        File folder = getCacheDir();
        File file = new File(folder, "saveInternalCacheData.txt");
        readData(file);

    }

    @OnClick(R.id.loadexcachebtn)
    public void loadExternalCacheData(View v) {
        File folder = getExternalCacheDir();
        File file = new File(folder, "saveExternalCacheData.txt");
        readData(file);
    }

    @OnClick(R.id.loadexprivatetn)
    public void loadExternalPrivateData(View v) {
        File folder = getExternalFilesDir("saveExternalPrivateData");
        File file = new File(folder, "saveExternalPrivateData.txt");
        readData(file);
    }

    @OnClick(R.id.loadepbtn)
    public void loadExternalPublicData(View v) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "saveExternalPublicData.txt");
        readData(file);
    }

    @OnClick(R.id.prevbtn)
    public void goToPreviousActivity(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void readData(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            String s = "";
            int r = -1;
            while ((r = fileInputStream.read()) != -1) {
                s = s + (char) r;
            }
            etdata.setText(s);
        } catch (FileNotFoundException e) {
            Log.d("err", e.getMessage());
        } catch (IOException e) {
            Log.d("err", e.getMessage());
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                Log.d("err", e.getMessage());
            }
        }
    }
}
