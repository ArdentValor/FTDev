package com.hart.ftdev.navigation;

import android.os.Bundle;

/**
 * Created by Alex on 4/6/16.
 * Proprietary (Hart)
 */
public class NavAction
{
    public Class fragment;
    public Bundle bundle;
    public String fragmentID;
    public String transactionID;
    public AnimationSet animations;

    private NavAction()
    {
        fragment = null;
        bundle = null;
        fragmentID = null;
        transactionID = null;
        animations = new AnimationSet();
        animations.enter = 0;
        animations.exit = 0;
        animations.popEnter = 0;
        animations.popExit = 0;
    }

    public static class Builder
    {
        private Class fragment;
        private Bundle bundle;
        private String fragmentID;
        private String transactionID;
        private AnimationSet animations;

        public Builder navTo(Class fragment)
        {
            this.fragment = fragment;
            return this;
        }

        public Builder withBundle(Bundle bundle)
        {
            this.bundle = bundle;
            return this;
        }

        public Builder withFragmentID(String fragmentID)
        {
            this.fragmentID = fragmentID;
            return this;
        }

        public Builder withTransactionID(String transactionID)
        {
            this.transactionID = transactionID;
            return this;
        }

        public Builder withAnimations(AnimationSet animations)
        {
            this.animations = animations;
            return this;
        }

        public NavAction buid()
        {
            if (fragment == null)
            {
                return null;
            }

            NavAction action = new NavAction();
            action.fragment = fragment;
            action.bundle = bundle;
            action.fragmentID = (fragmentID != null) ? fragmentID : fragment.getSimpleName();
            action.transactionID = (transactionID != null) ? transactionID : fragment.getSimpleName();
            action.animations = animations;

            return action;
        }

        public void go()
        {
            if (fragment == null)
            {
                return;
            }

            NavAction action = new NavAction();
            action.fragment = fragment;
            action.bundle = bundle;
            action.fragmentID = (fragmentID != null) ? fragmentID : fragment.getSimpleName();
            action.transactionID = (transactionID != null) ? transactionID : fragment.getSimpleName();
            action.animations = animations;

            Navigation.navTo(action);
        }
    }
}
