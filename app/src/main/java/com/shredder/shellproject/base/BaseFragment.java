package com.shredder.shellproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private AddFragmentHandler fragmentHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentHandler = new AddFragmentHandler(getActivity().getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getTitle());
    }

    protected abstract String getTitle();

    protected void add(BaseFragment fragment) {
        fragmentHandler.add(fragment);
    }
}
