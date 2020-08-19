package com.boreas.plainlife.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.boreas.plainlife.base.BaseFragment;

import java.util.List;

public class TabLayoutViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mList;
    private String[] mTitles;


    public TabLayoutViewPagerAdapter(FragmentManager fm, List<BaseFragment> mList,String[] mTitles) {
        super(fm);
        this.mList = mList;
        this.mTitles = mTitles;
        notifyDataSetChanged();
    }

    public TabLayoutViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
