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
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.raja.knowme.variables.ContactDetailsObject;
import com.raja.knowme.variables.KnowMeDataObject;

public class FragmentContact extends Fragment {
	
	private TextView mAddressText;
	private ImageButton mCallBtn, mMailBtn, mFbBtn;
	private ConnectivityManager mConnectivityManager;
	private boolean isCallMenu = true;
	private ContactDetailsObject mConatctsData;
	private KnowMeDataObject knowmeData;
	private ProgressDialog mProgressDialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mHolderView = inflater.inflate(R.layout.fragment_contact, null);
		knowmeData = new KnowMeDataObject(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.loading));
        
        mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		// mCallBtn = (ImageButton) mHolderView.findViewById(R.id.contact_call_btn);
		mMailBtn = (ImageButton) mHolderView.findViewById(R.id.contact_mail_btn);
		mFbBtn = (ImageButton) mHolderView.findViewById(R.id.contact_fb_btn);
		mAddressText = (TextView) mHolderView.findViewById(R.id.address_text);
		// registerForContextMenu(mCallBtn);
		registerForContextMenu(mMailBtn);

		//mCallBtn.setOnClickListener(new OnClickListener() {
		//	public void onClick(View v) {
		//	isCallMenu = true;
		//	getActivity().openContextMenu(v);
		//}
		//	});
		mFbBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/A.R.Raajaa"));
				startActivity(fb);

				openFacebook(getActivity());
			}
		});
        mMailBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//	isCallMenu = false;
				getActivity().openContextMenu(v);
			}
		});
        
        mProgressDialog.show();
		new LoadData().execute();
		return mHolderView;
	}

	private void openFacebook(Context context) {
		try {
			context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
			Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100004830370118"));
		} catch (Exception e) {
			Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/A.R.Raajaa"));
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
		//if(isCallMenu) {
		//	menu.setHeaderIcon(R.drawable.ic_call);
		//	menu.setHeaderTitle(R.string.call_me);
		//	for(int count = 0; count < mConatctsData.getPhoneNumbers().length; count++)
		//	menu.add(0, v.getId(), 0, mConatctsData.getPhoneNumbers()[count]);
		//	} else
		{
			menu.setHeaderIcon(R.drawable.ic_mail);
    		menu.setHeaderTitle(R.string.mail_me);
    		for(int count = 0; count < mConatctsData.getEmailAddress().length; count++) 
    			menu.add(0, v.getId(), 0, mConatctsData.getEmailAddress()[count]);
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
		//if(isCallMenu) {
		//try {
		//      startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+item.getTitle().toString().trim())));
		// } catch (ActivityNotFoundException e) {
		// 	Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
		//  }
		//} else {
		//if (mConnectivityManager.getActiveNetworkInfo() == null)
		//	Toast.makeText(getActivity(), R.string.error_interenet_connection, Toast.LENGTH_SHORT).show();
		//else
		{
				final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{item.getTitle().toString().trim()});
				startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.send_mail)));
			}
		//}
		return true;
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
				mConatctsData = knowmeData.getContactsData();
				loaderResponse = "true";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return loaderResponse;
		}

		protected void onPostExecute(String result) {
			if(result.equals("true")) {
				mAddressText.setText(mConatctsData.getAddress());
				mProgressDialog.dismiss();
			} else 
				Toast.makeText(getActivity(), R.string.error_parsing, Toast.LENGTH_SHORT).show();
		}
    }
}
