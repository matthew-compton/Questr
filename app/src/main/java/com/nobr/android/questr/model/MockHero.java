package com.nobr.android.questr.model;

import java.util.ArrayList;
import java.util.List;

public class MockHero extends Hero {

    public static final int DEFAULT_ID = 0;
    public static final String DEFAULT_NAME = "Nobr";
    public static final int DEFAULT_EXPERIENCE = 0;
    public static final List<Quest> DEFAULT_ACCEPTED_QUESTS = new ArrayList<>();
    public static final List<Quest> DEFAULT_COMPLETED_QUESTS = new ArrayList<>();
    public static final List<Quest> DEFAULT_ISSUED_QUESTS = new ArrayList<>();

    public MockHero(int id, String name, int experience, List<Quest> acceptedQuests, List<Quest> completedQuests, List<Quest> issuedQuests) {
        super(id, name, experience, acceptedQuests, completedQuests, issuedQuests);
    }

    public MockHero() {
        this(
                DEFAULT_ID,
                DEFAULT_NAME,
                DEFAULT_EXPERIENCE,
                DEFAULT_ACCEPTED_QUESTS,
                DEFAULT_COMPLETED_QUESTS,
                DEFAULT_ISSUED_QUESTS
        );
    }

}
