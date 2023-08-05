package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EventPage;

@RestController
@RequestMapping("/api/kafka")
public class Controller {

	@Autowired
	private StreamBridge streamBridge;
	
	@GetMapping("/publish/{topic}/{name}")
    public EventPage publish(@PathVariable String topic, @PathVariable String name){
       EventPage page = new EventPage(name, Math.random()>0.5?"A1":"A3", new Date(), new Random().nextInt(99999));
       streamBridge.send(topic, page);
       return page;
    }
}
