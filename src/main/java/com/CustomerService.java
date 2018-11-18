package com;

import com.entity.Customer;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CustomerService {

    private HashMap<String, TreeSet<Customer>> filesToCustomerMap = new HashMap<>();
    private CustomFileReader fileReader = null;
    final static Logger logger = Logger.getLogger(CustomerService.class);

    public CustomerService(String directoryPath) {
        fileReader = new CustomFileReader(directoryPath);
    }

    public HashMap<String, TreeSet<Customer>> getCustomers() {
        if(filesToCustomerMap.size() != 0) {
            return filesToCustomerMap;
        }
        try {
            return processFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesToCustomerMap;
    }

    private HashMap<String, TreeSet<Customer>> processFiles() throws IOException {
        filesToCustomerMap.clear();

        HashMap<String, List<String>> filesToLines = fileReader.readFiles();
        for(String file : filesToLines.keySet()) {
            filesToCustomerMap.put(file, processFile(filesToLines.get(file)));
        }
        return filesToCustomerMap;
    }

    private TreeSet<Customer> processFile(List<String> lines) {
        TreeSet<Customer> tree = new TreeSet<Customer>();
        JSONParser parser = new JSONParser();

            for (String line : lines) {
                Object obj = null;
                try {
                    obj = parser.parse(line);
                } catch (ParseException jex) {
                    logger.error("Invalid json object : " + line);
                }
                if (obj != null) {
                    JSONObject jsonObject = (JSONObject) obj;

                    long id = (Long) jsonObject.get("user_id");
                    String name = (String) jsonObject.get("name");
                    String lonVal = (String) jsonObject.get("longitude");
                    double longitude = lonVal == null ? 0 : Double.parseDouble(lonVal);
                    String latVal = (String) jsonObject.get("latitude");
                    double altitude = latVal == null ? 0 : Double.parseDouble(latVal);

                    Customer customer = new Customer(id, name, longitude, altitude);
                    tree.add(customer);
                }
            }
        return tree;
    }
}
