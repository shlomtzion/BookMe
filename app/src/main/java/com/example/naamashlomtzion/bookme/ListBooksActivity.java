package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entities.Book;
import entities.TypeBook;

public class ListBooksActivity extends Activity {


    //private long msTime;
    //private List<String> myList;

/*    private void initList(int size)
    {
        myList = new ArrayList<String>();

        for (int i = 0; i < size; i++)
            myList.add("user " + i);
    }*/

/*    private void initByScrollView(int size)
    {
        initList(size);
        ScrollView scrole = new ScrollView(this);
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < size; i++)
        {
            Button t = new Button(this);
            t.setText(myList.get(i));
            linear.addView(t);
        }
        scrole.addView(linear);
        this.setContentView(scrole);
    }*/

/*    void initByListView(int size)
    {
        initList(size);
        ListView listView = new ListView(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.row_button, myList);

        listView.setAdapter(adapter);

        this.setContentView(listView);
    }*/

    private List<Book> myItemList;

   void initItemList(int size) {
        myItemList = new ArrayList<Book>();

/*        for (int i = 0; i < size; i++)
        {

            int day = i % 27 + 1;
            int month = i % 12;
            int year = i % 10 + 1990;

            //Calendar calender = new GregorianCalendar(year, month, day);
        }*/
       //CharSequence date = DateFormat.format("[dd/MM/yyyy]";
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       Book book = new Book("Harry Potter", "rol", "asdfghj", "02/12/1993", 45, 70, TypeBook.ADULTS);
       Book book1 = new Book("Harry Potter", "rol", "asdfgh", "02/12/1998",42,70,TypeBook.ADULTS);
       myItemList.add(book);
       myItemList.add(book1);
    }

    void initItemByListView(int size) {
        {
            initItemList(size);
            ListView listView = new ListView(this);

            ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                    R.layout.book_view, myItemList)
            {

                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    if (convertView == null)
                    {
                        convertView = View.inflate(ListBooksActivity.this,
                                R.layout.book_view,null);
                    }

                    //image book
                    ImageView iv = (ImageView) convertView
                            .findViewById(R.id.imageBook);

                    //rating bar
                    RatingBar ratingBar = (RatingBar) convertView
                            .findViewById(R.id.ratingBar);



                    TextView productNameTextView = (TextView) convertView
                            .findViewById(R.id.text_name_book);

                    TextView productNameAuthorTextView = (TextView) convertView
                            .findViewById(R.id.text_nane_author);

                    TextView productCostTextView = (TextView) convertView
                            .findViewById(R.id.text_cost_book);

                    //image button
                    ImageButton imageButton = (ImageButton) convertView
                            .findViewById(R.id.imageButton2);

/*                    TextView productionDateTextView = (TextView) convertView
                            .findViewById(R.id.dateTextView);*/

/*                    productIdTextView.setText(((Integer) myItemList.get(position)
                            .getId()).toString());*/

                    productNameTextView.setText(myItemList.get(position).getName());
                    productNameAuthorTextView.setText(myItemList.get(position).getAuthor());
                    productCostTextView.setText((int) myItemList.get(position).getPrice());
                    //productCostTextView.setText(((int) myItemList.get(position).getPrice()));

                    iv.setImageResource(R.drawable.cake);
                    ratingBar.setNumStars(myItemList.get(position).getMakingStairs());



/*                    // format date to dd/MM/yyyy
                    CharSequence date = DateFormat.format("[dd/MM/yyyy]",
                            myItemList.get(position).getDate());

                    productionDateTextView.setText(date);*/

                    return convertView;
                }

            };

            listView.setAdapter(adapter);

            this.setContentView(listView);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        //msTime = System.currentTimeMillis();

        int size = 1000;

        //initByScrollView(size);
        //initByListView(size);
        initItemByListView(size);
    }

/*    @Override
    protected void onStart()
    {
        super.onStart();
        msTime = System.currentTimeMillis() - msTime;
        Toast.makeText(this, "start after " + msTime + "ms", Toast.LENGTH_SHORT)
                .show();
    }*/



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
