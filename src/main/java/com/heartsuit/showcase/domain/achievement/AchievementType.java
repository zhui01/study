package com.heartsuit.showcase.domain.achievement;

/**
 * The enum Achievement type.
 * @author Administrator
 */
public enum AchievementType {
    /**
     * Patent achievement type.
     */
    PATENT(PatentAchievement.class), // 专利
    /**
     * Dissertation achievement type.
     */
    DISSERTATION(DissertationAchievement.class); // 论文

    AchievementType(Class achievement) {
    }
}
