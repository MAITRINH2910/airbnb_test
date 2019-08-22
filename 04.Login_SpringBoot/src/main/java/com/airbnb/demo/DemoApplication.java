package com.airbnb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    //SpringApplication là câu lệnh để tạo ra container. Sau đó nó tìm toàn bộ các dependency trong project của bạn và đưa vào đó.
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
