package com.raja.knowme.variables;

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
public class WorkExpDetailsObject {

	private String mCompanyName = "";
	private String mWorkSpan = "";
	private String mWorkDetails = "";
	
	/**
	 * @param mCompanyName
	 * @param mWorkSpan
	 * @param mWorkDetails
	 */
	public WorkExpDetailsObject(String mCompanyName, String mWorkSpan,
			String mWorkDetails) {
		super();
		this.mCompanyName = mCompanyName;
		this.mWorkSpan = mWorkSpan;
		this.mWorkDetails = mWorkDetails;
	}

	/**
	 * @return the mCompanyName
	 */
	public String getCompanyName() {
		return mCompanyName;
	}

	/**
	 * @return the mWorkSpan
	 */
	public String getWorkSpan() {
		return mWorkSpan;
	}

	/**
	 * @return the mWorkDetails
	 */
	public String getWorkDetails() {
		return mWorkDetails;
	}
}
