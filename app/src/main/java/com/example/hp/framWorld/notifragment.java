package com.example.hp.framWorld;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class notifragment extends Fragment {
    String newsUrl;
    View view;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LayoutInflater lf = getActivity().getLayoutInflater();
        view =  lf.inflate(R.layout.fragment_noti, container, false);
        newsUrl="https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-28&sortBy=publishedAt&apiKey=f72cdd63a2444004ae6410dedfb38139";
        new notifragment.AsyncHttpTask().execute(newsUrl);
        return view;
    }
    public class AsyncHttpTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try{
                url =new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                String response=streamtostring(urlConnection.getInputStream());
                parseResult(response);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    String streamtostring(InputStream stream) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(stream));
        String data;
        String result="";
        while ((data=bufferedReader.readLine())!=null)
        {
            result+=data;
        }
        if(null!=stream)
        {
            stream.close();
        }
        return result;
    }
    private void parseResult(String result)
    {
        JSONObject response=null;
        try {
            response= new JSONObject(result);
            JSONArray articles=response.optJSONArray("articles");

            JSONObject article=articles.optJSONObject(0);
            String title=article.optString("title");
            Log.i("T",title);
            TextView t1 = (TextView) view.findViewById(R.id.t1);
            JSONObject article1=articles.optJSONObject(1);
            String title1=article.optString("title");
            TextView t2 = (TextView) view.findViewById(R.id.t2);
            JSONObject article2=articles.optJSONObject(2);
            String title2=article.optString("title");
            TextView t3 = (TextView) view.findViewById(R.id.t3);
            JSONObject article3=articles.optJSONObject(3);
            String title3=article.optString("title");
            TextView t4 = (TextView) view.findViewById(R.id.t4);
            JSONObject article4=articles.optJSONObject(4);
            String title4=article.optString("title");
            TextView t5 = (TextView) view.findViewById(R.id.t5);
            t1.setText(title);
            t2.setText(title1);
            t3.setText(title2);
            t4.setText(title3);
            t5.setText(title4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
