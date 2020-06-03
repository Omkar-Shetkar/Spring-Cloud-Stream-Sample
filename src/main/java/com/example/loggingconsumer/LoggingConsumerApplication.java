package com.example.loggingconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
@Controller
public class LoggingConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggingConsumerApplication.class, "--spring.cloud.stream.source=toStream");
    }

    @Autowired
    private StreamBridge streamBridge;

    @RequestMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@RequestBody Person person) {
        System.out.println("Sending " + person);
        streamBridge.send("toStream-out-0", person);
    }

    @Bean
    public Consumer<Person> consumer() {
        return p -> System.out.println("consumer--Received: " + p);
    }

    @Bean
    public Consumer<Person> consumer1() {
        return p -> System.out.println("consumer1--Received: " + p);
    }

//    @Bean
//    public Supplier<Person> supplier() {
//        return () -> {
//            Person person = new Person();
//            person.setName("Omkar");
//            System.out.println("Publishing: "+person);
//            return person;
//        };
//
//    }

//    @Bean
//    public Function<String, String> uppercase() {
//        System.out.println("I am called...");
//        return value -> {
//            System.out.println("Received: " + value);
//            return value.toUpperCase();
//        };
//    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
