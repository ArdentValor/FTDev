package com.hart.ftdev.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hart.ftdev.R;

/**
 * Created by Alex on 3/16/16.
 * Proprietary (Hart)
 */
public class FragmentA extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        Bundle bundle = getArguments();

        if (bundle != null)
        {
            String message = "Bundle Found: " + bundle.getString("name");
            Toast toast = Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        return view;
    }
}
