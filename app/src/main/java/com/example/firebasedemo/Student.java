package com.example.firebasedemo;

public class Student {
    private String ID;
    private  String Name;
    private  String Add;
    private  int ConyNo;

    public Student() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }

    public int getConyNo() {
        return ConyNo;
    }

    public void setConyNo(int conyNo) {
        ConyNo = conyNo;
    }
}
