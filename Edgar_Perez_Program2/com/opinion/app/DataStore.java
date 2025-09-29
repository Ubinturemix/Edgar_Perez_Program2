/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
*/

package com.opinion.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DataStore {

    //Look up user by email, case-insensitive
    //@param email email string
    //@return optional present when a user with email exists
    Optional<User> findUserByEmail(String email);

    //Add new user
    User addUser(User user);

    //Update existing user
    User updateUser(User user);

    //Add user review, only one review per reviewer for a product name
    UserReview addUserReview(UserReview ur); // fixed typo

    //Search over review name or comment, case-insensitive
    List<UserReview> searchUserReviews(String substring);

    //Create approval entry and attach it to user review
    ReviewApproval addApproval(UserReview ur, Administrator admin, LocalDate date);

    //Subscribe an observer that will be notified of all add/update events
    void addObserver(DataStoreObserver observer);
}
