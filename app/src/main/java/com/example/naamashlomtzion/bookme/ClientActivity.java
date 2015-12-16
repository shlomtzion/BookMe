package com.example.naamashlomtzion.bookme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    try {
    Button buttonAllBook = (Button) findViewById(R.id.bt_allBook);
    buttonAllBook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intentAllBook = new Intent(ClientActivity.this, AllBookActivity.class);
            startActivity(intentAllBook);

        }
    });
    }catch (Exception e) {
    Toast.makeText(getApplicationContext(),
            "SMS faild, please try again", Toast.LENGTH_LONG).show();
    e.printStackTrace();
    }


    }

}
