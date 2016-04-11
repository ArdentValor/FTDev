package com.hart.ftdev.navigation;

import com.hart.ftdev.R;

/**
 * Created by Alex on 4/7/16.
 * Proprietary (Hart)
 */
public class TransAnim
{
    /**
     * Defualt transition animation
     * @return The default animation for fragment transactions
     */
    public static AnimationSet getDefaultAnim()
    {
        AnimationSet animations = new AnimationSet();
        animations.enter = R.anim.slide_in_left;
        animations.exit = R.anim.slide_out_left;
        animations.popEnter = R.anim.slide_in_right;
        animations.popExit = R.anim.slide_out_right;
        return animations;
    }

    public static AnimationSet getFlatAnim()
    {
        AnimationSet animations = new AnimationSet();
        animations.enter = R.anim.slide_in_left;
        animations.exit = 0;
        animations.popEnter = R.anim.slide_in_right;
        animations.popExit = 0;
        return animations;
    }
}
