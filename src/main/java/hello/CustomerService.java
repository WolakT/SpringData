package hello;

import org.hibernate.usertype.CompositeUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        return customerRepository.save(customer);
    }

    public void delete(long id) {
        customerRepository.delete(id);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findOne(long id) {
        return customerRepository.findOne(id);
    }

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Iterable<Customer> findByLastName(String lastName){
        return customerRepository.findByLastName(lastName);
    }


}
