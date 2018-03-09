package com.example.baobang.gameduangua.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.baobang.gameduangua.base.BaseFragment;

import java.util.List;

/**
 * Created by huuduc on 01/02/2018.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{

    public static final int FRAGMENT_COUNT = 2;
    private List<BaseFragment> mList;

    public MyPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Description";
            case 1:
                return "Lectures";
            default:
                return "";
        }
    }

}
