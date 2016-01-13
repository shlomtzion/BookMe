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
public class DeleteBook extends Fragment/* implements AdapterView.OnItemClickListener */{
   //Spinner spinner;

    Backend backend = BackendFactory.getInstance(getContext());
    long idProvider;




    public DeleteBook() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"הספר שנבחר: "+item,Toast.LENGTH_LONG).show();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //super.onCreate(savedInstanceState);
        View rootView=inflater.inflate(R.layout.fragment_delete_book,container,false);
        Button button = (Button)rootView.findViewById(R.id.delete_book);
        Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider",-1);
        }




        //List<String> namebook=new ArrayList<String>();//nameOfBook
        String[] nameBooksStrings;
        nameBooksStrings = new String[1];
        nameBooksStrings[0]="הרשימה ריקה";
        try {

            List<String> namebook=backend.nameBook();
            nameBooksStrings = new String[namebook.size()];
            int i=0;
            for(String s:namebook)
            {
                nameBooksStrings[i]=s;
                i++;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String>adapter;
        //adapter=ArrayAdapter.createFromResource(getContext(),nameBooksStrings,android.R.layout.simple_spinner_item);
        adapter= new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,nameBooksStrings);
            //adapter = new ArrayAdapter<String>(this,R.layout.row_name_book, namebook);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                String idBook = item.substring(item.indexOf("id:"));
                //Toast.makeText(getContext(), "הספר שנבחר:" + item, Toast.LENGTH_LONG).show();
                try {
                    //backend.deleteBook(Long.parseLong(idBook),idProvider, Privileging.PROVIDER);
                    //Toast.makeText(parent.getContext(),,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(parent.getContext(), "הספר נמחק", Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
