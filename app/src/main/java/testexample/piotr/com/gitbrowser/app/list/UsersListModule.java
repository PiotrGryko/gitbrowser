package testexample.piotr.com.gitbrowser.app.list;

import dagger.Module;
import dagger.Provides;

/**
 * Created by piotr on 12/05/17.
 */

@Module
public class UsersListModule {
    private final UsersListContract.View mView;
    public UsersListModule(UsersListContract.View mView)
    {
        this.mView=mView;
    }

    @Provides
    UsersListContract.View provideListContractView()
    {
        return mView;
    }
}
