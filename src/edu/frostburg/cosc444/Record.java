package edu.frostburg.cosc444;

public class Record {
    private String id;
    private String districtName;
    private String neighborhood;
    private String street;
    private String weekday;
    private String month;
    private String partOfDay;
    private int victims;

    public Record(String id, String districtName, String neighborhood, String street, String weekday, String month, String partOfDay, int victims) {
        this.id = id;
        this.districtName = districtName;
        this.neighborhood = neighborhood;
        this.street = street;
        this.weekday = weekday;
        this.month = month;
        this.partOfDay = partOfDay;
        this.victims = victims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public int getVictims() {
        return victims;
    }

    public void setVictims(int victims) {
        this.victims = victims;
    }
}
