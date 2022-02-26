package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer{
    public  final String firstName;
    public final  String lastName;
    public final String email;

    public final String emailRegEx = "^(.+)@(.+).(.+)$";
    
    public Customer(final String fName, final String lName,final String email)
    {

        this.firstName=fName;
        this.lastName=lName;
        this.email=email;
        if (this.email=="") {
        	throw new IllegalArgumentException("empty email input has been provided");
        }
        Pattern pattern = Pattern.compile(emailRegEx);
        if(!pattern.matcher(email).matches())
        {
            throw new IllegalArgumentException("Invalid mail ID!!.Please enter in valid format name@doamin.com");
        }
    }

    public String getEmail()
    {
        return this.email;
    }
    public String toString()
    {
        return "First name: "+ this.firstName + "\nLast name: " + this.lastName + "\nEmail: " + this.email+"\n";
    }

    public String getLastName()
    {
        return this.lastName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        else {
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(emailRegEx, customer.emailRegEx);
    }
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }

}