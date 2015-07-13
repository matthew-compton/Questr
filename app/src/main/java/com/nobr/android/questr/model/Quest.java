package com.nobr.android.questr.model;

import java.io.Serializable;

public class Quest implements Serializable {

    private int mId;
    private String mName;
    private String mDescription;
    private String mRequirement;
    private int mExperienceReward;
    private Hero mQuestGiver;
    private boolean mIsActive;

    public Quest(int id, String name, String description, String requirement, int experienceReward, Hero questGiver, boolean isActive) {
        mId = id;
        mName = name;
        mDescription = description;
        mRequirement = requirement;
        mExperienceReward = experienceReward;
        mQuestGiver = questGiver;
        mIsActive = isActive;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getRequirement() {
        return mRequirement;
    }

    public void setRequirement(String requirement) {
        mRequirement = requirement;
    }

    public int getExperienceReward() {
        return mExperienceReward;
    }

    public void setExperienceReward(int experienceReward) {
        mExperienceReward = experienceReward;
    }

    public Hero getQuestGiver() {
        return mQuestGiver;
    }

    public void setQuestGiver(Hero questGiver) {
        mQuestGiver = questGiver;
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void setIsActive(boolean isActive) {
        mIsActive = isActive;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mId;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Quest) {
            return mId == ((Quest) o).getId();
        } else {
            return false;
        }
    }

}