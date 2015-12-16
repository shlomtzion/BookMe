package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;

public class AllBookActivity extends Activity {


    Backend backend = BackendFactory.getInstance(this);

    //ArrayList<String> strings = new ArrayList<String>();
    ArrayList<Book> func() {
/*        int x = 0;
        for (; x < 30; x++) {
            strings.add("book" + x);
        }*/
        ArrayList<Book> book_L = null;
        try {
            backend.setBooklist();

            book_L = backend.getBookList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book_L;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_book);
            //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_gallery_item,this.func()));
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //try {


            //ArrayAdapter adapter = new ArrayAdapter(this, )
        ArrayAdapter<Book> adapter = new mylistAdapter();
        GridView gridView=(GridView)findViewById(R.id.gv_allBook);



          /*  for (Book bookitem: book_L) {
                adapter.add(bookitem.getName());
            }*/

            //setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, this.func()));

            gridView.setAdapter(adapter);
            //this.setContentView(gridView);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }



    }
    private class mylistAdapter extends ArrayAdapter<Book>{
        public  mylistAdapter(){
            super(AllBookActivity.this,R.layout.book_view,func());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {

                View bookView = convertView;
                if (bookView == null) {
                    bookView = getLayoutInflater().inflate(R.layout.book_view,parent,false);
                    //bookView = getLayoutInflater().inflate(R.layout.book_view, parent, false);
                }
                Book currentBook = func().get(position);
                //leidkun hatmuna avur kol sefer
                ImageView imageView = (ImageView)bookView.findViewById(R.id.imageBook);
                //imageView.setImageResource(R.layout.imageBook);
                return bookView;
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "):", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return null;
            }
        }
    }

}
