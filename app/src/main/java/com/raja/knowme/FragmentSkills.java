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

package com.raja.knowme;

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
import com.raja.knowme.variables.SkillsDetailsObject;
import com.raja.knowme.widgets.AppTextView;

import java.util.ArrayList;

/**
 * http://www.youtube.com/watch?v=qxxM7X4Zpr8&feature=plcp&context=C3b2e024UDOEgsToPDskIKlaDd4I-hyl4W-2Hs1O7Z
 *
 * @author Azmeer Raja
 */
public class FragmentSkills extends Fragment {

    private LinearLayout mSkillsHolder;
    private Animation shake;
    private ArrayList<SkillsDetailsObject> mSkillsData = new ArrayList<SkillsDetailsObject>(0);
    private KnowMeDataObject knowmeData;
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mHolderView = inflater.inflate(R.layout.fragment_skills, null);
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        knowmeData = new KnowMeDataObject(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));

        mSkillsHolder = (LinearLayout) mHolderView.findViewById(R.id.skills_container_view);
        mProgressDialog.show();
        new LoadData().execute();
        return mHolderView;
    }

    private AppTextView addItem(final SkillsDetailsObject data) {
        final AppTextView mTextView = new AppTextView(getActivity());
        LayoutParams mViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mViewParams.setMargins(0, 5, -5, 0);
        mTextView.setLayoutParams(mViewParams);
        mTextView.setText(data.getSkillsName());
        mTextView.setGravity(Gravity.LEFT);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(20);
        mTextView.setPadding(3, 15, 0, 0);
        /*try {
    		mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, mImage, 0);
    	} catch (Exception e) {
    		Log.w(this.getClass().getName(), "Drawable not found for : " + name);
    	}*/
        if (data.getSkillsLink().length() > 0) {
            mTextView.setBackgroundResource(R.drawable.button_config);
            mTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (data.getSkillsLink().trim().length() > 0)
                        openWebPage(data.getSkillsLink(), mTextView);
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
     * @author Azmeer Raja
     * @usage new LoadData().execute(new Void[0]);
     */
    private class LoadData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            String loaderResponse = "false";
            try {
                mSkillsData = knowmeData.getSkillsData();
                loaderResponse = "true";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return loaderResponse;
        }

        protected void onPostExecute(String result) {
            if (result.equals("true")) {
                for (int count = 0; count < mSkillsData.size(); count++)
                    mSkillsHolder.addView(addItem(mSkillsData.get(count)));
                mProgressDialog.dismiss();
            } else
                Toast.makeText(getActivity(), R.string.error_parsing, Toast.LENGTH_SHORT).show();
        }
    }
}
