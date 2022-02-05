package com.rodrigues.hrworker.dao;

import com.rodrigues.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerDao extends JpaRepository<Worker, Long> {
}
