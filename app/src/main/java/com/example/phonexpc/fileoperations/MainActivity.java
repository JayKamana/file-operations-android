package com.example.phonexpc.fileoperations;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtSurname, txtPhone;
    Button btnAdd, btnList;
    private final String FILENAME = "myFile.txt";
    ArrayList contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtPhone = findViewById(R.id.txtPhone);

        btnAdd = findViewById(R.id.btnAdd);
        btnList = findViewById(R.id.btnList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
                    String text = txtName.getText().toString() + " " + txtSurname.getText().toString() + " " + txtPhone.getText().toString()+"\n";
                    fos.write(text.getBytes());
                    fos.close();

                    Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String text;
                    FileInputStream fin = openFileInput(FILENAME);
                    int size = fin.available();
                    byte[] buffer = new byte[size];
                    fin.read(buffer);
                    fin.close();
                    text=new String(buffer);

                    contents = new ArrayList<>(Arrays.asList(text.split("\n")));
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("array_list", contents);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "File read error!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
