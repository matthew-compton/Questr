package com.nobr.android.questr.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.assertj.core.api.Assertions.assertThat;

public class HeroTest {

    private Hero mHero;

    @Before
    public void setup() {
        mHero = new MockHero();
    }

    @Test
    public void testId() {
        assertThat(mHero.getId()).isEqualTo(MockHero.DEFAULT_ID);

        int expectedId = 1;
        mHero.setId(expectedId);
        assertThat(mHero.getId()).isEqualTo(expectedId);
    }

    @Test
    public void testName() {
        assertThat(mHero.getName()).isEqualTo(MockHero.DEFAULT_NAME);

        String expectedName = "Matt";
        mHero.setName(expectedName);
        assertThat(mHero.getName()).isEqualTo(expectedName);
    }

    @Test
    public void testExperience() {
        assertThat(mHero.getExperience()).isEqualTo(MockHero.DEFAULT_EXPERIENCE);

        int expectedExperience = 1;
        mHero.setExperience(expectedExperience);
        assertThat(mHero.getExperience()).isEqualTo(expectedExperience);
    }

    @Test
    public void testActiveQuests() {
        assertThat(mHero.getAcceptedQuests()).isEqualTo(MockHero.DEFAULT_ACCEPTED_QUESTS);
        assertThat(mHero.getAcceptedQuests()).isEmpty();

        mHero.addAcceptedQuest(new MockQuest());
        assertThat(mHero.getAcceptedQuests()).hasSize(1);

        mHero.removeAcceptedQuest(new MockQuest());
        assertThat(mHero.getAcceptedQuests()).isEmpty();

        mHero.addAcceptedQuest(new MockQuest());
        assertThat(mHero.getAcceptedQuests()).hasSize(1);

        mHero.clearAcceptedQuests();
        assertThat(mHero.getAcceptedQuests()).isEmpty();

        mHero.addAcceptedQuest(new MockQuest());
        assertThat(mHero.getAcceptedQuests()).hasSize(1);

        mHero.setAcceptedQuests(new ArrayList<>());
        assertThat(mHero.getAcceptedQuests()).isEmpty();
    }

    @Test
    public void testCompletedQuests() {
        assertThat(mHero.getCompletedQuests()).isEqualTo(MockHero.DEFAULT_COMPLETED_QUESTS);
        assertThat(mHero.getCompletedQuests()).isEmpty();

        mHero.addCompletedQuest(new MockQuest());
        assertThat(mHero.getCompletedQuests()).hasSize(1);

        mHero.removeCompletedQuest(new MockQuest());
        assertThat(mHero.getCompletedQuests()).isEmpty();

        mHero.addCompletedQuest(new MockQuest());
        assertThat(mHero.getCompletedQuests()).hasSize(1);

        mHero.clearCompletedQuests();
        assertThat(mHero.getCompletedQuests()).isEmpty();

        mHero.addCompletedQuest(new MockQuest());
        assertThat(mHero.getCompletedQuests()).hasSize(1);

        mHero.setCompletedQuests(new ArrayList<>());
        assertThat(mHero.getCompletedQuests()).isEmpty();
    }

    @Test
    public void testIssuedQuests() {
        assertThat(mHero.getIssuedQuests()).isEqualTo(MockHero.DEFAULT_ISSUED_QUESTS);
        assertThat(mHero.getIssuedQuests()).isEmpty();

        mHero.addIssuedQuest(new MockQuest());
        assertThat(mHero.getIssuedQuests()).hasSize(1);

        mHero.removeIssuedQuest(new MockQuest());
        assertThat(mHero.getIssuedQuests()).isEmpty();

        mHero.addIssuedQuest(new MockQuest());
        assertThat(mHero.getIssuedQuests()).hasSize(1);

        mHero.clearIssuedQuests();
        assertThat(mHero.getIssuedQuests()).isEmpty();

        mHero.addIssuedQuest(new MockQuest());
        assertThat(mHero.getIssuedQuests()).hasSize(1);

        mHero.setIssuedQuests(new ArrayList<>());
        assertThat(mHero.getIssuedQuests()).isEmpty();
    }

}