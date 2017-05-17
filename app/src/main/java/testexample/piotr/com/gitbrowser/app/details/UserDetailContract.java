package testexample.piotr.com.gitbrowser.app.details;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;

/**
 * Created by piotr on 17/05/17.
 */

public interface UserDetailContract {

    public interface Presenter {
        public void loadData(ModelUser user);
    }

    public interface View {
        public void publishData(ModelUser user);
    }

}
