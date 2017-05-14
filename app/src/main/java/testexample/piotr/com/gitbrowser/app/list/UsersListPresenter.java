package testexample.piotr.com.gitbrowser.app.list;

import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.Arrays;
import javax.inject.Inject;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.util.DAO;

/**
 * Created by piotr on 12/05/17.
 */

public class UsersListPresenter implements UsersListContract.Presenter {

    private String TAG = UsersListPresenter.class.getName();
    DAO dao;
    UsersListContract.View view;

    @Inject
    public UsersListPresenter(DAO dao, UsersListContract.View view) {
        this.dao = dao;
        this.view = view;
    }


    @Override
    public void loadData() {

        dao.loadUsers(new Response.Listener<ModelUser[]>() {
            @Override
            public void onResponse(ModelUser[] response) {
                Log.d(TAG, Arrays.toString(response));
                view.publishData(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
