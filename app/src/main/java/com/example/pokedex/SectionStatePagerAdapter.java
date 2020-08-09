package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mfragmentlist=new ArrayList<>();
    private final List<String> mfragmentTitlelist=new ArrayList<>();


    public SectionStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    private void AddFragment(Fragment fragment, String title)
    {
        mfragmentlist.add(fragment);
        mfragmentTitlelist.add(title);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentlist.size();
    }
}
