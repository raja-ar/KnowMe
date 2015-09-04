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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.raja.knowme.variables.KnowMeDataObject;
import com.raja.knowme.variables.SpecializationDetailsObject;
import com.raja.knowme.widgets.AppTextView;

import java.util.ArrayList;


public class FragmentSpecialization extends Fragment {

	private LinearLayout mSpecializationHolder;
	private Animation shake;
	private ArrayList<SpecializationDetailsObject> mSpecializationData = new ArrayList<SpecializationDetailsObject>(0);
	private KnowMeDataObject knowmeData;
	private ProgressDialog mProgressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View mHolderView = inflater.inflate(R.layout.fragment_specialization, null);
		shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
		knowmeData = new KnowMeDataObject(getActivity());
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(getString(R.string.loading));

		mSpecializationHolder = (LinearLayout) mHolderView.findViewById(R.id.specialization_container_view);
		mProgressDialog.show();
		new LoadData().execute();
		return mHolderView;
	}

	private AppTextView addItem(final SpecializationDetailsObject data) {
		final AppTextView mTextView= new AppTextView(getActivity());
		LayoutParams mViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mViewParams.setMargins(0, 5, -5, 0);
		mTextView.setLayoutParams(mViewParams);
		mTextView.setText(data.getSpecializationName());
		mTextView.setGravity(Gravity.LEFT);
		mTextView.setTextColor(Color.WHITE);
		mTextView.setTextSize(20);
		mTextView.setPadding(3, 15, 0, 0);
    	/*try {
    		mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, mImage, 0);
    	} catch (Exception e) {
    		Log.w(this.getClass().getName(), "Drawable not found for : " + name);
    	}*/
		if(data.getSpecializationLink().length() > 0) {
			mTextView.setBackgroundResource(R.drawable.button_config);
			mTextView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if(data.getSpecializationLink().trim().length() > 0)
						openWebPage(data.getSpecializationLink(), mTextView);
					else {
						mTextView.startAnimation(shake);
						Toast.makeText(getActivity(), R.string.error_redirection_link, Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
		return mTextView;
	}

	/**
	 *
	 * @param pageURL
	 * @param selectedView
	 */
	private void openWebPage(String pageURL, View selectedView) {
		ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() == null) {
			selectedView.startAnimation(shake);
			Toast.makeText(getActivity(), R.string.error_interenet_connection, Toast.LENGTH_SHORT).show();
		} else
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pageURL)));
	}

	/**
	 * @usage new LoadData().execute(new Void[0]);
	 * @author Azmeer Raja
	 */
	private class LoadData extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			String loaderResponse = "false";
			try {
				mSpecializationData = knowmeData.getSpecializationData();
				loaderResponse = "true";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return loaderResponse;
		}

		protected void onPostExecute(String result) {
			if(result.equals("true")) {
				for(int count = 0; count < mSpecializationData.size(); count++)
					mSpecializationHolder.addView(addItem(mSpecializationData.get(count)));
				mProgressDialog.dismiss();
			} else
				Toast.makeText(getActivity(), R.string.error_parsing, Toast.LENGTH_SHORT).show();
		}
	}
}
