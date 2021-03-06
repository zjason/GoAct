package com.example.jason.goact_beta.Fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.goact_beta.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEvent extends Fragment implements AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String eventName,hostName,location,day,hours,mins;
    private float price;
    private String month;
    private String type;
    Spinner monthSpin,eventTypeSpin;

    private AddEventInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEvent newInstance(String param1, String param2) {
        AddEvent fragment = new AddEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddEvent() {
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
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        monthSpin = (Spinner) view.findViewById(R.id.monthspin);
        eventTypeSpin = (Spinner) view.findViewById(R.id.typespin);

        ArrayAdapter monthAdapter =  ArrayAdapter.createFromResource(this.getActivity(), R.array.month, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.eventType, android.R.layout.simple_spinner_item);
        final EditText event = (EditText) view.findViewById(R.id.eventinput);
        final EditText host = (EditText) view.findViewById(R.id.hostinput);
        final EditText locationedit = (EditText) view.findViewById(R.id.locationinput);
        final EditText priceedit = (EditText) view.findViewById(R.id.priceinput);
        final EditText dayedit = (EditText) view.findViewById(R.id.dayinput);
        final EditText houredit = (EditText) view.findViewById(R.id.hourinput);
        final EditText minedit = (EditText) view.findViewById(R.id.mininput);
        Button bpost = (Button) view.findViewById(R.id.postbutton);

        bpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(event.length()!=0 && host.length()!=0 && locationedit.length()!=0 && priceedit.length()!=0 &&
                        dayedit.length()!=0 && houredit.length()!=0 && minedit.length()!=0) {
                    eventName = event.getText().toString();
                    hostName = host.getText().toString();
                    location = locationedit.getText().toString();
                    price = Integer.parseInt(priceedit.getText().toString());
                    day = dayedit.getText().toString();
                    hours = houredit.getText().toString();
                    mins = minedit.getText().toString();
                    Home.Evententry event = new Home.Evententry(eventName,hostName,location,month,day,hours,mins,type,price);
                    mListener.postEvent(event);

            }
                else{
                    Toast.makeText(getActivity(),"Please enter required information.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        monthSpin.setAdapter(monthAdapter);
        monthSpin.setOnItemSelectedListener(this);
        eventTypeSpin.setAdapter(typeAdapter);
        eventTypeSpin.setOnItemSelectedListener(this);
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
            mListener = (AddEventInteractionListener) activity;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == monthSpin){
            TextView temp = (TextView) view;
            month = temp.getText().toString();
            Log.d("test", month);
            Toast.makeText(getActivity(),"you selected "+month,Toast.LENGTH_SHORT).show();
        }
        if(parent == eventTypeSpin){
            TextView temp = (TextView) view;
            type = temp.getText().toString();
            Log.d("test", type);
            Toast.makeText(getActivity(),"you selected "+type,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    public interface AddEventInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        void postEvent(Home.Evententry event);

    }

}
