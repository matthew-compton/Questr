package com.nobr.android.questr.model;

import org.junit.Before;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class QuestTest {

    private Quest mQuest;

    @Before
    public void setup() {
        mQuest = new MockQuest();
    }

    @Test
    public void testId() {
        assertThat(mQuest.getId()).isEqualTo(MockQuest.DEFAULT_ID);

        int expectedId = 1;
        mQuest.setId(expectedId);
        assertThat(mQuest.getId()).isEqualTo(expectedId);
    }

    @Test
    public void testName() {
        assertThat(mQuest.getName()).isEqualTo(MockQuest.DEFAULT_NAME);

        String expectedName = "Save the Queen";
        mQuest.setName(expectedName);
        assertThat(mQuest.getName()).isEqualTo(expectedName);
    }

    @Test
    public void testDescription() {
        assertThat(mQuest.getDescription()).isEqualTo(MockQuest.DEFAULT_DESCRIPTION);

        String expectedDescription = "Go to a windmill and find a queen";
        mQuest.setDescription(expectedDescription);
        assertThat(mQuest.getDescription()).isEqualTo(expectedDescription);
    }

    @Test
    public void testRequirement() {
        assertThat(mQuest.getRequirement()).isEqualTo(MockQuest.DEFAULT_REQUIREMENT);

        String expectedRequirement = "Take a picture of the queen.";
        mQuest.setRequirement(expectedRequirement);
        assertThat(mQuest.getRequirement()).isEqualTo(expectedRequirement);
    }

    @Test
    public void testExperienceReward() {
        assertThat(mQuest.getExperienceReward()).isEqualTo(MockQuest.DEFAULT_EXPERIENCE_REWARD);

        int expectedExperience = 500;
        mQuest.setExperienceReward(expectedExperience);
        assertThat(mQuest.getExperienceReward()).isEqualTo(expectedExperience);
    }

    @Test
    public void testQuestGiver() {
        assertThat(mQuest.getQuestGiver()).isEqualTo(MockQuest.DEFAULT_QUEST_GIVER);

        Hero expectedQuestGiver = new MockHero();
        mQuest.setQuestGiver(expectedQuestGiver);
        assertThat(mQuest.getQuestGiver()).isEqualTo(expectedQuestGiver);
    }

    @Test
    public void testIsActive() {
        assertThat(mQuest.isActive()).isEqualTo(MockQuest.DEFAULT_IS_ACTIVE);

        boolean expectedIsActive = false;
        mQuest.setIsActive(expectedIsActive);
        assertThat(mQuest.isActive()).isEqualTo(expectedIsActive);
    }

}