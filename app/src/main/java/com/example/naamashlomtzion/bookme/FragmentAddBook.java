package com.example.naamashlomtzion.bookme;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddBook extends Fragment {


    public FragmentAddBook() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_fragment_add_book,container,false);

/*        EditText nameBook= (EditText) findViewById(R.id.name_book);
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
            e.printStackTrace();}*/
        // Inflate the layout for this fragment
        return rootView;
        //return inflater.inflate(R.layout.fragment_fragment_add_book, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
