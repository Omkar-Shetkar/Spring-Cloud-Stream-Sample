package com.example.loggingconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class LoggingConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggingConsumerApplication.class, args);
    }


    @Bean
    public Consumer<Person> consumer() {
        return p -> System.out.println("Received: " + p);
    }

    @Bean
    public Supplier<Person> supplier() {
        return () -> {
            Person person = new Person();
            person.setName("Omkar");
            System.out.println("Publishing: "+person);
            return person;
        };

    }

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
