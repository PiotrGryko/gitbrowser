package testexample.piotr.com.gitbrowser.app.list;

import android.widget.ImageView;

import java.util.ArrayList;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;

/**
 * Created by piotr on 12/05/17.
 */

public interface UsersListContract {

    interface View {
        void publishData(ModelUser[] users);
        void loadDetails(ModelUser user, ImageView image);

    }

    interface Presenter {
        void loadData();
    }

}
