/*
 * Copyright 2015 Azmeer Raja
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.raja.knowme.preferences;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
	
	private static SharedPreferences mPrefs;
	private Context context;

	public AppPreferences(Context _context){
		this.context = _context;
		mPrefs = context.getSharedPreferences("MyKnowMeAppPrefs", 0); //0 = mode private. only this app can read these preferences
	}

	//Profile runned
	public void setProfileRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeProfileFirstRun", false);
		edit.commit();
	}
	//Project runned
	public void setProjectsRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeProjectsFirstRun", false);
		edit.commit();
	}

	//Qualification runned
	public void setQualificationRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeQualificationFirstRun", false);
		edit.commit();
	}
	//Work Experience runned
	public void setWorkRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeWorkFirstRun", false);
		edit.commit();
	}
	//References runned
	public void setReferencesRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeReferencesFirstRun", false);
		edit.commit();
	}
	//Testimonials runned
	public void setTestimonialsRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("MyKnowMeTestimonialsFirstRun", false);
		edit.commit();
	}

	//Profile First run
	public boolean getProfileFirstRun() {
		return mPrefs.getBoolean("MyKnowMeProfileFirstRun", true);
	}
	//Project First run
	public boolean getProjectsFirstRun() {
		return mPrefs.getBoolean("MyKnowMeProjectsFirstRun", true);
	}
	//Qualification First Run
	public boolean getQualificationFirstRun() {
		return mPrefs.getBoolean("MyKnowMeQualificationFirstRun", true);
	}
	//work experience first run
	public boolean getWorkFirstRun() {
		return mPrefs.getBoolean("MyKnowMeWorkFirstRun", true);
	}
	//References First run
	public boolean getReferencesFirstRun() {
		return mPrefs.getBoolean("MyKnowMeReferencesFirstRun", true);
	}
	//Testimonials First run
	public boolean getTestimonialsFirstRun() {
		return mPrefs.getBoolean("MyKnowMeTestimonialsFirstRun", true);
	}
}
