package testexample.piotr.com.gitbrowser.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import testexample.piotr.com.gitbrowser.R;

/**
 * Created by piotr on 17/05/17.
 */

public class ProfileTitleBehavior extends CoordinatorLayout.Behavior<TextView> {
    float maxOffset = 0;

    public ProfileTitleBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {

        boolean flag = dependency instanceof Toolbar;

        if (flag) {
            if (maxOffset == 0) maxOffset = dependency.getY();
        }

        return flag;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {

        float offset = dependency.getY() / maxOffset;
        float currentY = dependency.getY();
        float targetY = (dependency.getHeight() - child.getHeight()) / 2;
        float targetX = dependency.getHeight();

        if (currentY < targetY)
            currentY = targetY;

        child.setX((1 - offset) * targetX);
        child.setY(currentY);

        return true;
    }
}
