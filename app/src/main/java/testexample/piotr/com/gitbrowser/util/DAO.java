package testexample.piotr.com.gitbrowser.util;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;

/**
 * Created by piotr on 10/05/17.
 */

public class DAO {



    RequestQueue requestQueue;
    String baseUrl;

    public DAO(RequestQueue requestQueue, String baseUrl)
    {
        this.requestQueue=requestQueue;
        this.baseUrl=baseUrl;

    }


    public void loadUsers(Response.Listener<ModelUser[]> successListener, Response.ErrorListener errorListener) {
        //Log.d("XXX","load user "+baseUrl +" "+requestQueue);
        GsonRequest<ModelUser[]> request = new GsonRequest<ModelUser[]>(Request.Method.GET, baseUrl,ModelUser[].class, successListener, errorListener);
        requestQueue.add(request);

    }

}
