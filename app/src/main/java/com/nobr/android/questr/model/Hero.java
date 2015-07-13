package com.nobr.android.questr.model;

import java.io.Serializable;
import java.util.List;

public class Hero implements Serializable {

    private int mId;
    private String mName;
    private int Experience;
    private List<Quest> mAcceptedQuests;
    private List<Quest> mCompletedQuests;
    private List<Quest> mIssuedQuests;

    public Hero(int id, String name, int experience, List<Quest> acceptedQuests, List<Quest> completedQuests, List<Quest> issuedQuests) {
        mId = id;
        mName = name;
        Experience = experience;
        mAcceptedQuests = acceptedQuests;
        mCompletedQuests = completedQuests;
        mIssuedQuests = issuedQuests;
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

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public List<Quest> getAcceptedQuests() {
        return mAcceptedQuests;
    }

    public void setAcceptedQuests(List<Quest> acceptedQuests) {
        mAcceptedQuests = acceptedQuests;
    }

    public void addAcceptedQuest(Quest quest) {
        mAcceptedQuests.add(quest);
    }

    public void removeAcceptedQuest(Quest quest) {
        mAcceptedQuests.remove(quest);
    }

    public void clearAcceptedQuests() {
        mAcceptedQuests.clear();
    }

    public List<Quest> getCompletedQuests() {
        return mCompletedQuests;
    }

    public void setCompletedQuests(List<Quest> completedQuests) {
        mCompletedQuests = completedQuests;
    }

    public void addCompletedQuest(Quest quest) {
        mCompletedQuests.add(quest);
    }

    public void removeCompletedQuest(Quest quest) {
        mCompletedQuests.remove(quest);
    }

    public void clearCompletedQuests() {
        mCompletedQuests.clear();
    }
    
    public List<Quest> getIssuedQuests() {
        return mIssuedQuests;
    }

    public void setIssuedQuests(List<Quest> issuedQuests) {
        mIssuedQuests = issuedQuests;
    }

    public void addIssuedQuest(Quest quest) {
        mIssuedQuests.add(quest);
    }

    public void removeIssuedQuest(Quest quest) {
        mIssuedQuests.remove(quest);
    }

    public void clearIssuedQuests() {
        mIssuedQuests.clear();
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
        if (o instanceof Hero) {
            return mId == ((Hero) o).getId();
        } else {
            return false;
        }
    }

}
