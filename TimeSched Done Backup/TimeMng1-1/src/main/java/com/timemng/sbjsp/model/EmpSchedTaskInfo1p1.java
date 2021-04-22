// project : Time Schedule, author : Ingrid Farkas, 2020
package com.timemng.sbjsp.model;

// EmpSchedTaskInfo - model class (the class representing the data of the record in the task table)
public class EmpSchedTaskInfo1p1 {

	private Long taskId; // the id of the task
    private String taskName; // the name of the task
    private String taskDate; // the date of the task
    private String taskStartTime; // the start time of the task
    private String taskEndTime; // the end time of the task
   
    // constructor of the class
    public EmpSchedTaskInfo1p1(Long taskId, String taskName, String taskDate, String taskStartTime, String taskEndTime) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }
 
    // get the task id of the task
    public Long getTaskId() {
        return taskId;
    }
 
    // set the task id of the task
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
 
    // get the task name of the task
    public String getTaskName() {
        return taskName;
    }
 
    // set the task name of the task
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    // get the date of the task (form : dd/mm/yyyy)
    public String getTaskDate() {
        return dateForm(taskDate);
    }
 
    // set the date of the task
    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
    
    // converts the date from the format yyyy-mm-dd (which is used by the SQL Server) to the format dd/mm/yyyy (show on the form)
    public String dateForm(String dateDB) {
    	String sYear; // the year
    	String sMonth; // the month
    	String sDay; // the day
    	String sDate; // the date in format dd/mm/yyyy
    	sYear = dateDB.substring(0, 4); // retrieving the year from the string
    	sMonth = dateDB.substring(5, 7); // retrieving the month
    	sDay = dateDB.substring(8); // retrieving the day from  the string
    	sDate = sDay + "/" + sMonth + "/" + sYear;
    	return sDate; // return the date in the format dd/mm/yyyy
    }
    
    // get the start time of the task
    public String getTaskStartTime() {
        return taskStartTime;
    }
 
    // set the start time of the task
    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }
    
    // get the end time of the task
    public String getTaskEndTime() {
        return taskEndTime;
    }
 
    // set the end time of the task
    public void setTadskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

}
