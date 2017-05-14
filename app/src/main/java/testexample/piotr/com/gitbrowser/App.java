package testexample.piotr.com.gitbrowser;

import android.app.Application;

import testexample.piotr.com.gitbrowser.data.component.DaggerDaoComponent;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.data.module.AppModule;
import testexample.piotr.com.gitbrowser.data.module.DaoModule;


/**
 * Created by piotr on 10/05/17.
 */

public class App extends Application {

    DaoComponent daoComponent;
    public void onCreate()
    {
        super.onCreate();
        daoComponent = DaggerDaoComponent.builder().appModule(new AppModule(this)).daoModule(new DaoModule()).build();
    }


    public DaoComponent getDaoComponent()
    {
        return daoComponent;
    }

}
