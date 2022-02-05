package com.rodrigues.hrworker.services;

import com.rodrigues.hrworker.dao.WorkerDao;
import com.rodrigues.hrworker.dto.WorkerResponse;
import com.rodrigues.hrworker.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerDao repository;
    @Autowired
    private WorkerResponse response;

    List<WorkerResponse> listResponse = new ArrayList<>();

    public List<WorkerResponse> findAll(){
        repository.findAll().stream().forEach(worker -> listResponse.add(new WorkerResponse(response.toResponse(worker))));
        return listResponse;
    }

    public ResponseEntity<WorkerResponse> findById(Long id){
        try {
            Worker obj = repository.findById(id).orElseThrow(() -> new RuntimeException());
            response.toResponse(obj);
        }catch(RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(response);
    }

}
