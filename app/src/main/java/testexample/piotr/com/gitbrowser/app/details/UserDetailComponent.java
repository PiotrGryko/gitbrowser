package testexample.piotr.com.gitbrowser.app.details;

import dagger.Component;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.util.CustomScope;

/**
 * Created by piotr on 17/05/17.
 */

@CustomScope
@Component(dependencies = DaoComponent.class, modules = UserDetailModule.class)
public interface UserDetailComponent {
    void inject(UserDetailFragment fragment);
}
