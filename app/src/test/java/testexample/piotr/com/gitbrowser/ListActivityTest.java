package testexample.piotr.com.gitbrowser;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;
import static org.junit.Assert.*;

/**
 * Created by piotr on 12/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ListActivityTest {

    ListActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(ListActivity.class).create().get();
    }

    @Test
    public void activityShouldNotBeNull() {
        assertNotNull("activity is null!", activity);
    }

    @Test
    public void recyclerViewAdapterShouldBeSet() {
        RecyclerView rv = (RecyclerView) activity.findViewById(R.id.recycler_view);
        assertNotNull("RecyclerView is null!", rv);
        assertNotNull("Adapter is not set!", rv.getAdapter());
    }
    @Test
    public void loadDetailsShouldStartNewActivity()
    {
        activity.loadDetails(null);
        Intent startedIntent = shadowOf(activity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals("StartedActivity not match",DetailActivity.class,shadowIntent.getIntentClass());
    }
}
