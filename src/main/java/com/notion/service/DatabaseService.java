package com.notion.service;

import com.notion.repositry.BlockRepository;
import com.notion.repositry.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.notion.constants.Constants.ROOT_PATH;

@Service
public class DatabaseService {

    private final Map<MongoRepository<? extends Serializable, ?>, String> map;

    private static final String FILE_NAME = ROOT_PATH + "/src/main/resources/static/backup/%s.txt";
    private static final String PAGES = "pages";
    private static final String BLOCK = "block";

    @Autowired
    public DatabaseService(PagesRepository pagesRepository, BlockRepository blockRepository) {
        map = new HashMap<>();
        map.put(pagesRepository, PAGES);
        map.put(blockRepository, BLOCK);
    }

    @PostConstruct
    public void init() {
        importData();
    }

    public Map<String, Integer> exportData() {
        Map<String, Integer> count = new HashMap<>();
        map.forEach((key, value) -> count.put(value, writeDataToFile(key, value)));
        return count;
    }

    private <Id, Data extends Serializable> int writeDataToFile(MongoRepository<Data, Id> mongoRepository, String identifier) {
        List<Data> list = mongoRepository.findAll();
        if (list.isEmpty())
            return 0;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(String.format(FILE_NAME, identifier));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            return list.size();
        } catch (Exception ignored) {

        }
        return 0;
    }

    public Map<String, Integer> importData() {
        Map<String, Integer> count = new HashMap<>();
        map.forEach((key, value) -> count.put(value, readDataFromFile(key, value)));
        return count;
    }

    @SuppressWarnings(value = "unchecked")
    private <Id, Data extends Serializable> int readDataFromFile(MongoRepository<Data, Id> mongoRepository, String identifier) {
        List<Data> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(String.format(FILE_NAME, identifier));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (List<Data>) objectInputStream.readObject();
            mongoRepository.saveAll(list);
            objectInputStream.close();
        } catch (Exception ignored) {

        }
        return list.size();
    }
}