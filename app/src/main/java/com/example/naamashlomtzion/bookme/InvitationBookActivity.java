package com.example.naamashlomtzion.bookme;

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
                long IdBook = book.getId_book();
                //boolean Delivery = (Boolean.parseBoolean(deliver.getText().toString()));
                int CountBook = (Integer.parseInt(countBook.getText().toString()));
                //long IdClient = 1;
                //Long setIdProvider;
                try {
                    double price = backend.toBuy(IdBook,CountBook,delivery.isChecked());
                    Invitation invitation = new Invitation(IDcurrentClient,123,IdBook,CountBook,price,delivery.isChecked());
                    backend.addInvitation(IdBook, IDcurrentClient, CountBook, price, delivery.isChecked());
                    Toast.makeText(getApplicationContext(), invitation.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

       /* Button cancel = (Button) findViewById(R.id.cancel_recommendation);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment cancel_dialog = new DialogFragment() {
                    @NonNull
                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(R.string.dialog_cancel_review)
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                    }
                                });
                        // Create the AlertDialog object and return it
                        return builder.create();
                    }
                };
                cancel_dialog.show(getSupportFragmentManager(), "Cancel the review?");
            }
        });*/




    }

}
