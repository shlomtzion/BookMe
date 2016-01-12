package com.example.naamashlomtzion.bookme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Invitation;

public class InvitationBookActivity extends AppCompatActivity {

    Backend backend = BackendFactory.getInstance(this);

    private boolean delivery;
    private Book book;
    private long IDcurrentClient;
    private Invitation invitation;
    private String deliver;
    private long IdBook;
    private int CountBook;
    private double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_book);
        book = (Book) getIntent().getSerializableExtra("book_details");
        IDcurrentClient = (long) getIntent().getSerializableExtra("idClient");

        TextView bookNameTextView = (TextView) findViewById(R.id.nameBook_textView);
        bookNameTextView.setText(book.getName());
        final EditText countBook = (EditText) findViewById(R.id.count_editText);
        EditText numberCard = (EditText) findViewById(R.id.card_editText);
        EditText IDcard = (EditText) findViewById(R.id.ID_editText);
        final CheckBox delivery = (CheckBox) findViewById(R.id.checkBox);

        Button buttonOK = (Button)findViewById(R.id.bt_OK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                final TextView textView = (TextView) findViewById(R.id.textView2);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("נא אשר את פרטי ההזמנה");
                    IdBook = book.getId_book();
                    CountBook = (Integer.parseInt(countBook.getText().toString()));
                    try {
                        price = backend.toBuy(IdBook, CountBook, delivery.isChecked());
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage() , Toast.LENGTH_LONG).show();
                        throw e;
                    }
                    if (delivery.isChecked()==true) {
                         deliver = "כן";
                    }
                    else deliver = "לא";
                builder.setMessage("שם הספר: " + book.getName()+'\n'+
                        "מספר עותקים: "+CountBook+'\n'+
                        "משלוח? "+deliver+'\n'+
                        "מחיר כולל: "+ price + '\n');
                    builder.setPositiveButton("אישור",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    backend.addInvitation(IdBook, IDcurrentClient, CountBook, price, delivery.isChecked());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                finish();
                            }
                        });
                builder.setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("בחר פרטים מחדש");
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //long IdBook = book.getId_book();
                //boolean Delivery = (Boolean.parseBoolean(deliver.getText().toString()));
                //int CountBook = (Integer.parseInt(countBook.getText().toString()));
                //long IdClient = 1;
                //Long setIdProvider;
               // try {
                   // double price = backend.toBuy(IdBook,CountBook,delivery.isChecked());
                    //Invitation invitation = new Invitation(IDcurrentClient,123,IdBook,CountBook,price,delivery.isChecked());
                   // backend.addInvitation(IdBook, IDcurrentClient, CountBook, price, delivery.isChecked());
                   //Toast.makeText(getApplicationContext(), invitation.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"נא הזן פרטים" , Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        });




    }
    public void click(View view) {
        final TextView textView = (TextView) findViewById(R.id.nameBook_textView);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("White",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("Your choice is 'White'.");
                    }
                });
            builder.setNegativeButton("Black", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    textView.setText("Your choice is 'Black'.");
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
}
