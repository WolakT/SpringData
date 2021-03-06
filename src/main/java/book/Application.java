package book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by RENT on 2017-08-16.
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(hello.Application.class);

    public static void main(String[] args) {
        SpringApplication.run(book.Application.class);

    }
}
