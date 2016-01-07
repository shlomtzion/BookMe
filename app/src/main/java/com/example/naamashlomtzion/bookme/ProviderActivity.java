package com.example.naamashlomtzion.bookme;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Book_Provider;
import entities.Privileging;

public class ProviderActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener{
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
        //toolBbar
        //Toolbar toolbar=(Toolbar)findViewById(R.id.drawerlayout);
        //setSupportActionBar(toolbar);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
/*        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //final long IDcurrentClient = (long) getIntent().getSerializableExtra("idClient");
        id_provider=(long) getIntent().getSerializableExtra("id_provider");
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_Layout);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navList=(ListView)findViewById(R.id.navlist);
        ArrayList<String>navArray=new ArrayList<String>();
        /*navArray.add("׳׳—׳™׳§׳× ׳¡׳₪׳¨");
        navArray.add("׳”׳•׳¡׳₪׳× ׳¡׳₪׳¨");
        navArray.add("׳¢׳™׳“׳›׳•׳ ׳¡׳₪׳¨");
        navArray.add("׳¨׳©׳™׳׳× ׳”׳¡׳₪׳¨׳™׳ ׳©׳׳™");*/

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        NavigationView navigationView= (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//??
        ArrayAdapter<String>adpter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setAdapter(adpter);
        // navList.setOnItemClickListener();



        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);//true
        actionBar.setDisplayHomeAsUpEnabled(true);//true
        fragmentManager=getSupportFragmentManager();





        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

    @Override
    public void onBackPressed() {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_Layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


/* private void loadSelection(int i){

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }*/

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        navList.setItemChecked(id, true);//?
        Fragment fragment=null;
        switch (id){
            case 0:
                fragment=new FragmentAddBook();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,fragment);
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
                            "׳˜׳¢׳•׳×", Toast.LENGTH_LONG).show();
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
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_Layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }

    ;
}







/*
package com.example.naamashlomtzion.bookme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;

public class ProviderActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener{
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
       //toolBbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.drawerlayout);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        id_provider=getIntent().getLongExtra("id_provider",-1);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navList=(ListView)findViewById(R.id.navlist);
        ArrayList<String>navArray=new ArrayList<String>();
        */
/*navArray.add("מחיקת ספר");
        navArray.add("הוספת ספר");
        navArray.add("עידכון ספר");
        navArray.add("רשימת הספרים שלי");*//*


        //NavigationView navigationView= (NavigationView)findViewById(R.id.navlist);
        //navigationView.setNavigationItemSelectedListener(this);


        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//??
        ArrayAdapter<String>adpter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setAdapter(adpter);
       // navList.setOnItemClickListener();



        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);//true
        actionBar.setDisplayHomeAsUpEnabled(true);//true
        fragmentManager=getSupportFragmentManager();





        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

    @Override
    public void onBackPressed() {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


*/
/* private void loadSelection(int i){

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }*//*


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
            navList.setItemChecked(id, true);//?
        Fragment fragment=null;
           */
/* switch (id){
                case 0:
                    fragment=new FragmentAddBook();
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentholder,fragment);
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
                   *//*
*/
/* DeleteBook fragmentDeleteBook=new DeleteBook();
                    fragmentTransaction=fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putLong("id_provider", id_provider);
                    fragmentDeleteBook.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentholder,fragmentDeleteBook);
                    fragmentTransaction.commit();*//*
*/
/*

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
            }*//*

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


        }

        ;
    }

*/
