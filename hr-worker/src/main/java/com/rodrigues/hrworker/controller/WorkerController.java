package com.rodrigues.hrworker.controller;

import com.rodrigues.hrworker.dao.WorkerDao;
import com.rodrigues.hrworker.dto.WorkerResponse;
import com.rodrigues.hrworker.entities.Worker;
import com.rodrigues.hrworker.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/workers")
public class WorkerController {
    @Autowired
    private WorkerService service;

    @GetMapping
    public ResponseEntity<List<WorkerResponse>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

}
