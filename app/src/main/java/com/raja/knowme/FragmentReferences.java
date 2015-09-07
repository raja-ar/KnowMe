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
import android.text.TextUtils;
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
import android.widget.ViewSwitcher;

import com.raja.knowme.functions.AppCommonFunctions;
import com.raja.knowme.preferences.AppPreferences;
import com.raja.knowme.variables.AppGlobalVariables;
import com.raja.knowme.variables.KnowMeDataObject;
import com.raja.knowme.variables.ReferencesDetailsObject;
import com.raja.knowme.widgets.AppTextView;

import java.util.ArrayList;

public class FragmentReferences extends Fragment {

    private final int MAX_COUNT = 2;
    private GestureDetector gestureDetector;
    private View.OnTouchListener gestureListener;

    private TextSwitcher mHeaderSwitcher, mBodySwitcher;
    private ScrollView mScrollview;
    private RelativeLayout mInstructionBtn;

    private Animation shake;
    private AppCommonFunctions functions;
    private AppPreferences pref;
    private int count = 0;
    private KnowMeDataObject knowmeData;
    private ArrayList<ReferencesDetailsObject> mReferencesDetails = new ArrayList<ReferencesDetailsObject>(0);
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mHolderView = inflater.inflate(R.layout.fragment_references, null);
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        functions = new AppCommonFunctions(getActivity());
        pref = new AppPreferences(getActivity());
        knowmeData = new KnowMeDataObject(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));

        mHeaderSwitcher = (TextSwitcher) mHolderView.findViewById(R.id.references_subheader_text);
        mBodySwitcher = (TextSwitcher) mHolderView.findViewById(R.id.references_body_text);
        mScrollview = (ScrollView) mHolderView.findViewById(R.id.references_scrollview);
        mInstructionBtn = (RelativeLayout) mHolderView.findViewById(R.id.instrunstions_layout);

        if (!pref.getReferencesFirstRun()) {
            mInstructionBtn.setVisibility(RelativeLayout.GONE);
        }

        mInstructionBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pref.setReferencesRunned();
                mInstructionBtn.setVisibility(RelativeLayout.GONE);
            }
        });

        /*                 Multiple Screen Size Condition             */

        // Small Size

        if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(getActivity(), "small", Toast.LENGTH_SHORT).show();
            mHeaderSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                    textSwitcher_text.setTextSize(24 * functions.getScreenDPI());
                    textSwitcher_text.setSingleLine(true);
                    textSwitcher_text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textSwitcher_text.setMarqueeRepeatLimit(-1);
                    textSwitcher_text.setHorizontallyScrolling(true);
                    return textSwitcher_text;
                }
            });

            mBodySwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                    textSwitcher_text.setTextSize(6 * functions.getScreenDPI());
                    return textSwitcher_text;
                }
            });
        }

        //Normal Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "normal", Toast.LENGTH_SHORT).show();
            mHeaderSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                    textSwitcher_text.setTextSize(14 * functions.getScreenDPI());
                    textSwitcher_text.setSingleLine(true);
                    textSwitcher_text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textSwitcher_text.setMarqueeRepeatLimit(-1);
                    textSwitcher_text.setHorizontallyScrolling(true);
                    return textSwitcher_text;
                }
            });

            mBodySwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                    textSwitcher_text.setTextSize(9 * functions.getScreenDPI());
                    return textSwitcher_text;
                }
            });

        }

        // Large Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "large", Toast.LENGTH_SHORT).show();
            mHeaderSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                    textSwitcher_text.setTextSize(32 * functions.getScreenDPI());
                    textSwitcher_text.setSingleLine(true);
                    textSwitcher_text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textSwitcher_text.setMarqueeRepeatLimit(-1);
                    textSwitcher_text.setHorizontallyScrolling(true);
                    return textSwitcher_text;
                }
            });

            mBodySwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                    textSwitcher_text.setTextSize(18 * functions.getScreenDPI());
                    return textSwitcher_text;
                }
            });


        }

        //X-large Size

        else if ((getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(getActivity(), "xlarge", Toast.LENGTH_SHORT).show();
            mHeaderSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                    textSwitcher_text.setTextSize(48 * functions.getScreenDPI());
                    textSwitcher_text.setSingleLine(true);
                    textSwitcher_text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textSwitcher_text.setMarqueeRepeatLimit(-1);
                    textSwitcher_text.setHorizontallyScrolling(true);
                    return textSwitcher_text;
                }
            });

            mBodySwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                    textSwitcher_text.setTextSize(24 * functions.getScreenDPI());
                    return textSwitcher_text;
                }
            });

        }

        //Undefined Size

        else {
            Toast.makeText(getActivity(), "undefined", Toast.LENGTH_SHORT).show();
            mHeaderSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 245, 242, 11));
                    textSwitcher_text.setTextSize(32 * functions.getScreenDPI());
                    textSwitcher_text.setSingleLine(true);
                    textSwitcher_text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textSwitcher_text.setMarqueeRepeatLimit(-1);
                    textSwitcher_text.setHorizontallyScrolling(true);
                    return textSwitcher_text;
                }
            });

            mBodySwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                public View makeView() {
                    /** Set up the custom auto scrolling text view class for lengthy album names */
                    AppTextView textSwitcher_text = new AppTextView(getActivity());
                    textSwitcher_text.setTextColor(Color.argb(225, 225, 225, 225));
                    textSwitcher_text.setTextSize(20 * functions.getScreenDPI());
                    return textSwitcher_text;
                }
            });


        }





        mProgressDialog.show();
        new LoadData().execute();

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
                            nextDetail();
                        else {
                            mHeaderSwitcher.startAnimation(shake);
                            mBodySwitcher.startAnimation(shake);
                        }
                        /** Right Swipe */
                    } else if (e2.getX() - e1.getX() > AppGlobalVariables.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > AppGlobalVariables.SWIPE_THRESHOLD_VELOCITY) {
                        if (count != 0)
                            previousDetail();
                        else {
                            mHeaderSwitcher.startAnimation(shake);
                            mBodySwitcher.startAnimation(shake);
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

        mScrollview.setOnTouchListener(gestureListener);
        return mHolderView;
    }

    private void nextDetail() {
        count++;
        mHeaderSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mHeaderSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        mBodySwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in));
        mBodySwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out));
        setUIData(count);
        mScrollview.scrollTo(0, 0);
    }

    private void previousDetail() {
        count--;
        mHeaderSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        mHeaderSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        mBodySwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in));
        mBodySwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out));
        setUIData(count);
        mScrollview.scrollTo(0, 0);
    }

    private void setUIData(int count) {
        mHeaderSwitcher.setText(mReferencesDetails.get(count).getHeader());
        StringBuilder sb = new StringBuilder();
        for (int counter = 0; counter < mReferencesDetails.get(count).getDetailsData().size(); counter++)
            sb.insert(sb.lastIndexOf(sb.toString()), mReferencesDetails.get(count).getDetailsData().get(counter) + "\n\n");
        mBodySwitcher.setText(sb.toString());
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
                mReferencesDetails = knowmeData.getReferences();
                loaderResponse = "true";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return loaderResponse;
        }

        protected void onPostExecute(String result) {
            if (result.equals("true")) {
                setUIData(count);
                mProgressDialog.dismiss();
            } else
                Toast.makeText(getActivity(), R.string.error_parsing, Toast.LENGTH_SHORT).show();
        }
    }
}
