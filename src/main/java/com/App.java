package com;

import com.entity.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class App {

    private final String REAL_MODE_PATH = "src/main/resources";


    public void start() {
        CustomerService customerService = new CustomerService(REAL_MODE_PATH);
        HashMap<String, TreeSet<Customer>> customersFromEachFile = customerService.getCustomers();
        List<String> result = DistanceUtil.getClosest(customersFromEachFile);
        result.stream().forEach(line -> System.out.println(line));
    }
}
