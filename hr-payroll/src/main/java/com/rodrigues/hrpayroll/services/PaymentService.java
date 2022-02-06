package com.rodrigues.hrpayroll.services;

import com.rodrigues.hrpayroll.entities.Payment;
import com.rodrigues.hrpayroll.entities.Worker;
import com.rodrigues.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    //chamar o endereço mockado no applicarion
    //@Value("${hr-worker.host}")
    //private String workerHost;

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days){
       //Metodo usando o template
       //Map<String, String> uriVariables = new HashMap<>();
       //uriVariables.put("id", "" + workerId);
       //Worker worker = restTemplate.getForObject(workerHost  + "/workers/{id}",Worker.class, uriVariables);

        Worker worker = workerFeignClient.findById(workerId).getBody();
        return  new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
