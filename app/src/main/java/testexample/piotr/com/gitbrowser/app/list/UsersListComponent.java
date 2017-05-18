package testexample.piotr.com.gitbrowser.app.list;

import dagger.Component;
import testexample.piotr.com.gitbrowser.MainActivity;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.util.CustomScope;

/**
 * Created by piotr on 12/05/17.
 */

@CustomScope
@Component(dependencies = DaoComponent.class,modules = UsersListModule.class)
public interface UsersListComponent {
    void inject(UsersListFragment fragment);
}
