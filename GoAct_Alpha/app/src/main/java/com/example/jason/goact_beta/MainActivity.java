package com.example.jason.goact_beta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.jason.goact_beta.Fragments.AddEvent;
import com.example.jason.goact_beta.Fragments.Home;
import com.example.jason.goact_beta.Fragments.Mapfrag;
import com.example.jason.goact_beta.Fragments.Setting;
import com.example.jason.goact_beta.Fragments.User;


public class MainActivity extends Activity implements View.OnClickListener{

    private static final String TAG_HOME = "Home";
    private static final String TAG_MAP = "Map";
    private static final String TAG_ADDEVENT = "AddEvent";
    private static final String TAG_USER= "User";
    private static final String TAG_SETTING = "Setting";
    private ImageButton bhome,bmap,baddevent,bsetting,buser;
    private Fragment home,map,addevent,user,setting;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = this.getFragmentManager();

        bhome = (ImageButton) findViewById(R.id.home);
        bmap = (ImageButton) findViewById(R.id.map);
        baddevent = (ImageButton) findViewById(R.id.post);
        bsetting = (ImageButton) findViewById(R.id.settting);
        buser = (ImageButton) findViewById(R.id.user);

        //bhome.setOnClickListener(this);
        //bmap.setOnClickListener(this);
        //baddevent.setOnClickListener(this);
        //bsetting.setOnClickListener(this);
        //buser.setOnClickListener(this);

        initHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Intent Login = new Intent(MainActivity.this, Login.class);
        //startActivity(Login);
        //finish();
    }

    private void initHomeFragment(){

        Log.d("fragtest", "1");
        if(fm.findFragmentByTag(TAG_HOME) == null){
            Log.d("fragtest", "2");
            home = Home.newInstance("a", "n");
            Log.d("fragtest", "3");
            fm.beginTransaction().add(R.id.frag, home, TAG_HOME).commit();
            Log.d("fragtest", "4");
        }
        Log.d("fragtest","5");
    }

    @Override
    public void onClick(View v){
        if(v == bhome){
            removeFragments();
            home = fm.findFragmentByTag(TAG_HOME);
            if(home == null){
                home = Home.newInstance("a","n");
                fm.beginTransaction().add(R.id.frag,home,TAG_HOME).commit();
            }
        }
        if(v == bmap){
            removeFragments();
            map = fm.findFragmentByTag(TAG_MAP);
            if(map == null){
                map = Mapfrag.newInstance("a", "n");
                fm.beginTransaction().add(R.id.frag,map,TAG_MAP).commit();
            }
        }
        if(v == baddevent){
            removeFragments();
            addevent = fm.findFragmentByTag(TAG_ADDEVENT);
            if(addevent == null){
                addevent = AddEvent.newInstance("a", "n");
                fm.beginTransaction().add(R.id.frag,addevent,TAG_ADDEVENT).commit();
            }
        }
        if(v == buser){
            removeFragments();
            user = fm.findFragmentByTag(TAG_USER);
            if(user == null){
                user = User.newInstance("a", "n");
                fm.beginTransaction().add(R.id.frag,user,TAG_USER).commit();
            }
        }
        if(v == bsetting){
            removeFragments();
            setting = fm.findFragmentByTag(TAG_SETTING);
            if(setting == null){
                setting = Setting.newInstance("a", "n");
                fm.beginTransaction().add(R.id.frag,setting,TAG_SETTING).commit();
            }
        }


    }

    //helper method that removes the current fragment
    //if you don't remove current fragment and add a new one
    // the fragments will overlap
    private void removeFragments() {
        if (home != null) {
            fm.beginTransaction().remove(home).commit();
        }
        if (map != null){
            fm.beginTransaction().remove(map).commit();
        }
        if (addevent != null) {
            fm.beginTransaction().remove(addevent).commit();
        }
        if (user != null) {
            fm.beginTransaction().remove(user).commit();
        }
        if (setting != null) {
            fm.beginTransaction().remove(setting).commit();
        }
    }
}
