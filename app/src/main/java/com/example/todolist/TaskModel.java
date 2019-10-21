package com.example.todolist;



public class TaskModel  {
    String keytask;
    String titletask;
    String datetask;
    String desctask;

    public TaskModel() { //bunun olaması lazım araştır

    }


    public TaskModel(String titleTask, String dateTask, String descTask,String key) {
        this.titletask = titleTask;
        this.datetask = dateTask;
        this.desctask = descTask;
        this.keytask = key;
    }

    public String getTitleTask() {
        return titletask;
    }

    public void setTitleTask(String titleTask) {
        this.titletask = titleTask;
    }

    public String getDateTask() {
        return datetask;
    }

    public void setDateTask(String dateTask) {
        this.datetask = dateTask;
    }

    public String getDescTask() {
        return desctask;
    }

    public void setDescTask(String descTask) {
        this.desctask = descTask;
    }

    public String getKeytask() {
        return keytask;
    }

    public void setKeytask(String keytask) {
        this.keytask = keytask;
    }
}
