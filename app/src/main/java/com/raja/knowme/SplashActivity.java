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
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.raja.knowme.variables.AppGlobalVariables;
import com.raja.knowme.variables.KnowMeDataObject;

public class SplashActivity extends Activity {
	
	public static String firstName, middleName, lastName;
	private TextView mNameFirstText, mNameMiddleText, mNameLastText;
	private KnowMeDataObject knowmeData;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        knowmeData = new KnowMeDataObject(this);
        
        mNameFirstText = (TextView) findViewById(R.id.first_name_text);
        mNameMiddleText = (TextView) findViewById(R.id.middle_name_text);
        mNameLastText = (TextView) findViewById(R.id.last_name_text);
        
        try {
			firstName = knowmeData.getFirstName().trim();
			middleName = knowmeData.getMiddleName().trim();
			lastName = knowmeData.getLastName().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if(firstName.length() > 0) {
        	mNameFirstText.setText(firstName);
        	mNameFirstText.setVisibility(TextView.VISIBLE);
        }		        
        if(middleName.length() > 0) {
        	mNameMiddleText.setText(middleName);
        	mNameMiddleText.setVisibility(TextView.VISIBLE);
        }		        
        if(lastName.length() > 0) {
        	mNameLastText.setText(lastName);
        	mNameLastText.setVisibility(TextView.VISIBLE);
        }
        
        /** Run thread to change screen once the display time is over */
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent MyKnowMeIntent = new Intent(SplashActivity.this, BaseActivity.class);
				SplashActivity.this.startActivity(MyKnowMeIntent);
				SplashActivity.this.finish();  
            }
		}, AppGlobalVariables.SPLASH_TIMEOUT);    
	}
}
