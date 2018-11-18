package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DistanceUtilTest {


    private final String multipleFilesPath = "src/test/recources/multiple";
    private CustomerService multipleFilesCustomerService= null;

    @Before
    public void setUp() throws Exception {
        multipleFilesCustomerService = new CustomerService(multipleFilesPath);
    }


    @Test
    public void getClosest() {
        List<String> result = DistanceUtil.getClosest(multipleFilesCustomerService.getCustomers());
        assert result.size() == 6;
        assert result.get(2).equals("4 Ian Kehoe");
    }

    @Test
    public void getClosestWithEmptyList() {
        List<String> result = DistanceUtil.getClosest(new HashMap<>());
        assert result.size() == 0;
    }
}