package com.notion.service;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public String ping() {
        return "200, Success";
    }

}
