package kz.ravshanbekn.todo.backend.micro.todoeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TodoEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoEurekaServerApplication.class, args);
    }

}
