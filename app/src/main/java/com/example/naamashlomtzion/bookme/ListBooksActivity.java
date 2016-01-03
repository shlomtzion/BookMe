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
import entities.TypeBook;

public class ListBooksActivity extends Activity {

    Backend backend = BackendFactory.getInstance(this);

     private List<Book> myItemList;
    ImageButton imageButton;

     void initItemList(int size) throws Exception {
        myItemList = new ArrayList<Book>();

         backend.setBooklist();
         myItemList = backend.getBookList();
       //CharSequence date = DateFormat.format("[dd/MM/yyyy]";
       //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       Book book = new Book("הארי פוטר", "רולינג", "asdfghj", "02/12/1993", 45.4, 70, TypeBook.ADULTS);
       Book book1 = new Book("הארי פוטר 1", "רולינג", "asdfgh", "02/12/1998",42.9,70,TypeBook.ADULTS);
       myItemList.add(book);
       myItemList.add(book1);
    }

    void initItemByListView(int size) throws Exception {
        initItemList(size);
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
                bookCostTextView.setText("המחיר: " + nm.format(myItemList.get(position).getPrice()));

               // iv.setImageResource(R.drawable.cake);
                ratingBar.setRating(myItemList.get(position).getMakingStairs());

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentInvitationBook = new Intent(ListBooksActivity.this, InvitationBookActivity.class);
                        Book book = myItemList.get(position);
                        Toast.makeText(getApplicationContext(), book.toString(), Toast.LENGTH_LONG).show();
                        intentInvitationBook.putExtra("book_details",  book);
                        startActivity(intentInvitationBook);
                    }
                });





                // format date to dd/MM/yyyy
                //CharSequence date = DateFormat.format("[dd/MM/yyyy]",
                      //  myItemList.get(position).getDate());

                //productionDateTextView.setText(date);

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

        int size = 1000;

        //initByScrollView(size);
        //initByListView(size);

        try {
            initItemByListView(size);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
