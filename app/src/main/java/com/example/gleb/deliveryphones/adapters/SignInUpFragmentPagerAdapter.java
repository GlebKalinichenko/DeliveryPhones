package com.example.gleb.deliveryphones.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SignInUpFragmentPagerAdapter extends FragmentPagerAdapter {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private List<Fragment> signFragments;

    public SignInUpFragmentPagerAdapter(FragmentManager fm, List<Fragment> signFragments) {
        super(fm);
        this.signFragments = signFragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = signFragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return signFragments.size();
    }
}
