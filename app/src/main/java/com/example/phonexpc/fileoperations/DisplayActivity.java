package com.example.phonexpc.fileoperations;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> array;
    Button btnUpdate;

    private final String FILENAME = "myFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        list = findViewById(R.id.fileList);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle bundle = getIntent().getExtras();
        array = bundle.getStringArrayList("array_list");

        listAdapter = new ArrayAdapter<>(DisplayActivity.this, android.R.layout.simple_list_item_1, array);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                array.remove(list.getItemAtPosition(i).toString());
                listAdapter.notifyDataSetChanged();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "";
                for (int i = 0; i < array.size(); i++) {
                    text += array.get(i) + "\n";
                }

                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();

                    Toast.makeText(getBaseContext(), "File Updated successfully!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
