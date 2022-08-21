package com.example.bsef19a028_crudapppractise;

public class Contact {

     String name;
    String contact;
    String email;
     String dob;

    public Contact()
    {
        this.name = null;
        this.contact = null;
        this.email = null;
        this.dob = null;

    }
    public Contact(String name,String contact,String email,String dob) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.dob = dob;
    }

    public void setname(String name)
    {
        this.name = name;
    }
    public void setemail(String email)
    {
        this.email = email;
    }
    public void setcontact(String contact)
    {
        this.contact = contact;
    }
    public void setdob(String dob)
    {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return  "Name      :      " + name + "\n" +
                "Contact   :      " + contact + "\n" +
                "Email       :      " + email + "\n" +
                "DOB         :      " + dob;
    }

    public String getId()
    { return this.contact;
    }
}
