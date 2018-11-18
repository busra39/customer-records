package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CustomFileReaderTest {

    private final String invalidPath = "asddsf/odjioj";
    CustomFileReader invalidPathReader = null;

    private final String emptyPath = "src/test/recources/empty";
    CustomFileReader emptyPathReader = null;

    private final String invalidJsonFilePath = "src/test/recources/invalidjson";
    CustomFileReader invalidJsonFileReader = null;

    private final String multipleFilesPath = "src/test/recources/multiple";
    CustomFileReader multipleFilesReader = null;

    @Before
    public void setUp() throws Exception {
        invalidPathReader = new CustomFileReader(invalidPath);
        emptyPathReader = new CustomFileReader(emptyPath);
        invalidJsonFileReader =  new CustomFileReader(invalidJsonFilePath);
        multipleFilesReader =  new CustomFileReader(multipleFilesPath);
    }

    @Test
    public void readFilesFromInvalidDirectoryPath() {
        try {
            invalidPathReader.readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFilesFromEmptyDirectory() {
        try {
            HashMap<String, List<String>> emptyMap =  emptyPathReader.readFiles();
            assert emptyMap.isEmpty() == true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readInvalidJsonFile() {
        try {
            HashMap<String, List<String>> map =  invalidJsonFileReader.readFiles();
            assert map.get("invalid.txt").get(0).equals("INVALID FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readMultipleMixFiles() {
        try {
            HashMap<String, List<String>> map =  multipleFilesReader.readFiles();
            assert map.get("file1.txt").get(0).equals("INVALID FORMAT");
            assert map.size() == 3;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}