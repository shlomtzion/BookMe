package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;

public class ListBooksActivity extends Activity {

    Backend backend = BackendFactory.getInstance(this);

    private List<Book> myItemList;
    ImageButton imageButton;

    void initItemList(int numButton) throws Exception {
        myItemList = new ArrayList<Book>();

        backend.setBooklist();
        switch (numButton) {
            case 1: myItemList = backend.getBookList();
                break;
            case 2: myItemList = backend.Bestsellers();
                break;
            case 3: myItemList = backend.RecommendedBooks();
                break;
        }
    }

    void initItemByListView(int numButton, final long IDcurrentClient) throws Exception {
        initItemList(numButton);
        ListView listView = new ListView(this);

        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                R.layout.row_book, myItemList)
        {

            @Override
            public View getView(final int position, View convertView, final ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = View.inflate(ListBooksActivity.this,
                            R.layout.row_book,null);
                }


                //image book
                //ImageView iv = (ImageView) convertView
                //  .findViewById(R.id.imageBook);

                //rating bar
                RatingBar ratingBar = (RatingBar) convertView
                        .findViewById(R.id.ratingBar);

                TextView bookNameTextView = (TextView) convertView.findViewById(R.id.text_name_book);

                TextView bookNameAuthorTextView = (TextView) convertView
                        .findViewById(R.id.text_nane_author);

                TextView bookCostTextView = (TextView) convertView
                        .findViewById(R.id.text_cost_book);

                //image button
                //RatingBar bar = new
                //ImageButton

                imageButton = (ImageButton)convertView
                        .findViewById(R.id.imageButton);

                bookNameTextView.setText("שם הספר: "+myItemList.get(position).getName());
                bookNameAuthorTextView.setText("מחבר: "+myItemList.get(position).getAuthor());

                NumberFormat nm = NumberFormat.getNumberInstance();
                bookCostTextView.setText("מחיר הספר: " + nm.format(myItemList.get(position).getPrice()));

                // iv.setImageResource(R.drawable.cake);
                ratingBar.setRating(myItemList.get(position).getMakingStairs());

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentInvitationBook = new Intent(ListBooksActivity.this, InvitationBookActivity.class);
                        Book book = myItemList.get(position);
                        Toast.makeText(getApplicationContext(), book.toString(), Toast.LENGTH_LONG).show();
                        intentInvitationBook.putExtra("book_details", book);
                        intentInvitationBook.putExtra("idClient", IDcurrentClient);
                        startActivity(intentInvitationBook);
                    }
                });

                return convertView;
            }

        };


////
        listView.setAdapter(adapter);

        this.setContentView(listView);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        int numButton = (int) getIntent().getSerializableExtra("type button");
        final long IDcurrentClient = (long) getIntent().getSerializableExtra("idClient");

        try {
            initItemByListView(numButton,IDcurrentClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
