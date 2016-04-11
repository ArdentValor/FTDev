package com.hart.ftdev.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex on 4/5/16.
 * Proprietary (Hart)
 */
public class Navigation
{
    private static FragmentManager manager;
    private static int viewID;

    /**
     * Required before use!
     * @param activity Activity requesting the fragment transactions
     * @param resourceID ID of the fragment view container
     */
    public static void registerActivity(AppCompatActivity activity, int resourceID)
    {
        manager = activity.getSupportFragmentManager();
        viewID = resourceID;
    }


    /**
     * Basic navigation action defaults: (bundle is null, fragment id is it's simple name, backStack entry id is it's simple name, default animations).
     * @param fragment Fragment to be loaded into view
     */
    public static void navTo(Class fragment)
    {
        navTo(fragment, null, fragment.getSimpleName(), fragment.getSimpleName(), null);
    }

    /**
     * Navigation with explicits action parameters
     * @param action object containing key parameters for a transaction
     */
    public static void navTo(NavAction action)
    {
        navTo(action.fragment, action.bundle, action.fragmentID, action.transactionID, action.animations);
    }

    /**
     * Navigation action with bundle
     * @param fragment Fragment to be loaded into view
     * @param bundle Bundle passed to fragment
     */
    public static void navTo(Class fragment, Bundle bundle)
    {
        navTo(fragment, bundle, fragment.getSimpleName(), fragment.getSimpleName(), null);
    }

    /**
     * Navigation action with fragment id
     * @param fragment Fragment to be loaded into view
     * @param fragmentID Bundle passed to fragment
     */
    public static void navTo(Class fragment, String fragmentID)
    {
        navTo(fragment, null, fragmentID, fragment.getSimpleName(), null);
    }

    /**
     * Navigation action with animation
     * @param fragment Fragment to be loaded into view
     * @param animations transition animations
     */
    public static void navTo(Class fragment, AnimationSet animations)
    {
        navTo(fragment, null, fragment.getSimpleName(), fragment.getSimpleName(), animations);
    }

    /**
     * Navigation action with bundle and animations
     * @param fragment fragment Fragment to be loaded into view
     * @param bundle Bundle passed to fragment
     * @param animations transition animations
     */
    public static void navTo(Class fragment, Bundle bundle, AnimationSet animations)
    {
        navTo(fragment, bundle, fragment.getSimpleName(), fragment.getSimpleName(), animations);
    }


    /**
     * Navigation action with bundle, and explicit fragment and backStack ids
     * @param fragment Fragment to be loaded into view
     * @param bundle Bundle passed to fragment
     * @param fragmentID String tag for fragment
     * @param transactionID BackStack entry tag for fragment transaction
     * @param animations transition animations
     */
    public static void navTo(Class fragment, Bundle bundle, String fragmentID, String transactionID, AnimationSet animations)
    {
        FragmentTransaction transaction = manager.beginTransaction();

        int backStackCount = manager.getBackStackEntryCount();

        Fragment instance = getInstance(fragment);

        if (animations != null)
        {
            transaction.setCustomAnimations(animations.enter, animations.exit, animations.popEnter, animations.popExit);
        }

        if (bundle != null)
        {
            instance.setArguments(bundle);
        }

        if (backStackCount == 0)
        {
            if (instance == null)
            {
                return;
            }

            transaction.add(viewID, instance, fragmentID);
        }
        else
        {
            transaction.replace(viewID, instance, fragmentID);
        }


        transaction.addToBackStack(transactionID);

        transaction.commit();
    }

    private static Fragment getInstance(Class fragment)
    {
        try
        {
            return  (Fragment) fragment.newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Pops the last backStack entry and performs it's operation in reverse
     * @return true if stack was popped, false of stack is empty
     */
    public static boolean pop()
    {
        if (manager.getBackStackEntryCount() == 0)
        {
            return false;
        }
        else
        {
            manager.popBackStack();
            return true;
        }
    }

    /**
     * Pop the BackStack until a specified transaction (entry) tag.
     * @param transactionTag Tag supplied at the time of transaction (default is fragment simple name)
     * @param inclusive True to include transaction referenced by the tag in the pop, false to pop everything after
     * @return true on a successful pop.
     */
    public static boolean popUntil(String transactionTag, boolean inclusive)
    {
        if (manager.getBackStackEntryCount() == 0)
        {
            return false;
        }
        else
        {
            manager.popBackStack(transactionTag, (inclusive) ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);
            return true;
        }
    }

    /**
     * Clears the entire backStack
     */
    public static void clearBackStack()
    {
        if (manager.getBackStackEntryCount() > 0)
        {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(0);
            manager.popBackStack(entry.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
