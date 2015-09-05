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

public class SkillsDetailsObject {

    private String mSkillsName = "";
    private String mSkillsLink = "";

    /**
     * @param mSkillsName
     * @param mSkillsLink
     */
    public SkillsDetailsObject(String mSkillsName, String mSkillsLink) {
        super();
        this.mSkillsName = mSkillsName;
        this.mSkillsLink = mSkillsLink;
    }

    /**
     * @return the mSkillsName
     */
    public String getSkillsName() {
        return mSkillsName;
    }

    /**
     * @return the mSkillsLink
     */
    public String getSkillsLink() {
        return mSkillsLink;
    }
}
