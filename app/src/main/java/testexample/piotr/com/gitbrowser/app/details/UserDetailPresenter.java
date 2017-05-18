package testexample.piotr.com.gitbrowser.app.details;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.util.DAO;

/**
 * Created by piotr on 17/05/17.
 */

public class UserDetailPresenter implements UserDetailContract.Presenter {

    DAO dao;
    UserDetailContract.View contractView;

    @Inject
    public UserDetailPresenter(DAO dao, UserDetailContract.View view) {
        this.dao = dao;
        this.contractView = view;
    }

    @Override
    public void loadData(ModelUser user) {
        dao.loadUserDetails(user, new Response.Listener<ModelUser>() {
            @Override
            public void onResponse(ModelUser response) {
                contractView.publishData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }
}
