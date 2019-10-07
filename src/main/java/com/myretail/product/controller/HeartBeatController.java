package com.myretail.product.controller;


import com.myretail.product.domain.HeartBeatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HeartBeatController {

    @Value("${application.name}")
    String name;

    @Value("${application.version}")
    String version;

    @GetMapping(value = "/heartbeat", produces = "application/json")
    HeartBeatResponse getHeartBeat(){
        HeartBeatResponse response = new HeartBeatResponse(name,version);
        return response;
    }

}
