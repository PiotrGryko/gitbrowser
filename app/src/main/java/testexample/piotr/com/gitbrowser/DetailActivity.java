package testexample.piotr.com.gitbrowser;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.databinding.ActivityDetailBinding;
import testexample.piotr.com.gitbrowser.databinding.ActivityListBinding;

/**
 * Created by piotr on 12/05/17.
 */

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding;
    private float maxOffset = 40;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        ModelUser user = (ModelUser) bundle.getSerializable("user");
        activityDetailBinding.setUser(user);
        activityDetailBinding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                float offset = appBarLayout.getHeight() + verticalOffset;
                View toolbar = activityDetailBinding.toolbar;
                if (offset < maxOffset) {
                    float alpha = offset / maxOffset;
                    toolbar.setAlpha(1 - alpha);
                } else if (offset == 0 && toolbar.getAlpha() != 1) {
                    toolbar.setAlpha(1);
                } else if (toolbar.getAlpha() != 0)
                    toolbar.setAlpha(0);


            }
        });
        /*
        String url = bundle.getString("url");
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into((ImageView) findViewById(R.id.iv));
        */
    }


}
