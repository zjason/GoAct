package com.example.jason.goact_beta.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.jason.goact_beta.R;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView entries;
    ArrayList<Evententry> temp;

    private EvententryAdapter adapter;

    private HomeInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mListener.getlist();

        entries = (ListView) view.findViewById(R.id.eventlist);

        adapter = new EvententryAdapter(getActivity().getApplicationContext(), temp);

        entries.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (HomeInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface HomeInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        void getlist();
        //void getdetail();
    }

    public void synclist(ArrayList<Evententry> list){
        temp = list;
    }

    private class EvententryAdapter extends ArrayAdapter<Evententry> {
        private final Context context;

        private final ArrayList<Evententry> values;

        public EvententryAdapter(Context context, ArrayList<Evententry> values){
            super(context, R.layout.home_entry_layout,values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Evententry event = getItem(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.home_entry_layout, parent, false);

            Button detail = (Button) rowView.findViewById(R.id.detail);
            TextView eventname = (TextView) rowView.findViewById(R.id.eventdisplay);
            TextView type = (TextView) rowView.findViewById(R.id.typedispaly);
            TextView date = (TextView) rowView.findViewById(R.id.datedisplay);
            TextView location = (TextView) rowView.findViewById(R.id.locationdisplay);

            eventname.setText(event.eventName);
            type.setText(event.type);
            date.setText(event.month + " / " + event.day);
            location.setText(event.location);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return rowView;
        }
    }

    public static class Evententry{
        public String eventName,hostName,location,day,hours,mins,month,type;
        public float price;

        public Evententry(String eventname, String hostName, String location, String month, String day, String hours, String mins,  String type, float price){
            this.eventName = eventname;
            this.hostName = hostName;
            this.location = location;
            this.day = day;
            this.hours = hours;
            this.mins = mins;
            this.month = month;
            this.type = type;
            this.price = price;
        }
    }
}
