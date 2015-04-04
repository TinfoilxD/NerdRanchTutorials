package tinfoilxd.criminalintent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

public abstract class SingleFragmentActivity extends ActionBarActivity
{
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager manager = getSupportFragmentManager();
        Fragment crimeFragment = manager.findFragmentById(R.id.fragmentContainer);
        if(crimeFragment == null)
        {
            crimeFragment = createFragment();
            //transaction object implements fluent interface; all methods are called from anonymous transaction object
            manager.beginTransaction().add(R.id.fragmentContainer,crimeFragment).commit();

        }
    }
}
