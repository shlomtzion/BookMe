package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.TypeBook;

public class AllBookActivity extends Activity {


    /*Backend backend = BackendFactory.getInstance(this);
    private GridView gridView;
    List<Book> book_L = new ArrayList<Book>();

    //ArrayList<String> strings = new ArrayList<String>();
*//*    ArrayList<Book> func() {
*//**//*        int x = 0;
        for (; x < 30; x++) {
            strings.add("book" + x);
        }*//**//*
        ArrayList<Book> book_L = null;
        try {
            backend.setBooklist();

            book_L = backend.getBookList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book_L;
    }*//*





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_book);
            GridView gridView=(GridView)findViewById(R.id.gv_allBook);

            final mylistAdapter adapter = new mylistAdapter();



            //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_gallery_item,this.func()));
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

*//*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*//*
        //try {


            //ArrayAdapter adapter = new ArrayAdapter(this, )




          *//*  for (Book bookitem: book_L) {
                adapter.add(bookitem.getName());
            }*//*

            //setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, this.func()));

            gridView.setAdapter(adapter);
            //this.setContentView(gridView);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }



    }
    private class mylistAdapter extends BaseAdapter {

        public  mylistAdapter(){
            super();
            try {
                backend.setBooklist();
                book_L = backend.getBookList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public int getCount() {
            try{
                return book_L.size();
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            try{
                return book_L.get(position);
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                View bookView = getLayoutInflater().inflate(R.layout.book_view,parent,false);
*//*                View bookView = convertView;
                if (bookView == null) {
                    bookView = getLayoutInflater().inflate(R.layout.book_view,parent,false);
                    //bookView = getLayoutInflater().inflate(R.layout.book_view, parent, false);
                }*//*
                Book currentBook = book_L.get(position);
                //leidkun hatmuna avur kol sefer
                ImageView imageView = (ImageView) bookView.findViewById(R.id.imageBook);
                imageView.setImageResource(R.drawable.cake);
                return bookView;
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "):", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return null;
            }
        }
    }*/
    Backend backend = BackendFactory.getInstance(this);
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        listView = (ListView) findViewById(R.id.listView);

        //Backend backend = BackendFactory.getInstance(this);
        final imageButtonAdapter adapter = new imageButtonAdapter();
        //EditText filterBox = (EditText) findViewById(com.example.haimn.shopapp.R.id.filterBox);
        //filterBox.addTextChangedListener(new TextWatcher() {
           /* @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        listView = (ListView) findViewById(R.id.listView);
        try {
            //productListView.setAdapter(new ArrayAdapter<Product>(this,R.layout.row_button,backend.getProductList()));
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book)parent.getItemAtPosition(position);
                Intent intent = new Intent(AllBookActivity.this, InvitationBookActivity.class);
                intent.putExtra("book_details", (Serializable) book);
                startActivity(intent);
            }
        });
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

    private class imageButtonAdapter extends BaseAdapter implements Filterable {
        public imageButtonAdapter() {
            super();
            try {

                //backend.setBooklist();
                bookList = backend.getBookList();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Book book = new Book("Harry Potter", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70, TypeBook.ADULTS);
                Book book1 = new Book("Harry Potter1", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70, TypeBook.ADULTS);
                Book book2 = new Book("Harry Potter2", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70, TypeBook.ADULTS);
                Book book3 = new Book("Harry Potter3", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70, TypeBook.ADULTS);
                bookList.add(book);
                bookList.add(book1);
                bookList.add(book2);
                bookList.add(book3);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Book> bookList;

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    try {
                        FilterResults fr = new FilterResults();
                        ArrayList<Book> filteredArray = new ArrayList<>();
                        ArrayList<Book> bookList = backend.getBookList();
                        for (Book book : bookList) {
                            if (book.getName().contains(constraint) || constraint.length() == 0)
                                filteredArray.add(book);
                        }
                        fr.count = filteredArray.size();
                        fr.values = filteredArray;
                        return fr;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null)
                    {bookList = (ArrayList<Book>) results.values;
                        notifyDataSetChanged();}
                }
            };
            return filter;
        }

        @Override
        public int getCount() {
            try {
                return bookList.size();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            try {
                return bookList.get(position);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                //LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //View row = inflater.inflate(R.layout.button_with_image, parent , false);
                View row = getLayoutInflater().inflate(R.layout.book_view, parent , false);
                TextView tv = (TextView) row.findViewById(R.id.text_cost_book);
                ImageView iv = (ImageView) row.findViewById(R.id.imageBook);
                iv.setImageResource(R.drawable.cake);
                int imageID = getResources().getIdentifier("p_" + (position + 1),"drawable",getPackageName());
                if (imageID != 0)
                    iv.setImageResource(imageID);
                tv.setText(bookList.get(position).getName());
                return row;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
