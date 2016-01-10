package com.example.naamashlomtzion.bookme;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;
import entities.Privileging;


/**
 * A simple {@link Fragment} subclass.
 */
public class upDateBook extends Fragment /*implements AdapterView.OnItemClickListener*/ {

    List<String> namebook;
    //ArrayAdapter<String> adapter;
    Backend backend = BackendFactory.getInstance(getContext());
    long idProvider;



    public upDateBook() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View rootView=inflater.inflate(R.layout.fragment_up_date_book,container,false);

        Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner2);

        //spinner.setOnItemClickListener();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider",-1);
        }
        namebook=new ArrayList<String>();//nameOfBook
        try {
            namebook = backend.nameBook();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<CharSequence>adapter;
      // // adapter = ArrayAdapter.createFromResource(getActivity(),R.array.)
      //  adapter = ArrayAdapter.createFromResource(getActivity(), R.array.nav_drawer_items, android.R.layout.simple_spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      //  spinner.setAdapter(adapter);
        return rootView;
    }


  //  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        String idBook=item.substring(item.indexOf("id:"));
        Toast.makeText(parent.getContext(), "הספר שנבחר:" + item, Toast.LENGTH_LONG).show();
        Book book;
        try {
             book=backend.returnBookFromId(Long.parseLong(idBook));
            //EditText dateBook= (EditText) getView().findViewById(R.id.dateOfPopolsion);
            EditText countBook= (EditText)getView(). findViewById(R.id.count_book);
            EditText priceBook= (EditText) getView().findViewById(R.id.price_book);
            //book.setPublication(Date.parse(priceBook.getText().toString()));//
            book.setCount(Integer.parseInt(priceBook.getText().toString()));//
            book.setPrice(Double.parseDouble(priceBook.getText().toString()));

            backend.updateBook(book, idProvider, Privileging.PROVIDER);
            Toast.makeText(parent.getContext(),"הספר נמחק",Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
