package testexample.piotr.com.gitbrowser;

import android.app.ListFragment;

import javax.inject.Singleton;

import dagger.Component;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.data.module.DaoModule;

/**
 * Created by piotr on 11/05/17.
 */

@Singleton
@Component(modules = DaoTestModule.class)
public interface DaoTestComponent extends DaoComponent{
    void inject(DaoUnitTest test);
    void inject(ListFragmentPresenterTest test);
    void inject(DetailFragmentPresenterTest test);
}