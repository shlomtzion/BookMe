package com.example.naamashlomtzion.bookme;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Privileging;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddBook extends Fragment {

    Backend backend = BackendFactory.getInstance(getContext());
    long idProvider;

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

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider",-1);
        }
        final View rootView=inflater.inflate(R.layout.fragment_fragment_add_book,container,false);
            ImageButton buttonAdd = (ImageButton) rootView.findViewById(R.id.add_button);
            final EditText nameBook = (EditText) rootView.findViewById(R.id.name_book);
            final EditText nameAnouther = (EditText) rootView.findViewById(R.id.name_another);
            final EditText namePublicion = (EditText) rootView.findViewById(R.id.publicion);
            //date of publicion
            final EditText priceBook = (EditText) rootView.findViewById(R.id.price);
            final EditText countBook = (EditText) rootView.findViewById(R.id.count);
            final RatingBar rating = (RatingBar) rootView.findViewById(R.id.ratingBarBook);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            Book book;

            @Override
            public void onClick(View v) {
               try{

                    Book newBook = new Book();
                    newBook.setName(nameBook.getText().toString());
                    newBook.setAuthor(nameAnouther.getText().toString());
                    newBook.setPublisher(namePublicion.getText().toString());
                    newBook.setPrice(Double.parseDouble(priceBook.getText().toString()));
                    newBook.setCount(Integer.parseInt(countBook.getText().toString()));
                    //type of book
                    newBook.setMakingStairs(rating.getNumStars());
                    long idBook = backend.addBook(newBook, idProvider, Privileging.PROVIDER);
                    Toast.makeText(getContext(), "הספר נוסף בהצלחה", Toast.LENGTH_LONG).show();
                    startActivity(getActivity().getIntent());
                /*Book_Provider book_provider=new Book_Provider();
            book_provider.setIdBook(idBook);
            book_provider.setIdProvider(idProvider);
            backend.addBookProvider(book_provider,Privileging.PROVIDER);*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "נא הזן פרטים", Toast.LENGTH_LONG).show();
                }
            }
        });
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
