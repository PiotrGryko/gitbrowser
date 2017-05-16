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

public class ProfileImageBehaviour extends CoordinatorLayout.Behavior<ImageView> {

    float maxOffset = 0;
    float startCenterX = 0;
    float mStartWidth = 0;
    float mStartHeight = 0;

    float minWidth = 0;
    float minHeight = 0;

    float targetY =0;

    float imageRatio =0;

    public ProfileImageBehaviour(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        minHeight = context.getResources().getDimension(R.dimen.profile_height);
        minWidth = context.getResources().getDimension(R.dimen.profile_widht);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {

        boolean flag = dependency instanceof Toolbar;

        if (flag) {

            if (maxOffset == 0) {
                maxOffset = dependency.getY();
                //Log.d("XXX", "depends on " + dependency.getY());
            }
            if (startCenterX == 0)
                startCenterX = parent.getWidth() / 2;
            if (mStartHeight == 0)
                mStartHeight = child.getHeight();
            if (mStartWidth == 0)
                mStartWidth = child.getWidth();

            if(imageRatio==0 && child.getHeight()>0 && child.getWidth()>0)
                imageRatio = (float)child.getWidth()/(float)child.getHeight();
            //if(targetY<=0)
            //    targetY = (dependency.getHeight()-minHeight)/2;
        }

        return flag;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {


        float offset = dependency.getY() / maxOffset;
        float currentCenterX = startCenterX - (startCenterX * offset);
        //child.setAlpha(offset);



        float width = mStartWidth * offset;
        float height = mStartHeight * offset;

        if(height<dependency.getHeight())
        {
            height = dependency.getHeight();
            //width = height*imageRatio;
        }
        if(width<height)
            width=height;

        child.setY(targetY*(1-offset));

        //child.setX(-currentCenterX);
        //child.setY(dependency.getY());


        // child.setScaleX(offset);
        // child.setScaleY(offset);

        //if (height>dependency.getHeight()) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            params.width = (int) width;
            params.height = (int) height;
            child.setLayoutParams(params);
        //}
        //Log.d("XXX", "positon " + dependency.getHeight()+" "+minHeight+" "+targetY+" "+imageRatio) ;
        return true;
    }
}
