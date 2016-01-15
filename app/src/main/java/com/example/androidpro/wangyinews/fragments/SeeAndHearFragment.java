package com.example.androidpro.wangyinews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidpro.wangyinews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAndHearFragment extends Fragment {


    public SeeAndHearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_and_hear, container, false);
    }

}
