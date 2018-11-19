package com.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer = null;
    @Before
    public void setUp() throws Exception {
        customer = new Customer(12, "Busra Canak", 34.000, -9.4444);
    }

    @Test
    public void getId() {
        assert customer.getId() == 12;
    }

    @Test
    public void getName() {
        assert customer.getName().equals("Busra Canak");
    }

    @Test
    public void getLatitude() {
        assert customer.getLatitude() == 34.000;
    }

    @Test
    public void getLongitude() {
        assert customer.getLongitude() == -9.4444;
    }

    @Test
    public void toStringMethod() {
        assert customer.toString().equals("[12: name = Busra Canak lat = 34.0 long = -9.4444]");
    }
}