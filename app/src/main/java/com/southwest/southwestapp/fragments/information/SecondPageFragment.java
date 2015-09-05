package com.southwest.southwestapp.fragments.information;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by luisalfonsobejaranosanchez on 9/5/15.
 */
public class SecondPageFragment extends BaseFragment {


    public static Fragment newInstance(Context context) {
        SecondPageFragment f = new SecondPageFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.first_information_item, null);
        return root;
    }

}
