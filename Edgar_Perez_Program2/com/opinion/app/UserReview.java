/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import com.opinion.Review;

import java.util.*;

public class UserReview {

    //Program 1 review, product, rating, comment
    private final Review review;
    //review author
    private final Reviewer reviewer;
    private final List<ReviewApproval> approvals = new ArrayList<>();


    //@throws IllegalArgumentException if review or reviewer is null
    public UserReview(final Review review, final Reviewer reviewer) {
        if (review == null || reviewer == null)
            throw new IllegalArgumentException("Review and Reviewer can't be null");
        this.review = review;
        this.reviewer = reviewer;
    }

    public Review getReview() {return review;}
    public Reviewer getReviewer() {return reviewer;}

    public List<ReviewApproval> getApprovals() {
        return Collections.unmodifiableList(approvals);
    }

    void addApproval(final ReviewApproval a) {
        approvals.add(Objects.requireNonNull(a, "approval"));

    }
}