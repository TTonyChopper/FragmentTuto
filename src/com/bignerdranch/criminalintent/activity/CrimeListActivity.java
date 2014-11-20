package com.bignerdranch.criminalintent.activity;

		
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.bignerdranch.criminalintent.fragment.CrimeListFragment;
import com.bignerdranch.criminalintent.R;


public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new CrimeListFragment();
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
