package testexample.piotr.com.gitbrowser.app.details;

import dagger.Module;
import dagger.Provides;

/**
 * Created by piotr on 17/05/17.
 */

@Module
public class UserDetailModule {
    private final UserDetailContract.View view;

    public UserDetailModule(UserDetailContract.View view) {
        this.view = view;
    }

    @Provides
    public UserDetailContract.View provideUserDetailContractView() {
        return this.view;
    }
}
