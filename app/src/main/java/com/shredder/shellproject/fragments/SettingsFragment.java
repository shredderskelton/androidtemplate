package com.shredder.shellproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shredder.shellproject.BuildConfig;
import com.shredder.shellproject.R;
import com.shredder.shellproject.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsFragment extends BaseFragment {
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Bind(R.id.version_text)
    TextView versionText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        versionText.setText(BuildConfig.VERSION_NAME);
    }

    @Override
    protected String getTitle() {
        return "Settings";
    }
}
