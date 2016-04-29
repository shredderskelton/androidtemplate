package com.shredder.shellproject.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private AddFragmentHandler fragmentHandler;

    final View.OnClickListener navigationBackPressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager.popBackStack();
        }
    };
    FragmentManager.OnBackStackChangedListener backStackListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            onBackStackChangedEvent();
        }
    };

    private void onBackStackChangedEvent() {
        syncDrawerToggleState();
    }

    private void syncDrawerToggleState() {
        ActionBarDrawerToggle drawerToggle = getDrawerToggle();
        if (drawerToggle == null) {
            return;
        }
        if (fragmentManager.getBackStackEntryCount() > 1) {
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setToolbarNavigationClickListener(navigationBackPressListener); //pop backstack
        } else {
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerToggle.setToolbarNavigationClickListener(drawerToggle.getToolbarNavigationClickListener()); //open nav menu drawer
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        fragmentHandler = new AddFragmentHandler(fragmentManager);
        fragmentManager.addOnBackStackChangedListener(backStackListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDrawer().addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                syncDrawerToggleState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                syncDrawerToggleState();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        fragmentManager.removeOnBackStackChangedListener(backStackListener);
        fragmentManager = null;
        super.onDestroy();
    }

    protected void add(BaseFragment fragment) {
        fragmentHandler.add(fragment);
    }

    @Override
    public void onBackPressed() {
        if (sendBackPressToDrawer()) {
            //the drawer consumed the backpress
            return;
        }

        if (sendBackPressToFragmentOnTop()) {
            // fragment on top consumed the back press
            return;
        }

        //let the android system handle the back press, usually by popping the fragment
        super.onBackPressed();

        //close the activity if back is pressed on the root fragment
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }
    }

    private boolean sendBackPressToDrawer() {
        DrawerLayout drawer = getDrawer();
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean sendBackPressToFragmentOnTop() {
        BaseFragment fragmentOnTop = fragmentHandler.getCurrentFragment();
        if (fragmentOnTop == null) {
            return false;
        }
        if (!(fragmentOnTop instanceof BackButtonSupportFragment)) {
            return false;
        }
        boolean consumedBackPress = ((BackButtonSupportFragment) fragmentOnTop).onBackPressed();
        return consumedBackPress;
    }

    protected abstract ActionBarDrawerToggle getDrawerToggle();

    protected abstract DrawerLayout getDrawer();
}
