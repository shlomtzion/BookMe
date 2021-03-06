package com.example.naamashlomtzion.bookme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Privileging;

public class MainActivity extends AppCompatActivity {

    Backend backend = BackendFactory.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button buttonEnter = (Button)findViewById(R.id.bt_enter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameUser = (EditText) findViewById(R.id.edit_name);
                EditText password = (EditText) findViewById(R.id.edit_password);

                try {

                String str = backend.returnId(password.getText().toString());
                Privileging privileging;
                long id;
                String st;
                if (str.startsWith("c")) {
                    privileging = Privileging.CLIENT;
                    st = str.substring(1);
                    Intent intentEnter;

                    id = Long.parseLong(st);
                    Toast.makeText(getApplicationContext(),st,Toast.LENGTH_LONG).show();
                    backend.findClient(id,nameUser.getText().toString());
                    intentEnter = new Intent(MainActivity.this, ClientActivity.class);
                    intentEnter.putExtra("idClient", id);
                    startActivity(intentEnter);
                }

                else {
                    privileging = Privileging.PROVIDER;
                    st = str.substring(1);
                    Intent intentEnterProvider;
                    id = Long.parseLong(st);
                    Toast.makeText(getApplicationContext(), st, Toast.LENGTH_LONG).show();
                    backend.findProvider(id,nameUser.getText().toString());
                    intentEnterProvider = new Intent(MainActivity.this,ProviderActivity.class);
                    intentEnterProvider.putExtra("idProvider",  id);
                    startActivity(intentEnterProvider);
                }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "אחד הנתונים שגוי" , Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });



        Button buttonAdd = (Button)findViewById(R.id.bt_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAdd = new Intent(MainActivity.this,addActivity.class);
                startActivity(intentAdd);

            }
        });

/*        try {
            backend.setBooklist();
        } catch (Exception e) {
            e.printStackTrace();
        }*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
