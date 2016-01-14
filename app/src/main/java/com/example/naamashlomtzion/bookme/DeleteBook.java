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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Privileging;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteBook extends Fragment implements AdapterView.OnItemClickListener{
   //Spinner spinner;

    Backend backend = BackendFactory.getInstance(getContext());
    long idProvider;

    String[] nameBooksStrings;
    String nameBook_id;
    List<String> namebook;




    public DeleteBook() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_delete_book, container, false);
        Button buttonDel = (Button) rootView.findViewById(R.id.delete_book);
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider", -1);
        }

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

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String idBook = backend.returnIdFromName(nameBook_id);
                    long i = Long.parseLong(idBook);
                    //Toast.makeText(getContext(),idBook, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext(),idProv, Toast.LENGTH_LONG).show();
                    backend.deleteBook(i,idProvider,Privileging.PROVIDER);
                    Toast.makeText(getContext(),"הספר "+ nameBook_id + " נמחק" , Toast.LENGTH_LONG).show();
                    startActivity(getActivity().getIntent());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),e.getMessage()+ " :(" , Toast.LENGTH_LONG).show();
                }
            }

        });

        return rootView;



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


   // @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        String idBook=item.substring(item.indexOf("id:"));
        Toast.makeText(parent.getContext(),"הספר שנבחר:"+item,Toast.LENGTH_LONG).show();
        try {
            backend.deleteBook(Long.parseLong(idBook),idProvider, Privileging.PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(parent.getContext(),"הספר נמחק",Toast.LENGTH_LONG).show();


    }
}
