package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Client;
import entities.Privileging;
import entities.Provider;

//import  static android.Telephony.SMS_DELIVER;

public class addActivity extends Activity {

    Backend backend = BackendFactory.getInstance(this);

    EditText firstName;
    EditText lastName ;
    EditText phone ;
    EditText address;
    EditText email ;
    String name;
    String password;
    String type;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });}


    public void onCheckboxClicked(View view) {//check box
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    type = "p";

                } else {
                    type = "c";
                }
                break;
        }




        Button buttonsignUp = (Button) findViewById(R.id.bt_sign_up);
        buttonsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               firstName = (EditText) findViewById(R.id.Text_firstName);
               lastName = (EditText) findViewById(R.id.Text_lastName);
                phone = (EditText) findViewById(R.id.TextPone);
                address = (EditText) findViewById(R.id.Text_address);
                email = (EditText) findViewById(R.id.Text_email);

                if (type=="p"){
                  name=firstName.getText().toString()+ "_"+lastName.getText().toString();
                   Provider provider = new Provider();
                   provider.setName(name);
                   provider.setPhone(phone.getText().toString());
                    provider.setAddress(address.getText().toString());
                   try {
                        password = backend.addProvider(provider, Privileging.CEO);
                        sendSms();

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                "טעות", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            }

                }

                        // Put some meat on the sandwich
                else{
                    name=firstName.getText().toString()+ "_"+lastName.getText().toString();
                    Client client = new Client();
                    client.setName(name);
                    client.setPhone(phone.getText().toString());
                    client.setAddress(address.getText().toString());
                    client.setEmail(email.getText().toString());
                    try {
                        password = backend.addClient(client);
                        sendSms();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "טעות", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    }
 }


            });
        }

    private void sendSms() {
        String number = phone.getText().toString();
        String massage = firstName.getText().toString()+" hallo, your user name: " + name + " your password : "+password;

        try {

            SmsManager manager = SmsManager.getDefault();
            //manager.sendTextMessage(number, "0527204658", massage,RESULT_ERROR_GENERIC_FAILURE,null);
            manager.sendTextMessage(number, null, massage, null, null);
            System.out.println(manager.toString());
            Toast.makeText(getApplicationContext(), "נישלח sms עם פרטיך", Toast.LENGTH_LONG).show();
            finish();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

}

