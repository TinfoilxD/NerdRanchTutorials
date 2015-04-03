package tinfoilxd.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity
{


    @Override
    protected Fragment createFragment()
    {
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
