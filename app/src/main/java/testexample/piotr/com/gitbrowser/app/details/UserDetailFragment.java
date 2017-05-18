package testexample.piotr.com.gitbrowser.app.details;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.App;
import testexample.piotr.com.gitbrowser.R;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.databinding.FragmentDetailBinding;
import testexample.piotr.com.gitbrowser.util.DetailsTransition;
import testexample.piotr.com.gitbrowser.util.ProfileImageBehavior;

/**
 * Created by piotr on 18/05/17.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class UserDetailFragment extends Fragment implements UserDetailContract.View{

    private FragmentDetailBinding fragmentDetailBinding;
    @Inject
    UserDetailPresenter userDetailPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUserDetailComponent.builder().daoComponent(((App) getActivity().getApplication())
                .getDaoComponent
                        ()).userDetailModule(new UserDetailModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        fragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, parent, false);
        Bundle bundle = getArguments();
        ModelUser user = (ModelUser) bundle.getSerializable("user");
        fragmentDetailBinding.setUser(user);
        userDetailPresenter.loadData(user);
        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void publishData(ModelUser user) {

        fragmentDetailBinding.tvName.setText(user.getName());
        fragmentDetailBinding.tvType.setText(user.getType());
        fragmentDetailBinding.tvLocation.setText(user.getLocation());
        fragmentDetailBinding.tvBlog.setText(user.getBlog());
        fragmentDetailBinding.tvEmail.setText(user.getEmail());
        fragmentDetailBinding.tvRepos.setText(Integer.toString(user.getPublicRepos()));
        fragmentDetailBinding.tvGists.setText(Integer.toString(user.getGists()));
        fragmentDetailBinding.tvFollowers.setText(Integer.toString(user.getFollowers()));
        fragmentDetailBinding.tvFollowing.setText(Integer.toString(user.getFollowing()));

    }


}