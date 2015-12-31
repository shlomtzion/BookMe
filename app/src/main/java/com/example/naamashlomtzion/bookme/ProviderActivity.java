package com.example.naamashlomtzion.bookme;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Book_Provider;
import entities.Privileging;

public class ProviderActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    Backend backend = BackendFactory.getInstance(this);
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private int mNavItemId;
    private ListView navList;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private android.support.v4.app.FragmentManager fragmentManager;
    //private FragmentManager fragmentManager;
    private long id_provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        id_provider=getIntent().getLongExtra("id_provider",-1);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        navList=(ListView)findViewById(R.id.navlist);
        ArrayList<String>navArray=new ArrayList<String>();
        navArray.add("מחיקת ספר");
        navArray.add("הוספת ספר");
        navArray.add("עידכון ספר");
        navArray.add("רשימת הספרים שלי");
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String>adpter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setAdapter(adpter);
        navList.setOnItemClickListener(this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);//true
        actionBar.setDisplayHomeAsUpEnabled(true);//true
        fragmentManager=getSupportFragmentManager();

        loadSelection(0);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void loadSelection(int i){
        navList.setItemChecked(i,true);
        switch (i){
            case 0:
                FragmentAddBook fragmentAddBook=new FragmentAddBook();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, fragmentAddBook);
                fragmentTransaction.commit();
                EditText nameBook= (EditText) findViewById(R.id.name_book);
                EditText nameAnouther= (EditText) findViewById(R.id.name_another);
                EditText namePublicion= (EditText) findViewById(R.id.publicion);
                //date of publicion
                EditText priceBook= (EditText) findViewById(R.id.price);
                EditText countBook= (EditText) findViewById(R.id.count);
                RatingBar rating=(RatingBar)findViewById(R.id.ratingBarBook);
                Book newBook=new Book();
                newBook.setName(nameBook.getText().toString());
                newBook.setAuthor(nameAnouther.getText().toString());
                newBook.setPublisher(namePublicion.getText().toString());
                newBook.setPrice(Double.parseDouble(priceBook.getText().toString()));
                newBook.setCount(Integer.parseInt( countBook.getText().toString()));
                //type of book
                newBook.setMakingStairs(rating.getNumStars());
                try {
                   long idBook= backend.addBook(newBook, Privileging.PROVIDER);
                    Book_Provider book_provider=new Book_Provider();
                    book_provider.setIdBook(idBook);
                    book_provider.setIdProvider(id_provider);
                    backend.addBookProvider(book_provider,Privileging.PROVIDER);

                } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "טעות", Toast.LENGTH_LONG).show();
                e.printStackTrace();}



                break;
            case 1:
                DeleteBook fragmentDeleteBook=new DeleteBook();
                fragmentTransaction=fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putLong("id_provider", id_provider);
                fragmentDeleteBook.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentholder,fragmentDeleteBook);
                fragmentTransaction.commit();

                break;
            case 2:
                upDateBook fragmentupdatebook=new upDateBook();
                fragmentTransaction=fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putLong("id_provider", id_provider);
                fragmentupdatebook.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentholder,fragmentupdatebook);
                fragmentTransaction.commit();
                break;
            case 3:
                ListBookOfProvider listBookOfProvider =new ListBookOfProvider();
                fragmentTransaction.replace(R.id.fragmentholder,listBookOfProvider);
                fragmentTransaction.commit();

                break;
        }


    }
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
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
        else if (id==android.R.id.home){
            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }else{
                drawerLayout.openDrawer(navList);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(navList);
    }
}
