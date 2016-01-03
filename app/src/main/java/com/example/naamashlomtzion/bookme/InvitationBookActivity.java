package com.example.naamashlomtzion.bookme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Invitation;

public class InvitationBookActivity extends AppCompatActivity {

    Backend backend = BackendFactory.getInstance(this);

    private Book book;
    private Invitation invitation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_book);
        book = (Book) getIntent().getSerializableExtra("book_details");
        TextView bookNameTextView = (TextView) findViewById(R.id.nameBook_textView);
        bookNameTextView.setText(book.getName());
        final EditText countBook = (EditText) findViewById(R.id.count_editText);
        EditText numberCard = (EditText) findViewById(R.id.card_editText);
        EditText IDcard = (EditText) findViewById(R.id.ID_editText);
        final CheckBox deliver = (CheckBox) findViewById(R.id.checkBox);

        Button buttonOK = (Button)findViewById(R.id.bt_OK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long IdBook = book.getId_book();
                boolean Delivery = (Boolean.parseBoolean(deliver.getText().toString()));
                int CountBook = (Integer.parseInt(countBook.getText().toString()));
                long IdClient = 1;
                //Long setIdProvider;
                try {
                    double price = backend.toBuy(IdBook,CountBook,Delivery);
                   // backend.addInvitation(IdBook,IdClient,CountBook,price,Delivery);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });




    }

}
