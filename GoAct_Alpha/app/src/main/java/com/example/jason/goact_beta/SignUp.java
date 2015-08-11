package com.example.jason.goact_beta;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jason.goact_beta.Fragments.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;


public class SignUp extends Activity {
    EditText username;
    EditText email;
    EditText password;
    String myurl = "http://10.0.1.101:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        Button signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.length()!=0 && email.length()!=0 && password.length()!=0){
                    add(new Usertype(username.getText().toString(), password.getText().toString(), email.getText().toString()));
                }
            }
        });
    }

    public void add(Usertype user) {
        Log.d("vt","add");
        new EntryUploader().execute(user);
    }

    public boolean uploadToServer(Usertype user) throws IOException, JSONException {

        InputStream is = null;
        HttpURLConnection conn = (HttpURLConnection) ((new URL(MainActivity.myurl + "/thisispost").openConnection()));
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        conn.connect();

        JSONObject entry = new JSONObject();
        entry.put("username", user.username);
        entry.put("password", user.password);
        entry.put("email", user.email);
        Log.d("vt", "this is user information" + entry.toString());
        Writer wr = new OutputStreamWriter(conn.getOutputStream());

        wr.write(entry.toString());
        wr.flush();
        wr.close();
        Log.d("vt", String.valueOf(conn.getResponseCode()));
        is = conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream();
        String resp = readIt(is,2);
        Log.d("vt","http response: " + resp);
        if (conn.getResponseCode() >= 400 || resp.equals("nu")) {
            return false;

        } else {
            return true;

        }

    }

    private class EntryUploader extends AsyncTask<Usertype, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Usertype... params) {
            try {
                return uploadToServer(params[0]);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (result) {
                Toast.makeText(getApplicationContext(),"Thank you for sign up!", Toast.LENGTH_SHORT).show();
                finish();
                Log.d("vt", "posted!");

            } else {
                Toast.makeText(getApplicationContext(),"email already exist!", Toast.LENGTH_SHORT).show();
                Log.d("vt", "not posted!");
            }
        }

    }

    public class Usertype{
        public String username, password, email;

        public Usertype(String username, String password, String email){
            this.username = username;
            this.password = password;
            this.email = email;
        }

    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
