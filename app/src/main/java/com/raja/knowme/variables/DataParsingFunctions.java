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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;


public class DataParsingFunctions {

    private final String KNOWME_TAG = "knowme";
    private final String NAME_TAG = "name";
    private final String FIRST_NAME_TAG = "first-name";
    private final String MIDDLE_NAME_TAG = "middle-name";
    private final String LAST_NAME_TAG = "last-name";
    private final String PERIOD_TAG = "period";
    private final String ADDRESS_TAG = "address";
    private final String PHONE_TAG = "phone";
    private final String MAIL_TAG = "mail";
    private final String PROFILE_DETAILS_TAG = "profile-detail";
    private final String SKILLS_DETAILS_TAG = "skills-details";
    private final String SPECIALIZATION_DETAILS_TAG = "specialization-details";
    private final String PROJECT_DETAILS_TAG = "projects-details";
    private final String COMPANY_TAG = "company";
    private final String QUALIFICATION_DETAILS_TAG = "qualification-details";
    private final String INSTITUTION_TAG = "institution";
    private final String WORK_DETAILS_TAG = "work-detial";
    private final String REFERENCES_DETAILS_TAG = "references-detail";
    private final String TESTIMONIALS_DETAILS_TAG = "testimonials-detail";
    private final String ITEM_DETAILS_TAG = "item-details";
    /**
     * @param doc
     * @return
     */
    protected String parseFirstName(Document doc) {
        String mFirstName = "";
        NodeList mKnowMeNodeList = doc.getElementsByTagName(KNOWME_TAG);
        for (int counter = 0; counter < mKnowMeNodeList.getLength(); counter++) {
            Element elementData = (Element) mKnowMeNodeList.item(counter);
            mFirstName = elementData.getAttribute(FIRST_NAME_TAG);
        }
        return mFirstName;
    }

    /**
     * @param doc
     * @return
     */
    protected String parseMiddleName(Document doc) {
        String mFirstName = "";
        NodeList mKnowMeNodeList = doc.getElementsByTagName(KNOWME_TAG);
        for (int counter = 0; counter < mKnowMeNodeList.getLength(); counter++) {
            Element elementData = (Element) mKnowMeNodeList.item(counter);
            mFirstName = elementData.getAttribute(MIDDLE_NAME_TAG);
        }
        return mFirstName;
    }

