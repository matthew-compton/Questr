package com.nobr.android.questr.model;

public class MockQuest extends Quest {

    public static final int DEFAULT_ID = 0;
    public static final String DEFAULT_NAME = "Save the Princess";
    public static final String DEFAULT_DESCRIPTION = "Go to a castle and find a princess";
    public static final String DEFAULT_REQUIREMENT = "Take a picture of the princess.";
    public static final int DEFAULT_EXPERIENCE_REWARD = 1000;
    public static final Hero DEFAULT_QUEST_GIVER = new MockHero();
    public static final boolean DEFAULT_IS_ACTIVE = true;

    public MockQuest(int id, String name, String description, String requirement, int experienceReward, Hero questGiver, boolean isActive) {
        super(id, name, description, requirement, experienceReward, questGiver, isActive);
    }

    public MockQuest() {
        this(
                DEFAULT_ID,
                DEFAULT_NAME,
                DEFAULT_DESCRIPTION,
                DEFAULT_REQUIREMENT,
                DEFAULT_EXPERIENCE_REWARD,
                DEFAULT_QUEST_GIVER,
                DEFAULT_IS_ACTIVE
        );
    }

}