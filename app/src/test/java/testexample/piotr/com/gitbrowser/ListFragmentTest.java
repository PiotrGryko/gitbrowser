package testexample.piotr.com.gitbrowser;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import testexample.piotr.com.gitbrowser.app.list.UsersListFragment;

import static junit.framework.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;
import static org.junit.Assert.*;

/**
 * Created by piotr on 12/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ListFragmentTest {

    private UsersListFragment usersListFragment;

    @Before
    public void setUp() {

        usersListFragment = new UsersListFragment();
        SupportFragmentTestUtil.startVisibleFragment(usersListFragment);
    }

    @Test
    public void fragmentShouldNotBeNull() {
        assertNotNull("fragment is null!", usersListFragment);
    }

    @Test
    public void recyclerViewAdapterShouldBeSet() {
        assertNotNull("fragment view is null!", usersListFragment.getView());
        RecyclerView rv = (RecyclerView) usersListFragment.getView().findViewById(R.id
                .recycler_view);
        assertNotNull("RecyclerView is null!", rv);
        assertNotNull("Adapter is not set!", rv.getAdapter());
    }


}
