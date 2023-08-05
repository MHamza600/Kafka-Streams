package com.example.demo.consumer;

import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.demo.model.EventPage;

@Service
public class consumer {
	@Bean
	public Consumer<EventPage> eventPageConsumer(){
		return (input)->{
			System.out.println("*********************************");
			System.out.println(input.getName());
			System.out.println("*********************************");
			System.out.println(input.getUser());
			System.out.println("*********************************");
		};
	}
	@Bean
	public Supplier<EventPage> eventPageSupplier(){
		return() -> new EventPage(Math.random()>0.5?"X1":"X3",Math.random()>0.5?"A1":"A3" , new Date(), new Random().nextInt(99999)); 
}
	
	@Bean
	public Function<EventPage,EventPage> eventPageStreamConsumer(){
		return (input)->{
			return new EventPage(input.getName(), input.getUser(), new Date(), input.getDuration()+ new Random().nextInt(100));
		};
	}
}
