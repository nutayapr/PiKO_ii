package com.cpe327.piko;

class StudentSchedule {
    public String scheDay;
    public String scheStartTime;
    public String scheEndTime;
    public String scheActivity;

    public StudentSchedule(){

    }

    public StudentSchedule(String day, String startTime, String endTime, String activity) {
        this.scheDay = day;
        this.scheStartTime = startTime;
        this.scheEndTime = endTime;
        this.scheActivity = activity;
    }
}
