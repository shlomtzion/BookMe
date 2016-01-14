package com.example.naamashlomtzion.bookme;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    String[] nameBooksStrings;
    String nameBook_id;



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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider",-1);
        }

        View rootView=inflater.inflate(R.layout.fragment_up_date_book,container,false);

        final Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner2);
        Button buttonUp = (Button)rootView.findViewById(R.id.bt_update);
        //EditText countBook= (EditText)rootView.findViewById(R.id.count_book);
        //EditText priceBook= (EditText)rootView.findViewById(R.id.price_book);


        nameBooksStrings = new String[1];
        nameBooksStrings[0] = "הרשימה ריקה";
        try {
            namebook = backend.nameBook(idProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameBooksStrings = new String[namebook.size()];
        int i = 0;
        for (String s : namebook) {
            nameBooksStrings[i] = s;
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,nameBooksStrings);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameBook_id = spinner.getSelectedItem().toString();
                //Toast.makeText(getContext(), "הספר שנבחר:" + nameBook, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonUp.setOnClickListener(new View.OnClickListener() {
            Book book;
            @Override
            public void onClick(View v) {
                try {
                EditText countBook= (EditText)getView(). findViewById(R.id.count_book);
                EditText priceBook= (EditText) getView().findViewById(R.id.price_book);

                    String idBook = backend.returnIdFromName(nameBook_id);
                    long i = Long.parseLong(idBook);
                    book=backend.returnBookFromId(i);
                    book.setCount(Integer.parseInt(countBook.getText().toString()));
                    book.setPrice(Double.parseDouble(priceBook.getText().toString()));
                    backend.updateBook(book, idProvider, Privileging.PROVIDER);
                    Toast.makeText(getContext(), "הספר "+book.getName()+" עודכן" , Toast.LENGTH_LONG).show();
                    startActivity(getActivity().getIntent());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "נא הזן פרטים" , Toast.LENGTH_LONG).show();
                }
            }

        });

        return rootView;
    }
}
