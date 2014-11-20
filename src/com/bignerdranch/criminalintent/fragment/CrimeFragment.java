package com.bignerdranch.criminalintent.fragment;

import java.util.Date;
import java.util.UUID;

import com.bignerdranch.criminalintent.controller.CrimeLab;
import com.bignerdranch.criminalintent.model.Crime;
import com.bignerdranch.criminalintent.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {

	public static final String EXTRA_CRIME_ID = "com.bignerdranch.criminalintent.crime_id";
	private Crime crime;
	private EditText titleField;
	private Button dateButton;
	private CheckBox solvedCheckBox;
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		crime = CrimeLab.Singleton.getCrime(crimeId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime,container,false);
		
		titleField = (EditText) v.findViewById(R.id.crime_title);
		titleField.setText(crime.getTitle());
		titleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				crime.setTitle(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}		
		});
		
		dateButton = (Button) v.findViewById(R.id.crime_date);
		updateDate();
		dateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
				dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		solvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		solvedCheckBox.setChecked(crime.isSolved());
		solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				crime.setSolved(isChecked);				
			}
			
		});
		
		return v;
	}

	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		
		return fragment;
	}

	private void updateDate() {
		dateButton.setText(crime.getDate().toString());
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		
		if (requestCode == REQUEST_DATE) {
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			crime.setDate(date);
			updateDate();
		}
	}
	
	
}
