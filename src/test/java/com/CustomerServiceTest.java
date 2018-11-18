package com;

import com.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private final String invalidPath = "asddsf/odjioj";
    private CustomerService invalidCustomerService = null;

    private final String emptyPath = "src/test/recources/empty";
    private CustomerService emptyCustomerService = null;

    private final String invalidJsonFilePath = "src/test/recources/invalidjson";
    private CustomerService invalidJsonFileCustomerService = null;

    private final String multipleFilesPath = "src/test/recources/multiple";
    private CustomerService multipleFilesCustomerService= null;

    @Before
    public void setUp() throws Exception {
        invalidCustomerService = new CustomerService(invalidPath);
        emptyCustomerService = new CustomerService(emptyPath);
        invalidJsonFileCustomerService = new CustomerService(invalidJsonFilePath);
        multipleFilesCustomerService = new CustomerService(multipleFilesPath);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCustomersFromInvalidDirectory() {

        HashMap<String, TreeSet<Customer>> map = invalidCustomerService.getCustomers();
        assert map.size() == 0;
    }

    @Test
    public void getCustomersFromEmptyDirectory() {

        HashMap<String, TreeSet<Customer>> map = emptyCustomerService.getCustomers();
        assert map.size() == 0;
    }

    @Test
    public void getCustomersFromReadingInvalidFile() {

        HashMap<String, TreeSet<Customer>> map = invalidJsonFileCustomerService.getCustomers();
        assert map.size() == 1;
        assert map.get("invalid.txt").size() == 0;
    }

    @Test
    public void getCustomersFromMultipleDifferentFiles() {

        HashMap<String, TreeSet<Customer>> map = multipleFilesCustomerService.getCustomers();
        assert map.size() == 3;
        assert map.get("file1.txt").size() == 0;
        assert map.get("file2.txt").size() == 2;
        assert map.get("file3.txt").size() == 7;
    }

    @Test
    public void getRandomCustomerFromMultipleDifferentFiles() {
        HashMap<String, TreeSet<Customer>> map = multipleFilesCustomerService.getCustomers();
        for(Customer c : map.get("file3.txt")){
            if(c.getId() == 8){
                assert c.getName().equals("Eoin Ahearn");
            }
        }
    }
}