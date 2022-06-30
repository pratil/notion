package com.notion.controller;

import com.notion.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.notion.constants.Constants.Path.*;

@RestController
@RequestMapping(DATABASE_ENDPOINT)
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = IMPORT)
    public ResponseEntity<Map<String, Integer>> importData() {
        return ResponseEntity.ok(databaseService.importData());
    }

    @RequestMapping(method = RequestMethod.POST, value = EXPORT)
    public ResponseEntity<Map<String, Integer>> exportData() {
        return ResponseEntity.ok(databaseService.exportData());
    }

}
