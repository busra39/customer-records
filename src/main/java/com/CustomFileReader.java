package com;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomFileReader {

    private String directory;
    final static Logger logger = Logger.getLogger(CustomFileReader.class);

    public CustomFileReader(String directoryPath) {
        this.directory = directoryPath;
    }

    private List<String> getFileList() throws IOException{
        try {
            return Files.list(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .map(f -> f.getFileName().toString())
                    .filter(f -> f.endsWith(".txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Invalid directory : [" + directory + "]");
            throw new IOException();
        }
    }

    public HashMap<String, List<String>> readFiles() throws IOException{
        HashMap<String, List<String>> fileLinesMap = new HashMap<>();
        List<String> fileList =  null;
        try {
            fileList = getFileList();
        } catch (IOException e) {
            throw new IOException();
        }

        for(String file : fileList) {
            List<String> lines = new ArrayList<>();
            try {
                Stream<String> stream = Files.lines(Paths.get(directory + "/"+ file));
                stream.forEach(line -> lines.add(line));
            } catch (FileNotFoundException e) {
                logger.error("File not found : [" + directory + "/" + file + "]");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileLinesMap.put(file, lines);
        }
        return fileLinesMap;
    }
}
