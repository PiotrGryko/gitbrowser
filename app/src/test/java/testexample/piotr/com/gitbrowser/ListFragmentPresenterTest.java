package testexample.piotr.com.gitbrowser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.app.list.UsersListContract;
import testexample.piotr.com.gitbrowser.app.list.UsersListPresenter;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.util.DAO;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by piotr on 19/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ListFragmentPresenterTest {

    private UsersListPresenter presenter;
    @Inject
    DAO dao;

    @Mock
    UsersListContract.View view;
    private final CountDownLatch signal = new CountDownLatch(1);


    @Before
    public void setUp()
    {
        DaggerDaoTestComponent.builder().daoTestModule(new DaoTestModule()).build().inject(this);
        MockitoAnnotations.initMocks(this);
        presenter = new UsersListPresenter(dao,view);
    }

    @Test
    public void presenterShouldLoadData()
    {
        assertNotNull(presenter);
        presenter.loadData();
        try {
            System.out.println("waiting 30secs..");
            signal.await(30, TimeUnit.SECONDS); // wait for callback
            signal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mockito.verify(view).publishData(Mockito.any(ModelUser[].class));
    }
}
