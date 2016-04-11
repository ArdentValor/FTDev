package com.hart.ftdev.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Alex on 4/4/16.
 * Proprietary (Hart)
 */
public class NavigationOld
{
    public static void navTo(Activity activity, Fragment fragment, int resourceID)
    {
        navTo(activity, fragment, resourceID, fragment.getClass().getSimpleName(), true);
    }

    public static void navTo(Activity activity, Fragment fragment, int resourceID, String id)
    {
        navTo(activity, fragment, resourceID, id, true);
    }

    public static void navTo(Activity activity, Fragment fragment, int resourceID, boolean addToBackStack)
    {
        navTo(activity, fragment, resourceID, fragment.getClass().getSimpleName(), addToBackStack);
    }

    public static void navTo(Activity activity, Fragment fragment, int resourceID, String id, boolean addToBackStack)
    {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (addToBackStack)
        {
            transaction.addToBackStack(id);
        }

        int backStackCount = manager.getBackStackEntryCount();

        if (backStackCount == 0)
        {
            transaction.add(resourceID, fragment, id);
        }
        else
        {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(backStackCount - 1);
            transaction.replace(resourceID, fragment, id);
        }

        transaction.commit();
    }

    public static boolean pop(Activity activity)
    {
        FragmentManager manager = activity.getFragmentManager();

        if (manager.getBackStackEntryCount() == 0)
        {
            return false;
        }
        else
        {
            //FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
            manager.popBackStack();
            return true;
        }
    }

    public static boolean popUtil(Activity activity, String transactionTag, boolean inclusive)
    {
        FragmentManager manager = activity.getFragmentManager();

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
}
