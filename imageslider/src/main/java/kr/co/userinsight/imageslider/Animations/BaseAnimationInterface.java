package kr.co.userinsight.imageslider.Animations;

import android.view.View;

/**
 * Created by rimi on 2017. 3. 20..
 * Copyright (c) 2017 UserInsight Corp.
 *
 * This interface gives you chance to inject your own animation or do something when the
 * {@link kr.co.userinsight.imageslider.Tricks.ViewPagerEx} animation (PagerTransformer) starts or ends.
 *
 *
 * There are two items you have to know. The first item is the slider you are dragging. This item
 * I call it Current Item. The second is the slider that gonna to show. I call that Next Item.
 *
 * When you start to drag the slider in front of you, onPrepareCurrentItemLeaveScreen() and
 * onPrepareNextItemShowInScreen will be called.
 *
 * When you finish drag, the onCurrentItemDisappear and onNextItemAppear will be invoked.
 *
 * You can see a demo class {@link kr.co.userinsight.imageslider.Animations.DescriptionAnimation},
 * this class gives the description text an animation.
 */

public interface BaseAnimationInterface {
    /**
     * When the current item prepare to start leaving the screen.
     *
     * @param current View
     */
    public void onPrepareCurrentItemLeaveScreen(View current);

    /**
     * The next item which will be shown in ViewPager/
     *
     * @param next View
     */
    public void onPrepareNextItemShowInScreen(View next);

    /**
     * Current item totally disappear from screen.
     *
     * @param view View
     */
    public void onCurrentItemDisappear(View view);

    /**
     * Next item totally show in screen.
     *
     * @param view View
     */
    public void onNextItemAppear(View view);
}
