/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import java.time.LocalDate;
import java.time.LocalDateTime;


//Administrator can create users and approve reviews
//Tracks last access time, updated on successful login
public class Administrator extends User {

    //Most recent successful login timestamp, null until first login
    private LocalDateTime lastAccessDate;


    //Construct an adminisrator
    //@param email unique admin email
    //@param name admin display name
    //@param regisrtationDate must be valid

    public Administrator(final String email, final String name, final LocalDate registrationDate){

        super(email, name, registrationDate);

    }

    //@return last access timestamp, or null if never loggen in
    public LocalDateTime getLastAccessDate() {
        return lastAccessDate;
    }

    //Sets last access timestamp to now, call after successful authentication
    public void touchAccessNow(){

        this.lastAccessDate = LocalDateTime.now();
    }






}