    /**
     * @param doc
     * @return
     */
    protected String parseLastName(Document doc) {
        String mFirstName = "";
        NodeList mKnowMeNodeList = doc.getElementsByTagName(KNOWME_TAG);
        for (int counter = 0; counter < mKnowMeNodeList.getLength(); counter++) {
            Element elementData = (Element) mKnowMeNodeList.item(counter);
            mFirstName = elementData.getAttribute(LAST_NAME_TAG);
        }
        return mFirstName;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<ProfileDetailsObject> parseProfileDetails(Document doc) {
        ArrayList<ProfileDetailsObject> mData = new ArrayList<ProfileDetailsObject>(0);
        NodeList mProfileNodeList = doc.getElementsByTagName(PROFILE_DETAILS_TAG);
        for (int counter = 0; counter < mProfileNodeList.getLength(); counter++) {
            Element data = (Element) mProfileNodeList.item(counter);
            ArrayList<String> mDetailsData = new ArrayList<String>(0);
            NodeList mDetailsNodeList = data.getElementsByTagName(ITEM_DETAILS_TAG);
            for (int counter_2 = 0; counter_2 < mDetailsNodeList.getLength(); counter_2++) {
                mDetailsData.add(getElementValue(mDetailsNodeList.item(counter_2)));
            }
            mData.add(new ProfileDetailsObject(data.getAttribute(NAME_TAG), mDetailsData));
        }
        return mData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<SkillsDetailsObject> parseSkillsData(Document doc) {
        ArrayList<SkillsDetailsObject> mSkillsData = new ArrayList<SkillsDetailsObject>(0);
        NodeList mSkillsNodeList = doc.getElementsByTagName(SKILLS_DETAILS_TAG);
        for (int counter = 0; counter < mSkillsNodeList.getLength(); counter++) {
            Element data = (Element) mSkillsNodeList.item(counter);
            mSkillsData.add(new SkillsDetailsObject(data.getAttribute(NAME_TAG), getElementValue(data)));
        }
        return mSkillsData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<SpecializationDetailsObject> parseSpecializationData(Document doc) {
        ArrayList<SpecializationDetailsObject> mSpecializationData = new ArrayList<SpecializationDetailsObject>(0);
        NodeList mSpecializationNodeList = doc.getElementsByTagName(SPECIALIZATION_DETAILS_TAG);
        for (int counter = 0; counter < mSpecializationNodeList.getLength(); counter++) {
            Element data = (Element) mSpecializationNodeList.item(counter);
            mSpecializationData.add(new SpecializationDetailsObject(data.getAttribute(NAME_TAG), getElementValue(data)));
        }
        return mSpecializationData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<ProjectsObject> parseProjectsDetails(Document doc) {
        ArrayList<ProjectsObject> mProjectsData = new ArrayList<ProjectsObject>(0);
        NodeList mCompanyNodeList = doc.getElementsByTagName(COMPANY_TAG);
        for (int counter = 0; counter < mCompanyNodeList.getLength(); counter++) {
            Element data = (Element) mCompanyNodeList.item(counter);
            NodeList mDetailsNodeList = data.getElementsByTagName(PROJECT_DETAILS_TAG);
            ArrayList<ProjectsDetailsObject> mDetails = new ArrayList<ProjectsDetailsObject>(0);
            for (int counter_2 = 0; counter_2 < mDetailsNodeList.getLength(); counter_2++) {
                Element details_data = (Element) mDetailsNodeList.item(counter_2);
                mDetails.add(new ProjectsDetailsObject(details_data.getAttribute(NAME_TAG), getElementValue(details_data)));
            }
            mProjectsData.add(new ProjectsObject(data.getAttribute(NAME_TAG), mDetails));
        }
        return mProjectsData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<QualificationObject> parseQualificationDetails(Document doc) {
        ArrayList<QualificationObject> mQualificationData = new ArrayList<QualificationObject>(0);
        NodeList mInstitutionNodeList = doc.getElementsByTagName(INSTITUTION_TAG);
        for (int counter = 0; counter < mInstitutionNodeList.getLength(); counter++) {
            Element data = (Element) mInstitutionNodeList.item(counter);
            NodeList mDetailsNodeList = data.getElementsByTagName(QUALIFICATION_DETAILS_TAG);
            ArrayList<QualificationDetailsObject> mDetails = new ArrayList<QualificationDetailsObject>(0);
            for (int counter_2 = 0; counter_2 < mDetailsNodeList.getLength(); counter_2++) {
                Element details_data = (Element) mDetailsNodeList.item(counter_2);
                mDetails.add(new QualificationDetailsObject(details_data.getAttribute(NAME_TAG), getElementValue(details_data)));
            }
            mQualificationData.add(new QualificationObject(data.getAttribute(NAME_TAG), mDetails));
        }
        return mQualificationData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<WorkExpDetailsObject> parseWorkExpDetails(Document doc) {
        ArrayList<WorkExpDetailsObject> mWorkExpData = new ArrayList<WorkExpDetailsObject>(0);
        NodeList mWorkExpNodeList = doc.getElementsByTagName(WORK_DETAILS_TAG);
        for (int counter = 0; counter < mWorkExpNodeList.getLength(); counter++) {
            Element data = (Element) mWorkExpNodeList.item(counter);
            mWorkExpData.add(new WorkExpDetailsObject(data.getAttribute(NAME_TAG),
                    data.getAttribute(PERIOD_TAG), getElementValue(data)));
        }
        return mWorkExpData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<ReferencesDetailsObject> parseReferencesDetails(Document doc) {
        ArrayList<ReferencesDetailsObject> mData = new ArrayList<ReferencesDetailsObject>(0);
        NodeList mReferencesNodeList = doc.getElementsByTagName(REFERENCES_DETAILS_TAG);
        for (int counter = 0; counter < mReferencesNodeList.getLength(); counter++) {
            Element data = (Element) mReferencesNodeList.item(counter);
            ArrayList<String> mDetailsData = new ArrayList<String>(0);
            NodeList mDetailsNodeList = data.getElementsByTagName(ITEM_DETAILS_TAG);
            for (int counter_2 = 0; counter_2 < mDetailsNodeList.getLength(); counter_2++) {
                mDetailsData.add(getElementValue(mDetailsNodeList.item(counter_2)));
            }
            mData.add(new ReferencesDetailsObject(data.getAttribute(NAME_TAG), mDetailsData));
        }
        return mData;
    }
    /**
     * @param doc
     * @return
     */
    protected ArrayList<TestimonialsDetailsObject> parseTestimonialsDetails(Document doc) {
        ArrayList<TestimonialsDetailsObject> mData = new ArrayList<TestimonialsDetailsObject>(0);
        NodeList mTestimonialsNodeList = doc.getElementsByTagName(TESTIMONIALS_DETAILS_TAG);
        for (int counter = 0; counter < mTestimonialsNodeList.getLength(); counter++) {
            Element data = (Element) mTestimonialsNodeList.item(counter);
            ArrayList<String> mDetailsData = new ArrayList<String>(0);
            NodeList mDetailsNodeList = data.getElementsByTagName(ITEM_DETAILS_TAG);
            for (int counter_2 = 0; counter_2 < mDetailsNodeList.getLength(); counter_2++) {
                mDetailsData.add(getElementValue(mDetailsNodeList.item(counter_2)));
            }
            mData.add(new TestimonialsDetailsObject(data.getAttribute(NAME_TAG), mDetailsData));
        }
        return mData;
    }

    /**
     * @param doc
     * @return
     */
    protected ContactDetailsObject parseContactDetails(Document doc) {
        NodeList mContactNodeList = doc.getElementsByTagName(ADDRESS_TAG);
        String address = "";
        for (int counter = 0; counter < mContactNodeList.getLength(); counter++) {
            address = getElementValue(mContactNodeList.item(counter));
        }
        NodeList mPhoneNodeList = doc.getElementsByTagName(PHONE_TAG);
        String[] phone = new String[mPhoneNodeList.getLength()];
        for (int counter = 0; counter < mPhoneNodeList.getLength(); counter++) {
            phone[counter] = getElementValue(mPhoneNodeList.item(counter));
        }
        NodeList mEmailAddressNodeList = doc.getElementsByTagName(MAIL_TAG);
        String[] mail = new String[mEmailAddressNodeList.getLength()];
        for (int counter = 0; counter < mEmailAddressNodeList.getLength(); counter++) {
            mail[counter] = getElementValue(mEmailAddressNodeList.item(counter));
        }

        return new ContactDetailsObject(address, phone, mail);
    }
    /**
     * @param elem
     * @return
     */
    private final String getElementValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child
                        .getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
