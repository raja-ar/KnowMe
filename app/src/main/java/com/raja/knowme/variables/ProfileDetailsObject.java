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
import java.util.ArrayList;

public class ProfileDetailsObject {

	private String mHeader = null;
	private ArrayList<String> mDetailsData = null;
	
	/**
	 * @param mHeader
	 * @param mDetailsData
	 */
	public ProfileDetailsObject(String mHeader, ArrayList<String> mDetailsData) {
		super();
		this.mHeader = mHeader;
		this.mDetailsData = mDetailsData;
	}

	/**
	 * @return the mHeader
	 */
	public String getHeader() {
		return mHeader;
	}

	/**
	 * @return the mDetailsData
	 */
	public ArrayList<String> getDetailsData() {
		return mDetailsData;
	}
}
