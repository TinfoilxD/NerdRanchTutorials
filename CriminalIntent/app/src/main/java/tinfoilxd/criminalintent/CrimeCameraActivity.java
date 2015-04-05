package tinfoilxd.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by TinfoilxD on 4/4/15.
 */
public class CrimeCameraActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new CrimeCameraFragment();
    }
}
