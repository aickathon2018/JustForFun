package com.example.shopranger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("person")
    @Expose
    private Person_ person;

    /**
     * No args constructor for use in serialization
     *
     */
    public Person() {
    }

    /**
     *
     * @param person
     */
    public Person(Person_ person) {
        super();
        this.person = person;
    }

    public Person_ getPerson() {
        return person;
    }

    public void setPerson(Person_ person) {
        this.person = person;
    }

}