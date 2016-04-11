package com.hart.ftdev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hart.ftdev.fragments.FragmentA;
import com.hart.ftdev.fragments.FragmentB;
import com.hart.ftdev.fragments.FragmentC;
import com.hart.ftdev.navigation.NavAction;
import com.hart.ftdev.navigation.Navigation;
import com.hart.ftdev.navigation.TransAnim;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // any activity MUST register with Navigation class before
        Navigation.registerActivity(this, R.id.baseHold);
    }


    public void onNav(View view)
    {
        Bundle bundle;

        switch (view.getTag().toString())
        {
            case "NAA":
                // basic nav
                Navigation.navTo(FragmentA.class);
                break;
            case "NAB":
                // nav with explicit fragment id
                Navigation.navTo(FragmentB.class, FragmentB.class.getSimpleName());
                break;
            case "NAC":
                // nav example with animation
                Navigation.navTo(FragmentC.class, TransAnim.getDefaultAnim());
                break;
            case "NWA":
                // nav example with bundle and animations
                bundle = new Bundle();
                bundle.putString("name", "Mark");
                new NavAction.Builder().navTo(FragmentA.class)
                        .withBundle(bundle)
                        .withAnimations(TransAnim.getDefaultAnim())
                        .go();
                break;
            case "NWB":
                // nav example using a predefined action
                bundle = new Bundle();
                bundle.putString("name", "Esteban");
                NavAction action = new NavAction.Builder().navTo(FragmentB.class)
                        .withBundle(bundle)
                        .withAnimations(TransAnim.getDefaultAnim())
                        .buid();

                Navigation.navTo(action);
                break;
            case "NWC":
                // nav example with bundle and animation
                bundle = new Bundle();
                bundle.putString("name", "Alex");
                Navigation.navTo(FragmentC.class, bundle, TransAnim.getDefaultAnim());
                break;
            case "PTA":
                Navigation.popUntil(FragmentA.class.getSimpleName(), false);
                break;
            case "PTB":
                Navigation.popUntil(FragmentB.class.getSimpleName(), false);
                break;
            case "PTC":
                Navigation.popUntil(FragmentC.class.getSimpleName(), false);
                break;
            case "PIA":
                Navigation.popUntil(FragmentA.class.getSimpleName(), true);
                break;
            case "PIB":
                Navigation.popUntil(FragmentB.class.getSimpleName(), true);
                break;
            case "PIC":
                Navigation.popUntil(FragmentC.class.getSimpleName(), true);
                break;
            case "CLEAR":
                Navigation.clearBackStack();
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        if (!Navigation.pop())
        {
            super.onBackPressed();
        }
    }
}
