package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClientActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        final long IDcurrentClient = (long) getIntent().getSerializableExtra("idClient");


        try {
        Button buttonAllBook = (Button) findViewById(R.id.bt_allBook);
        Button buttonBestSeller = (Button) findViewById(R.id.bt_sellBook);
        Button buttonBestRecom = (Button) findViewById(R.id.bt_recoBook);

        buttonAllBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAllBook = new Intent(ClientActivity.this, ListBooksActivity.class);
                intentAllBook.putExtra("type button",1);
                intentAllBook.putExtra("idClient", IDcurrentClient);
                startActivity(intentAllBook);

            }
        });

        buttonBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAllBook = new Intent(ClientActivity.this, ListBooksActivity.class);
                intentAllBook.putExtra("type button",2);
                intentAllBook.putExtra("idClient", IDcurrentClient);
                startActivity(intentAllBook);

            }
        });

        buttonBestRecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAllBook = new Intent(ClientActivity.this, ListBooksActivity.class);
                intentAllBook.putExtra("type button",3);
                intentAllBook.putExtra("idClient", IDcurrentClient);
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
