package com.raja.knowme;
/*
 * Copyright 2015 Azmeer Raja
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity implements
		FragmentMainMenu.Callbacks {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.base_single_pane_container,
						new FragmentMainMenu()).commit();
	}
	public void onProfileSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentProfile()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentProfile()).addToBackStack(null)
					.commit();
	}

	public void onSkillsSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentSkills()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentSkills()).addToBackStack(null)
					.commit();
	}

	public void onSpecializationSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentSpecialization()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentSpecialization()).addToBackStack(null)
					.commit();
	}

	public void onProjectSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentProjects()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentProjects()).addToBackStack(null)
					.commit();
	}

	public void onQualificationSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentQualification()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentQualification()).addToBackStack(null)
					.commit();
	}

	public void onWorkSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentWorkExp()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentWorkExp()).addToBackStack(null)
					.commit();
	}

	public void onReferencesSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentReferences()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentReferences()).addToBackStack(null)
					.commit();
	}

	public void onTestimonialsSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentTestimonials()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentTestimonials()).addToBackStack(null)
					.commit();
	}
	public void onContactSelected() {
		if (findViewById(R.id.base_second_pane_container) != null)
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_second_pane_container,
							new FragmentContact()).commit();
		else
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.base_single_pane_container,
							new FragmentContact()).addToBackStack(null)
					.commit();
	}
}
