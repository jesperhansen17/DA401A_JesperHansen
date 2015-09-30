package jesperhansen.assignment5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                // Create Fragment
                fragment = new MoviesFragment();
                break;
            case 1:
                // Create Fragment
                fragment = new QuoteFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
