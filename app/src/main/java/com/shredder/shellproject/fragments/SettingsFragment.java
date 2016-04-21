package com.shredder.shellproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shredder.shellproject.R;
import com.shredder.shellproject.base.BaseFragment;

public class SettingsFragment extends BaseFragment {
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    protected String getTitle() {
        return "SettingsFragment";
    }
}
