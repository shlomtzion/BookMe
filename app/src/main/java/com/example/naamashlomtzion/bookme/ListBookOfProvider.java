package com.example.naamashlomtzion.bookme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Backend.Backend;
import Model.Backend.BackendFactory;
import entities.Book;

import static com.example.naamashlomtzion.bookme.R.layout.row_book_provider;


public class ListBookOfProvider extends Fragment {

    Backend backend = BackendFactory.getInstance(getContext());
    long idProvider;
    private List<Book> myItemList;

    public ListBookOfProvider() {
        // Required empty public constructor
    }

    void initItemList(int numButton) throws Exception {
        myItemList = new ArrayList<Book>();
        myItemList = backend.getBookList();
    }




    // TODO: Rename and change types of parameters
/*    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;*/


/*    // TODO: Rename and change types and number of parameters
    public static ListBookOfProvider newInstance(String param1, String param2) {
        ListBookOfProvider fragment = new ListBookOfProvider();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/



/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_up_date_book,container,false);




    //void initItemByListView(int numButton, final long IDcurrentClient) throws Exception {
        try {
            initItemList(1);

        ListView listView = new ListView(getActivity());

        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(getActivity(),
                row_book_provider, myItemList) {

            @Override
            public View getView(final int position, View convertView, final ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(getContext(),
                            R.layout.row_book, null);
                }


                //image book
                //ImageView iv = (ImageView) convertView
                //  .findViewById(R.id.imageBook);

                //rating bar
                RatingBar ratingBar = (RatingBar) convertView
                        .findViewById(R.id.ratingBar);

                TextView bookNameTextView = (TextView) convertView.findViewById(R.id.text_name_book);

                TextView bookNameAuthorTextView = (TextView) convertView
                        .findViewById(R.id.text_nane_author);

                TextView bookCostTextView = (TextView) convertView
                        .findViewById(R.id.text_cost_book);

                //image button
                //RatingBar bar = new
                //ImageButton

                bookNameTextView.setText("שם הספר: " + myItemList.get(position).getName());
                bookNameAuthorTextView.setText("מחבר: " + myItemList.get(position).getAuthor());

                NumberFormat nm = NumberFormat.getNumberInstance();
                bookCostTextView.setText("מחיר הספר: " + nm.format(myItemList.get(position).getPrice()));

                // iv.setImageResource(R.drawable.cake);
                ratingBar.setRating(myItemList.get(position).getMakingStairs());

                return convertView;
            }


        };

        listView.setAdapter(adapter);
        getActivity().setContentView(listView);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
/*        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
