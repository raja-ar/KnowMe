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
public class ProjectsDetailsObject {
	
	private String mProjectName = "";
	private String mProjectDescription = "";
	
	/**
	 * @param mProjectName
	 * @param mProjectDescription
	 */
	public ProjectsDetailsObject(String mProjectName,
			String mProjectDescription) {
		super();
		this.mProjectName = mProjectName;
		this.mProjectDescription = mProjectDescription;
	}

	/**
	 * @return the mProjectName
	 */
	public String getProjectName() {
		return mProjectName;
	}

	/**
	 * @return the mProjectDescription
	 */
	public String getProjectDescription() {
		return mProjectDescription;
	}
}
