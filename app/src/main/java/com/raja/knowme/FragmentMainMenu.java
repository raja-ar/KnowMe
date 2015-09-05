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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentMainMenu extends Fragment {

    private static Callbacks sCallbacks = new Callbacks() {
        //create extra callbacks
        public void onProfileSelected() {
        }

        public void onSkillsSelected() {
        }

        public void onSpecializationSelected() {
        }

        public void onProjectSelected() {
        }

        public void onQualificationSelected() {
        }

        public void onWorkSelected() {
        }

        public void onReferencesSelected() {
        }

        public void onTestimonialsSelected() {
        }

        public void onContactSelected() {
        }
    };
    private Button mProfileBtn, mSkillsBtn, mSpecializationBtn, mProjectsBtn, mQualificationBtn, mWorkBtn, mReferencesBtn, mTestimonialsBtn, mContactBtn;
    private TextView mNameFirstText, mNameMiddleText, mNameLastText;
    private Callbacks mCallbacks = sCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mHolderView = inflater.inflate(R.layout.fragment_main, null);
        //Create extra buttons here
        mProfileBtn = (Button) mHolderView.findViewById(R.id.profile_btn);
        mSkillsBtn = (Button) mHolderView.findViewById(R.id.skills_btn);
        mSpecializationBtn = (Button) mHolderView.findViewById(R.id.specialization_btn);
        mQualificationBtn = (Button) mHolderView.findViewById(R.id.qualification_btn);
        mProjectsBtn = (Button) mHolderView.findViewById(R.id.projects_btn);
        mWorkBtn = (Button) mHolderView.findViewById(R.id.work_btn);
        mReferencesBtn = (Button) mHolderView.findViewById(R.id.references_btn);
        mTestimonialsBtn = (Button) mHolderView.findViewById(R.id.testimonials_btn);
        mContactBtn = (Button) mHolderView.findViewById(R.id.contact_btn);
        mNameFirstText = (TextView) mHolderView.findViewById(R.id.main_first_name_text);
        mNameMiddleText = (TextView) mHolderView.findViewById(R.id.main_middle_name_text);
        mNameLastText = (TextView) mHolderView.findViewById(R.id.main_last_name_text);
        if (com.raja.knowme.SplashActivity.firstName.length() > 0) {
            mNameFirstText.setText(SplashActivity.firstName);
            mNameFirstText.setVisibility(TextView.VISIBLE);
        }
        if (SplashActivity.middleName.length() > 0) {
            mNameMiddleText.setText(SplashActivity.middleName);
            mNameMiddleText.setVisibility(TextView.VISIBLE);
        }
        if (SplashActivity.lastName.length() > 0) {
            mNameLastText.setText(SplashActivity.lastName);
            mNameLastText.setVisibility(TextView.VISIBLE);
        }
        // create extra on click listeners and their respective call back events here
        mProfileBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onProfileSelected();
            }
        });
        mSkillsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onSkillsSelected();
            }
        });
        mSpecializationBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onSpecializationSelected();
            }
        });
        mProjectsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onProjectSelected();
            }
        });
        mQualificationBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onQualificationSelected();
            }
        });
        mWorkBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onWorkSelected();
            }
        });
        mReferencesBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onReferencesSelected();
            }
        });
        mTestimonialsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onTestimonialsSelected();
            }
        });
        mContactBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mCallbacks.onContactSelected();
            }
        });
        return mHolderView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Reset the active callbacks interface to the fragment implementation.
        mCallbacks = sCallbacks;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) activity;
    }

    public interface Callbacks {
        void onProfileSelected();

        void onSkillsSelected();

        void onSpecializationSelected();

        void onProjectSelected();

        void onQualificationSelected();

        void onWorkSelected();

        void onReferencesSelected();

        void onTestimonialsSelected();

        void onContactSelected();

    }
}