package kr.co.userinsight.imageslider.Animations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import kr.co.userinsight.imageslider.R;


/**
 * Created by rimi on 2017. 3. 20..
 * Copyright (c) 2017 UserInsight Corp.
 *
 * A demo class to show how to use {@link kr.co.userinsight.imageslider.Animations.BaseAnimationInterface}
 * to make  your custom animation in {@link kr.co.userinsight.imageslider.Tricks.ViewPagerEx.PageTransformer} action.
 */

public class DescriptionAnimation implements BaseAnimationInterface {
    @Override
    public void onPrepareCurrentItemLeaveScreen(View current) {
        View descriptionLayout = current.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            current.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * When next item is coming to show, let's hide the description layout.
     *
     * @param next View
     */
    @Override
    public void onPrepareNextItemShowInScreen(View next) {
        View descriptionLayout = next.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            next.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onCurrentItemDisappear(View view) {

    }

    /**
     * When next item show in ViewPagerEx, let's make an animation to show the
     * description layout.
     *
     * @param view View
     */
    @Override
    public void onNextItemAppear(View view) {

        View descriptionLayout = view.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            float layoutY = View.Y.get(descriptionLayout);
            view.findViewById(R.id.description_layout).setVisibility(View.VISIBLE);
            ValueAnimator animator = ObjectAnimator.ofFloat(
                    descriptionLayout, "y", layoutY + descriptionLayout.getHeight(),
                    layoutY).setDuration(500);
            animator.start();
        }

    }
}
