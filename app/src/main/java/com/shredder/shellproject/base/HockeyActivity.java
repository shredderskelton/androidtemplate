package com.shredder.shellproject.base;

import android.os.Bundle;

import com.shredder.shellproject.ShredderBuildSettings;

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
        if (!ShredderBuildSettings.HockeyAppEnabled) {
            return;
        }
        UpdateManager.register(this, ShredderBuildSettings.HockeyAppId);
    }

    private void unregisterManagers() {
        if (!ShredderBuildSettings.HockeyAppEnabled) {
            return;
        }
        UpdateManager.unregister();
    }

    private void checkForCrashes() {
        CrashManager.register(this, ShredderBuildSettings.HockeyAppId);
    }
}
