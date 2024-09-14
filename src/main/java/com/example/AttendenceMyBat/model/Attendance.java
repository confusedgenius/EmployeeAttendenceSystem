package com.example.AttendenceMyBat.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private long Serial_No;
    private int emp_id;
    private LocalDate check_in_date;
    private LocalTime check_in_time;
    private LocalTime check_out_time;

    public Attendance() {
    }

    public Attendance(long serial_No, int emp_id, LocalDate check_in_date, LocalTime check_in_time, LocalTime check_out_time) {
        Serial_No = serial_No;
        this.emp_id = emp_id;
        this.check_in_date = check_in_date;
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
    }

    public long getSerial_No() {
        return Serial_No;
    }

    public void setSerial_No(long serial_No) {
        Serial_No = serial_No;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate date) {
        this.check_in_date = date;
    }

    public LocalTime getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(LocalTime check_in_time) {
        this.check_in_time = check_in_time;
    }

    public LocalTime getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(LocalTime check_out_time) {
        this.check_out_time = check_out_time;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "Serial_No=" + Serial_No +
                ", emp_id=" + emp_id +
                ", check_in_date=" + check_in_date +
                ", check_in_time=" + check_in_time +
                ", check_out_time=" + check_out_time +
                '}';
    }
}
