package nvduy1997.com.easytoeic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import nvduy1997.com.easytoeic.fragment.ScreenSlideFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 10;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new ScreenSlideFragment();
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
