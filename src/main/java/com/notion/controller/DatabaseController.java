package com.notion.controller;

import com.notion.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = "/import")
    public ResponseEntity<Map<String, Integer>> importData() {
        return ResponseEntity.ok(databaseService.importData());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/export")
    public ResponseEntity<Map<String, Integer>> exportData() {
        return ResponseEntity.ok(databaseService.exportData());
    }

}
