/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import java.time.LocalDate;

//Reviewer can create UserReviews; must declare a specialization
public class Reviewer extends User {

    private final String specialization;


     //@param email unique reviewer email
     //@param name reviewer name
     //@param registrationDate >= 2010-01-01
     //@param specialization 10..300 characters

    public Reviewer(final String email,
                    final String name,
                    final LocalDate registrationDate,
                    final String specialization) {
        super(email, name, registrationDate);
        if (!Validators.isValidSpecialization(specialization)) {
            throw new IllegalArgumentException("Invalid specialization length");
        }
        this.specialization = specialization;
    }

    //@return reviewer specialization
    public String getSpecialization() { return specialization; }
}
