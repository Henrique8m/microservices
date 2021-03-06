package com.rodrigues.hrworker.controller;

import com.rodrigues.hrworker.dto.WorkerResponse;
import com.rodrigues.hrworker.services.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//atualizar automaticamente a config pelo spring actuator
@RefreshScope
@Controller
@RequestMapping(value = "/workers")
public class WorkerController {

    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    private WorkerService service;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> testConfig(){
        logger.info("Config = " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WorkerResponse>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerResponse> findById(@PathVariable Long id){
        logger.info("PORT = " + environment.getProperty("local.server.port"));
        return service.findById(id);
    }

}
