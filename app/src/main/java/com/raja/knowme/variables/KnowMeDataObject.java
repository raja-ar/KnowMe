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

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class KnowMeDataObject {

    private final String LOCAL_DATA_SOURCE = "projects_data.xml";

    private String mFirstName = null;
    private String mMiddleName = null;
    private String mLastName = null;
    private ArrayList<SkillsDetailsObject> mSkillsData = null;
    private ArrayList<ProfileDetailsObject> mProfile = null;
    private ArrayList<ReferencesDetailsObject> mReferences = null;
    private ArrayList<TestimonialsDetailsObject> mTestimonials = null;
    private ArrayList<WorkExpDetailsObject> mWorkDetails = null;
    private ArrayList<ProjectsObject> mProjectsData = null;
    private ArrayList<QualificationObject> mQualificationData = null;
    private ArrayList<SpecializationDetailsObject> mSpecializationData = null;
    private ContactDetailsObject mContactsData = null;
    private Context mContext;
    private DataParsingFunctions mPraserFunction;


    public KnowMeDataObject(Context context) {
        this.mContext = context;
        this.mPraserFunction = new DataParsingFunctions();
    }

    public String readFileData() throws Exception {
        InputStream in = mContext.getAssets().open(LOCAL_DATA_SOURCE);
        InputStreamReader is = new InputStreamReader(in);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(is);
        String read = br.readLine();
        while (read != null) {
            sb.append(read);
            read = br.readLine();
        }
        return sb.toString();
    }

    /**
     * @param xml
     * @return
     * @throws Exception
     */
    private Document getDomElement(String xml) throws Exception {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        doc = db.parse(is);
        return doc;
    }

    /**
     * @return the mFirstName
     */
    public String getFirstName() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mFirstName = mPraserFunction.parseFirstName(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mFirstName;
    }

    /**
     * @return the mMiddleName
     */
    public String getMiddleName() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mMiddleName = mPraserFunction.parseMiddleName(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mMiddleName;
    }

    /**
     * @return the mLastName
     */
    public String getLastName() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mLastName = mPraserFunction.parseLastName(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLastName;
    }

    /**
     * @return the mProfile
     */
    public ArrayList<ProfileDetailsObject> getProfile() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mProfile = mPraserFunction.parseProfileDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProfile;
    }

    /**
     * @return the mReferences
     */
    public ArrayList<ReferencesDetailsObject> getReferences() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mReferences = mPraserFunction.parseReferencesDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mReferences;
    }

    /**
     * @return the mTestimonials
     */
    public ArrayList<TestimonialsDetailsObject> getTestimonials() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mTestimonials = mPraserFunction.parseTestimonialsDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mTestimonials;
    }

    /**
     * @return the mWorkDetails
     */
    public ArrayList<WorkExpDetailsObject> getWorkDetails() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mWorkDetails = mPraserFunction.parseWorkExpDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mWorkDetails;
    }

    /**
     * @return the mProjectsData
     */
    public ArrayList<ProjectsObject> getProjectsData() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mProjectsData = mPraserFunction.parseProjectsDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProjectsData;
    }

    /**
     * @return the mQualificationData
     */
    public ArrayList<QualificationObject> getQualificationData() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mQualificationData = mPraserFunction.parseQualificationDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mQualificationData;
    }

    /**
     * @return the mSpecializationData
     */
    public ArrayList<SpecializationDetailsObject> getSpecializationData() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mSpecializationData = mPraserFunction.parseSpecializationData(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSpecializationData;
    }

    /**
     * @return the mSkillsData
     */
    public ArrayList<SkillsDetailsObject> getSkillsData() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mSkillsData = mPraserFunction.parseSkillsData(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mSkillsData;
    }


    /**
     * @return the mContactsData
     */
    public ContactDetailsObject getContactsData() {
        String data = null;
        Document doc = null;
        try {
            data = readFileData();
            if (data != null) {
                doc = getDomElement(data);
                mContactsData = mPraserFunction.parseContactDetails(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mContactsData;
    }
}
