package com.errahouti.BabyCareApi.model;

public enum SleepType {
    DEEP,NAP;

    public static SleepType determineSleepType(int awakenings) {
        if (awakenings <= 1) {
            return DEEP;
        } else {
            return NAP;
        }
    }
}
