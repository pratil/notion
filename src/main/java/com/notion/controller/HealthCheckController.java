package com.notion.controller;

import com.notion.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok(healthCheckService.ping());
    }
}
