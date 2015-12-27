package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.TypeBook;

public class ListBooksActivity extends Activity {

    Backend backend = BackendFactory.getInstance(this);


    private long msTime;



    ///

     private List<Book> myItemList;

     void initItemList(int size) throws Exception {
        myItemList = new ArrayList<Book>();

         backend.setBooklist();
         myItemList = backend.getBookList();
       //CharSequence date = DateFormat.format("[dd/MM/yyyy]";
       //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       Book book = new Book("הארי פוטר", "רולינג", "asdfghj", "02/12/1993", 45, 70, TypeBook.ADULTS);
       Book book1 = new Book("הארי פוטר 1", "רולינג", "asdfgh", "02/12/1998",42,70,TypeBook.ADULTS);
       myItemList.add(book);
       myItemList.add(book1);
    }

    void initItemByListView(int size) throws Exception {
        initItemList(size);
        ListView listView = new ListView(this);

        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                R.layout.book_view, myItemList)
        {

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                //if (convertView == null) {
                //    convertView = View.inflate(ListBooksActivity.this, R.layout.row_item,null);}
                //convertView = View.inflate(ListBooksActivity.this,
                 //       R.layout.row_book,null);
                if (convertView == null)
                {
                    convertView = View.inflate(ListBooksActivity.this,
                            R.layout.row_book,null);
                }

                //image book
                //ImageView iv = (ImageView) convertView
                      //  .findViewById(R.id.imageBook);

                //rating bar
                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

                TextView productNameTextView = (TextView) convertView
                        .findViewById(R.id.text_name_book);

                TextView productNameAuthorTextView = (TextView) convertView
                        .findViewById(R.id.text_nane_author);

                //TextView productCostTextView = (TextView) convertView
                     //   .findViewById(R.id.text_cost_book);

                //image button
                ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton2);

              //TextView productionDateTextView = (TextView) convertView
                      //  .findViewById(R.id.dateTextView);

               // productIdTextView.setText(((Integer) myItemList.get(position)
                     //   .getId()).toString());
                productNameTextView.setText(myItemList.get(position).getName());
                productNameAuthorTextView.setText(myItemList.get(position).getAuthor());
                //productCostTextView.setText((int) myItemList.get(position).getPrice());
                //productCostTextView.setText(((int) myItemList.get(position).getPrice()));

               // iv.setImageResource(R.drawable.cake);
               // ratingBar.setNumStars(4);
                //ratingBar.setNumStars(myItemList.get(position).getMakingStairs())
                //ratingBar.setRating((float) 2);
                //ratingBar.setStepSize((float) 0.1);


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

        msTime = System.currentTimeMillis();

        int size = 1000;

        //initByScrollView(size);
        //initByListView(size);

        try {
            initItemByListView(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        msTime = System.currentTimeMillis() - msTime;
        Toast.makeText(this, "start after " + msTime + "ms", Toast.LENGTH_SHORT)
                .show();
    }



/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_books);

        int size = 1000;

        try {
            initItemByListView(size);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

}
