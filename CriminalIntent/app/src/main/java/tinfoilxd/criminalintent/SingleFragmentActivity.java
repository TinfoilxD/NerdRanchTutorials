package tinfoilxd.criminalintent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

public abstract class SingleFragmentActivity extends ActionBarActivity
{
    protected abstract Fragment createFragment();

    protected int getLayoutResId()
    {
        return R.layout.activity_fragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
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
