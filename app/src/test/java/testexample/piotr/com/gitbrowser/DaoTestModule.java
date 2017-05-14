package testexample.piotr.com.gitbrowser;

import android.app.Application;
import android.net.http.HttpResponseCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowHttpResponseCache;
import org.robolectric.shadows.httpclient.FakeHttp;
import org.robolectric.shadows.httpclient.FakeHttpLayer;
import org.robolectric.shadows.httpclient.HttpResponseStub;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import testexample.piotr.com.gitbrowser.data.module.DaoModule;
import testexample.piotr.com.gitbrowser.util.DAO;

/**
 * Created by piotr on 10/05/17.
 *
 * provide dependencies for valley
 */

@Module
public class DaoTestModule extends DaoModule{


    private String mockResponse = "[  {\n" +
            "    \"login\": \"mojombo\",\n" +
            "    \"id\": 1,\n" +
            "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1?v=3\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/mojombo\",\n" +
            "    \"html_url\": \"https://github.com/mojombo\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  }]";

    @Provides
    @Singleton
    Cache provideTestCache()
    {
        return new NoCache();
    }

    @Provides
    @Singleton
    RequestQueue provideTestRequestQueue(Cache cache) {
        Network network = new BasicNetwork(new HttpStack() {
            @Override
            public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
                HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
                response.setEntity(new StringEntity(mockResponse));
                return response;
            }
        });
        ResponseDelivery responseDelivery = new ExecutorDelivery(Executors.newSingleThreadExecutor());
        RequestQueue queue = new RequestQueue(cache, network, 1,responseDelivery);
        queue.start();
        return queue;
    }
    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "https://www.example.com";
    }

    @Provides
    @Singleton
    DAO provideTestDAO(RequestQueue requestQueue, @Named("baseUrl") String baseUrl) {
        return new DAO(requestQueue, baseUrl);
    }




}
