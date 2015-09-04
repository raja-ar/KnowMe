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

public class ProjectsObject {
	
	private String mCompanyName = "";
	private ArrayList<ProjectsDetailsObject> mProjectDescription = new ArrayList<ProjectsDetailsObject>(0);
	
	/**
	 * @param mCompanyName
	 * @param mProjectDescription
	 */
	public ProjectsObject(String mCompanyName,
			ArrayList<ProjectsDetailsObject> mProjectDescription) {
		super();
		this.mCompanyName = mCompanyName;
		this.mProjectDescription = mProjectDescription;
	}

	/**
	 * @return the mCompanyName
	 */
	public String getCompanyName() {
		return mCompanyName;
	}

	/**
	 * @return the mProjectDescription
	 */
	public ArrayList<ProjectsDetailsObject> getProjectDescription() {
		return mProjectDescription;
	}
}
