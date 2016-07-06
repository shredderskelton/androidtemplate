package com.shredder.shellproject.base;

import android.os.Bundle;

import net.hockeyapp.android.BuildConfig;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

public abstract class HockeyActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkForUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    private void checkForUpdates() {
        if (!BuildConfig.DEBUG) {
            return;
        }
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        if (!BuildConfig.DEBUG) {
            return;
        }
        UpdateManager.unregister();
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }
}
