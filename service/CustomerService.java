package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final CustomerService SINGLETON = new CustomerService();
    public Map<String,Customer> customers = new HashMap<String,Customer>();
    public static CustomerService getSINGLETON()
    {
        return SINGLETON;
    }
    public void addCustomer(String email,String firstName,String lastName)
    {
        Customer user = new Customer(firstName,lastName,email);
        customers.put(email,user);
    }
    public Customer getCustomer(String customerEmail)
    {
        return customers.get(customerEmail);
    }
    public Collection<Customer> getAllCustomers()
    {
        return customers.values();
    }



}