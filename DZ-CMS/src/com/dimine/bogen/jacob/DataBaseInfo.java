package com.dimine.bogen.jacob;
public class DataBaseInfo {   
    private String column_Name;//数据名称   
    private String data_type;//数据类型   
    private String data_length;//数据长度   
    private String data_null;//数据是否为空   
    private String data_comments;//数据注释   
    public String getColumn_Name() {   
        return column_Name;   
    }   
    public void setColumn_Name(String column_Name) {   
        this.column_Name = column_Name;   
    }   
    public String getData_type() {   
        return data_type;   
    }   
    public void setData_type(String data_type) {   
        this.data_type = data_type;   
    }   
    public String getData_length() {   
        return data_length;   
    }   
    public void setData_length(String data_length) {   
        this.data_length = data_length;   
    }   
    public String getData_null() {   
        return data_null;   
    }   
    public void setData_null(String data_null) {   
        this.data_null = data_null;   
    }   
    public String getData_comments() {   
        return data_comments;   
    }   
    public void setData_comments(String data_comments) {   
        this.data_comments = data_comments;   
    }   
    public String toString(){   
        return ""+column_Name+"\r"+data_type+"\r"+data_length+"\r"+data_null+"\r"+data_comments+"\n";   
    }   
}  
