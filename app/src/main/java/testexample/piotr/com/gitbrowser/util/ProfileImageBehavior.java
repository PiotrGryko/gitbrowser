package testexample.piotr.com.gitbrowser.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import testexample.piotr.com.gitbrowser.R;

/**
 * Created by piotr on 16/05/17.
 */
public class ProfileImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    float maxOffset = 0;
    float startCenterX = 0;
    float mStartWidth = 0;
    float mStartHeight = 0;
    float minWidth = 0;
    float minHeight = 0;
    float targetY = 0;
    float imageRatio = 0;

    public ProfileImageBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public ProfileImageBehavior(Context context) {
        super(context, null);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        boolean flag = dependency instanceof Toolbar;
        if (flag) {
            if (maxOffset == 0) maxOffset = dependency.getY();
            if (startCenterX == 0) startCenterX = parent.getWidth() / 2;
            if (mStartHeight == 0) mStartHeight =  parent.getContext().getResources().getDimension(R.dimen.profile_image_height);
            if (mStartWidth == 0) mStartWidth = parent.getWidth();
            if (imageRatio == 0 && child.getHeight() > 0 && child.getWidth() > 0)
                imageRatio = (float) child.getWidth() / (float) child.getHeight();
        }
        return flag;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View
            dependency) {
        float offset = dependency.getY() / maxOffset;
        float width = mStartWidth * offset;
        float height = mStartHeight * offset;
        if (height < dependency.getHeight()) height = dependency.getHeight();
        if (width < height) width = height;
        child.setY(targetY * (1 - offset));
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child
                .getLayoutParams();
        params.width = (int) width;
        params.height = (int) height;
        child.setLayoutParams(params);

        return true;
    }
}
