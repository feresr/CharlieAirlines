package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class ThirdPageFragment extends BaseFragment {

    public static ThirdPageFragment newInstance(Context context) {
        ThirdPageFragment fragment = new ThirdPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_first_information_item, null);
        return root;
    }






}
