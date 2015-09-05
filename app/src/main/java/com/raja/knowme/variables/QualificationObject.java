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

package com.raja.knowme.variables;

import java.util.ArrayList;

public class QualificationObject {

    private String mInstitutionName = "";
    private ArrayList<QualificationDetailsObject> mQualificationDescription = new ArrayList<QualificationDetailsObject>(0);

    /**
     * @param mInstitutionName
     * @param mQualificationDescription
     */
    public QualificationObject(String mInstitutionName,
                               ArrayList<QualificationDetailsObject> mQualificationDescription) {
        super();
        this.mInstitutionName = mInstitutionName;
        this.mQualificationDescription = mQualificationDescription;
    }

    /**
     * @return the mInstitutionName
     */
    public String getInstitutionName() {
        return mInstitutionName;
    }

    /**
     * @return the mQualificationDescription
     */
    public ArrayList<QualificationDetailsObject> getQualificationDescription() {
        return mQualificationDescription;
    }
}
