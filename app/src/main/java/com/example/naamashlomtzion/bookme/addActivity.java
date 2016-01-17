package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
    CheckBox ifProvider;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



/*    public void onCheckboxClicked(View view) {//check box
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    type = "p";

                } else {
                    type = "c";
                }
                break;
        }*/


        //final CheckBox ifProvider = (CheckBox)findViewById(R.id.checkBox_wantProvider);

        Button buttonsignUp = (Button) findViewById(R.id.bt_sign_up);
        buttonsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = (EditText) findViewById(R.id.Text_firstName);
                lastName = (EditText) findViewById(R.id.Text_lastName);
                phone = (EditText) findViewById(R.id.TextPone);
                address = (EditText) findViewById(R.id.Text_address);
                email = (EditText) findViewById(R.id.Text_email);
                ifProvider = (CheckBox)findViewById(R.id.checkBox_wantProvider);
                if (ifProvider.isChecked())
                    type = "p";
                else
                    type = "c";

                if (type=="p") {
                    name = firstName.getText().toString() + "_" + lastName.getText().toString();
                    final Provider provider = new Provider();
                    provider.setName(name);
                    provider.setPhone(phone.getText().toString());
                    provider.setAddress(address.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("ברוכים הבאים");
                    try {
                        password = backend.addProvider(provider, Privileging.CEO);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String massage = "שם משתמש: " + name + '\n' + "הסיסמה שלך: " + password;
                    builder.setMessage(massage);
                    builder.setPositiveButton("אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                else{
                    name=firstName.getText().toString()+ "_"+lastName.getText().toString();
                    final Client client = new Client();
                    client.setName(name);
                    client.setPhone(phone.getText().toString());
                    client.setAddress(address.getText().toString());
                    client.setEmail(email.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("ברוכים הבאים");
                    //String number = phone.getText().toString();
                    try {
                        password = backend.addClient(client);
                        //backend.addInvitation(IdBook, IDcurrentClient, CountBook, price, delivery.isChecked());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String massage = firstName.getText().toString()+"שם משתמש: " + name +'\n'+ "הסיסמה שלך: "+password;
                    builder.setMessage(massage);
                    builder.setPositiveButton("אישור",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
/*                    try {
                        password = backend.addClient(client);
                        sendSms();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "טעות", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }*/
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

