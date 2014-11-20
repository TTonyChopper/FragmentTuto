package com.bignerdranch.criminalintent.activity;

import java.util.ArrayList;
import java.util.UUID;

import com.bignerdranch.criminalintent.controller.CrimeLab;
import com.bignerdranch.criminalintent.fragment.CrimeFragment;
import com.bignerdranch.criminalintent.model.Crime;
import com.bignerdranch.criminalintent.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class CrimePagerActivity extends FragmentActivity {

	private ViewPager viewPager;
	private ArrayList<Crime> crimes;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		viewPager = new ViewPager(this);
		viewPager.setId(R.id.viewPager);
		setContentView(viewPager);
		
		crimes = CrimeLab.Singleton.getCrimes();
		
		FragmentManager fm = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentStatePagerAdapter(fm){

			@Override
			public Fragment getItem(int arg0) {
				Crime crime = crimes.get(arg0);
				return CrimeFragment.newInstance(crime.getId());
			}

			@Override
			public int getCount() {
				return crimes.size();
			}
			
		});
		
		UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		for (int i = 0; i< crimes.size(); i++) {
			if (crimes.get(i).getId().equals(crimeId)) {
				viewPager.setCurrentItem(i);
				break;
			}
		}
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageSelected(int arg0) {
				Crime crime = crimes.get(arg0);
				if (crime.getTitle() != null) {
					setTitle(crime.getTitle());
				}
			}	
		});
	}
}
