package testexample.piotr.com.gitbrowser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.app.details.DaggerUserDetailComponent;
import testexample.piotr.com.gitbrowser.app.details.UserDetailContract;
import testexample.piotr.com.gitbrowser.app.details.UserDetailModule;
import testexample.piotr.com.gitbrowser.app.details.UserDetailPresenter;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.databinding.ActivityDetailBinding;

/**
 * Created by piotr on 12/05/17.
 */
public class DetailActivity extends AppCompatActivity implements UserDetailContract.View {

    private ActivityDetailBinding activityDetailBinding;
    private float maxToolbarOffset = 40;
    @Inject
    UserDetailPresenter userDetailPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DaggerUserDetailComponent.builder().daoComponent(((App) getApplication()).getDaoComponent
                ()).userDetailModule(new UserDetailModule(this)).build().inject(this);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        ModelUser user = (ModelUser) bundle.getSerializable("user");
        activityDetailBinding.setUser(user);

        activityDetailBinding.appbar.addOnOffsetChangedListener(new AppBarLayout
                .OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                View toolbar = activityDetailBinding.toolbar;
                View frameLayout = activityDetailBinding.fl;
                float offset = appBarLayout.getHeight() + verticalOffset;
                float percent = offset / appBarLayout.getHeight();
                frameLayout.setAlpha(1 - percent);
                if (offset < maxToolbarOffset) {
                    float alpha = offset / maxToolbarOffset;
                    toolbar.setAlpha(1 - alpha);
                } else if (offset == 0 && toolbar.getAlpha() != 1) {
                    toolbar.setAlpha(1);
                } else if (toolbar.getAlpha() != 0)
                    toolbar.setAlpha(0);
            }
        });
        userDetailPresenter.loadData(user);
    }

    @Override
    public void publishData(ModelUser user) {
        activityDetailBinding.tvName.setText(user.getName());
        activityDetailBinding.tvType.setText(user.getType());
        activityDetailBinding.tvLocation.setText(user.getLocation());
        activityDetailBinding.tvBlog.setText(user.getBlog());
        activityDetailBinding.tvEmail.setText(user.getEmail());
        activityDetailBinding.tvRepos.setText(Integer.toString(user.getPublicRepos()));
        activityDetailBinding.tvGists.setText(Integer.toString(user.getGists()));
        activityDetailBinding.tvFollowers.setText(Integer.toString(user.getFollowers()));
        activityDetailBinding.tvFollowing.setText(Integer.toString(user.getFollowing()));
    }
}
