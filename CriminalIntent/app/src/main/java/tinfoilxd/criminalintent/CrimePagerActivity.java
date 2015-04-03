package tinfoilxd.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.UUID;

public class CrimePagerActivity extends ActionBarActivity
{
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);


        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm)
        {
            @Override
            public Fragment getItem(int position)
            {
                Crime c = mCrimes.get(position);
                return CrimeFragment.newInstance(c.getId());
            }

            @Override
            public int getCount()
            {
                return mCrimes.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                Crime c = mCrimes.get(position);
                if(c.getTitle() != null)
                    setTitle(c.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for(int i = 0; i < mCrimes.size(); i++)
        {
            if(crimeId.equals(mCrimes.get(i).getId()))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
