package testexample.piotr.com.gitbrowser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.databinding.ActivityDetailBinding;

/**
 * Created by piotr on 12/05/17.
 */

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding;
    private float maxToolbarOffset = 40;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        ModelUser user = (ModelUser) bundle.getSerializable("user");
        activityDetailBinding.setUser(user);
        activityDetailBinding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                View toolbar = activityDetailBinding.toolbar;
                View frameLayout = activityDetailBinding.fl;

                float offset = appBarLayout.getHeight() + verticalOffset;
                float percent = offset/appBarLayout.getHeight();

                frameLayout.setAlpha(1-percent);
                if (offset < maxToolbarOffset) {
                    float alpha = offset / maxToolbarOffset;
                    toolbar.setAlpha(1 - alpha);
                } else if (offset == 0 && toolbar.getAlpha() != 1) {
                    toolbar.setAlpha(1);
                } else if (toolbar.getAlpha() != 0)
                    toolbar.setAlpha(0);


            }
        });

    }


}
