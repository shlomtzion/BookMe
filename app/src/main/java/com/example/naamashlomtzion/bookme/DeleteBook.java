package com.example.naamashlomtzion.bookme;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
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
        Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idProvider = bundle.getLong("id_provider",-1);
        }


       // spinner.setOnItemClickListener(this);??
        List<String> namebook=new ArrayList<String>();//nameOfBook
        try {
            namebook=backend.nameBook();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            ArrayAdapter<CharSequence>adapter;
       // adapter=ArrayAdapter.createFromResource(getActivity(),R.array.nav_drawer_items, android.R.layout.simple_spinner_item);
            //adapter = new ArrayAdapter<String>(this,R.layout.row_name_book, namebook);
          //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           // spinner.setAdapter(adapter);


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
