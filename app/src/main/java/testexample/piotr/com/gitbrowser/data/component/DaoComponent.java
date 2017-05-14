package testexample.piotr.com.gitbrowser.data.component;

import javax.inject.Singleton;

import dagger.Component;
import testexample.piotr.com.gitbrowser.ListActivity;
import testexample.piotr.com.gitbrowser.data.module.AppModule;
import testexample.piotr.com.gitbrowser.data.module.DaoModule;
import testexample.piotr.com.gitbrowser.util.DAO;

/**
 * Created by piotr on 10/05/17.
 */

@Singleton
@Component(modules = {AppModule.class,DaoModule.class})
public interface DaoComponent {
    //tell dagger that ListActivity has access to singletons
    //dont use base classes
    //void inject(ListActivity activity);
    //DAO needs to be exposed for UsersListComponent
    DAO dao();

}
