package testexample.piotr.com.gitbrowser;

import android.app.Application;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.httpclient.FakeHttp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.data.component.DaggerDaoComponent;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.util.DAO;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DaoUnitTest {

    @Inject
    DAO dao;
    private final CountDownLatch signal = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {
        DaoTestComponent testComponent = DaggerDaoTestComponent.builder().build();
        testComponent.inject(this);

    }

    @Test
    public void daoShouldReturnGsonArray() throws Exception {

        dao.loadUsers(new Response.Listener<ModelUser[]>() {
            @Override
            public void onResponse(ModelUser[] response) {
                System.out.println("response " + response);
                signal.countDown();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error  " + error.toString());
                signal.countDown();
            }
        });


        try {
            System.out.println("waiting 30secs..");
            signal.await(30, TimeUnit.SECONDS); // wait for callback
            if (signal.getCount() > 0)
                fail("Response was not delivered within timeout");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}