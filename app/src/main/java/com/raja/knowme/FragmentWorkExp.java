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
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils.TruncateAt;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.raja.knowme.functions.AppCommonFunctions;
import com.raja.knowme.preferences.AppPreferences;
import com.raja.knowme.variables.AppGlobalVariables;
import com.raja.knowme.variables.KnowMeDataObject;
import com.raja.knowme.variables.WorkExpDetailsObject;
import com.raja.knowme.widgets.AppTextView;

import java.util.ArrayList;

public class FragmentWorkExp extends Fragment {

    private int MAX_COUNT = 2;
    private GestureDetector gestureDetector;
    private View.OnTouchListener gestureListener;

    private TextSwitcher mCompanyNameSwitcher, mCompanySpanSwitcher, mCompanySummarySwitcher;
    private ScrollView mScrollContainer;
    private RelativeLayout mInstructionBtn;
    private Animation shake;

    private int count = 0;
    private ArrayList<WorkExpDetailsObject> mData = new ArrayList<WorkExpDetailsObject>(0);
    private KnowMeDataObject knowmeData;
    private AppCommonFunctions functions;
    private AppPreferences pref;
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mHolderView = inflater.inflate(R.layout.fragment_work_exp, null);
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        functions = new AppCommonFunctions(getActivity());
        pref = new AppPreferences(getActivity());
        knowmeData = new KnowMeDataObject(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));

        mCompanyNameSwitcher = (TextSwitcher) mHolderView.findViewById(R.id.work_company_name_text);
        mCompanySpanSwitcher = (TextSwitcher) mHolderView.findViewById(R.id.work_company_span_text);
        mCompanySummarySwitcher = (TextSwitcher) mHolderView.findViewById(R.id.work_company_summary_text);
        mScrollContainer = (ScrollView) mHolderView.findViewById(R.id.work_scrollview);
        mInstructionBtn = (RelativeLayout) mHolderView.findViewById(R.id.instrunstions_layout);

        if (!pref.getWorkFirstRun()) {
            mInstructionBtn.setVisibility(RelativeLayout.GONE);
        }

        mInstructionBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pref.setWorkRunned();
                mInstructionBtn.setVisibility(RelativeLayout.GONE);
            }
        });
/*                 Multiple Screen Size Condition             */

        // Small Size

        if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(getActivity(), "small", Toast.LENGTH_SHORT).show();
        }

        //Normal Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "normal", Toast.LENGTH_SHORT).show();

        }

        // Large Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "large", Toast.LENGTH_SHORT).show();


        }

        //X-large Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(getActivity(), "xlarge", Toast.LENGTH_SHORT).show();

        }

        //Undefined Size

        else {
            Toast.makeText(getActivity(), "undefined", Toast.LENGTH_SHORT).show();


        }

        mCompanyNameSwitcher.setFactory(new ViewFactory() {
            public View makeView() {
                /** Set up the custom auto scrolling text view class for lengthy album names */
                AppTextView textSwitcher_text = new AppTextView(getActivity());
                textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                textSwitcher_text.setTextSize(19 * functions.getScreenDPI());
                textSwitcher_text.setSingleLine(true);
                textSwitcher_text.setEllipsize(TruncateAt.MARQUEE);
                textSwitcher_text.setMarqueeRepeatLimit(-1);
                textSwitcher_text.setHorizontallyScrolling(true);
                return textSwitcher_text;
            }
        });

        mCompanySpanSwitcher.setFactory(new ViewFactory() {
            public View makeView() {
                /** Set up the custom auto scrolling text view class for lengthy album names */
                AppTextView textSwitcher_text = new AppTextView(getActivity());
                textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                textSwitcher_text.setTextSize(12 * functions.getScreenDPI());
                textSwitcher_text.setSingleLine(true);
                textSwitcher_text.setEllipsize(TruncateAt.MARQUEE);
                textSwitcher_text.setMarqueeRepeatLimit(-1);
                textSwitcher_text.setHorizontallyScrolling(true);
                return textSwitcher_text;
            }
        });

        mCompanySummarySwitcher.setFactory(new ViewFactory() {
            public View makeView() {
                /** Set up the custom auto scrolling text view class for lengthy album names */
                AppTextView textSwitcher_text = new AppTextView(getActivity());
                textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                textSwitcher_text.setTextSize(12 * functions.getScreenDPI());
                return textSwitcher_text;
            }
        });

        mProgressDialog.show();
        new LoadData().execute();

        // Gesture detection
        gestureDetector = new GestureDetector(getActivity(), new OnGestureListener() {

            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            public void onShowPress(MotionEvent e) {
            }

            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            public void onLongPress(MotionEvent e) {
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(e1.getY() - e2.getY()) > AppGlobalVariables.SWIPE_MAX_OFF_PATH)
                        return false;
                    /** Left swipe */
                    if (e1.getX() - e2.getX() > AppGlobalVariables.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > AppGlobalVariables.SWIPE_THRESHOLD_VELOCITY) {
                        if (count < (MAX_COUNT - 1))
                            nextCompany();
                        else {
                            mCompanyNameSwitcher.startAnimation(shake);
                            mCompanySpanSwitcher.startAnimation(shake);
                            mCompanySummarySwitcher.startAnimation(shake);
                        }
                        /** Right Swipe */
                    } else if (e2.getX() - e1.getX() > AppGlobalVariables.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > AppGlobalVariables.SWIPE_THRESHOLD_VELOCITY) {
                        if (count != 0)
                            previousComapny();
                        else {
                            mCompanyNameSwitcher.startAnimation(shake);
                            mCompanySpanSwitcher.startAnimation(shake);
                            mCompanySummarySwitcher.startAnimation(shake);
                        }
                    }
                } catch (Exception e) {
                }

                return false;
            }

            public boolean onDown(MotionEvent e) {
                return false;
            }
        });

        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        mScrollContainer.setOnTouchListener(gestureListener);
        return mHolderView;
    }

    private void nextCompany() {
        count++;
        mCompanyNameSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mCompanyNameSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mCompanySpanSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mCompanySpanSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mCompanySummarySwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mCompanySummarySwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mCompanyNameSwitcher.setText(mData.get(count).getCompanyName());
        mCompanySpanSwitcher.setText(mData.get(count).getWorkSpan());
        mCompanySummarySwitcher.setText(mData.get(count).getWorkDetails());
        mScrollContainer.scrollTo(0, 0);
    }

    private void previousComapny() {
        count--;
        mCompanyNameSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        mCompanyNameSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        mCompanySpanSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        mCompanySpanSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        mCompanySummarySwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        mCompanySummarySwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        mCompanyNameSwitcher.setText(mData.get(count).getCompanyName());
        mCompanySpanSwitcher.setText(mData.get(count).getWorkSpan());
        mCompanySummarySwitcher.setText(mData.get(count).getWorkDetails());
        mScrollContainer.scrollTo(0, 0);
    }


    private class LoadData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            String loaderResponse = "false";
            try {
                mData = knowmeData.getWorkDetails();
                loaderResponse = "true";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return loaderResponse;
        }

        protected void onPostExecute(String result) {
            if (result.equals("true")) {
                MAX_COUNT = mData.size();
                mCompanyNameSwitcher.setText(mData.get(count).getCompanyName());
                mCompanySpanSwitcher.setText(mData.get(count).getWorkSpan());
                mCompanySummarySwitcher.setText(mData.get(count).getWorkDetails());
                mProgressDialog.dismiss();
            } else
                Toast.makeText(getActivity(), R.string.error_parsing, Toast.LENGTH_SHORT).show();
        }
    }
}
