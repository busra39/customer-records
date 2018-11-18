package com.entity;

public class Customer implements Comparable<Customer> {

    private long id;
    private String name;
    private double latitude;
    private double longitude;

    public Customer(long id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Customer c){
        return (int)(this.getId() - c.getId());

    }

    @Override
    public String toString(){
        return "[" + this.id + ": name = " + this.name + " lat = " + this.latitude + " long = " + this.longitude + "]";
    }
}
