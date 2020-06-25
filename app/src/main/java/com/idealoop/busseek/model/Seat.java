package com.idealoop.busseek.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {
    public Seat() {
    }

    String SeatId, busID, booked, username, busowner, seatno;

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public Seat(String seatId, String busID, String booked, String username, String busowner, String seatno) {
        SeatId = seatId;
        this.busID = busID;
        this.booked = booked;
        this.username = username;
        this.busowner = busowner;
        this.seatno = seatno;
    }

    public String getSeatId() {
        return SeatId;
    }

    public void setSeatId(String seatId) {
        SeatId = seatId;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBusowner() {
        return busowner;
    }

    public void setBusowner(String busowner) {
        this.busowner = busowner;
    }

    public static Creator<Seat> getCREATOR() {
        return CREATOR;
    }

    protected Seat(Parcel in) {
        SeatId = in.readString();
        busID = in.readString();
        booked = in.readString();
        username = in.readString();
        busowner = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(SeatId);
        dest.writeString(busID);
        dest.writeString(booked);
        dest.writeString(username);
        dest.writeString(busowner);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Seat> CREATOR = new Creator<Seat>() {
        @Override
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }

        @Override
        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };
}
