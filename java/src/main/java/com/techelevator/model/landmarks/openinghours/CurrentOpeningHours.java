package com.techelevator.model.landmarks.openinghours;

import com.techelevator.model.landmarks.openinghours.periods.Periods;

import java.util.List;

public class CurrentOpeningHours {

    private String openNow;

    private Periods periods;

    private List<String> weekdayDescriptions;

    public String getOpenNow() {
        return openNow;
    }

    public void setOpenNow(String openNow) {
        this.openNow = openNow;
    }

    public Periods getPeriods() {
        return periods;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    public List<String> getWeekdayDescriptions() {
        return weekdayDescriptions;
    }

    public void setWeekdayDescriptions(List<String> weekdayDescriptions) {
        this.weekdayDescriptions = weekdayDescriptions;
    }
}
