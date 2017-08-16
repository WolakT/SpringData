package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @RequestMapping("/customer/findall")
    public Iterable<Customer> findAll() {
        return customerService.getAll();
    }

    @RequestMapping("/customer/findall/{lastName}")
    public Iterable<Customer> findByLastName(@PathVariable("lastName") String lastName) {
        return customerService.findByLastName(lastName);
    }
}
