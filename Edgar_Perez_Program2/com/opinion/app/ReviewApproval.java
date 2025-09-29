/*
Author: Edgar Perez
Assignment: Program 2
Class : CSC 2040
 */

package com.opinion.app;

import java.time.LocalDate;

public class ReviewApproval {

    private final UserReview userReview;
    private final Administrator administrator;
    private final LocalDate approvalDate;

    //@throws IllegalArgument Exception if any arg invalid or date is before 2010-01-01
    public ReviewApproval(final UserReview userReview, final Administrator administrator, final LocalDate approvalDate) {

        if (userReview == null || administrator == null)
            throw new IllegalArgumentException("UserReview and Administrator are required");
        if (!Validators.isValidDate(approvalDate))
            throw new IllegalArgumentException("Invalid approval date");
        this.userReview = userReview;
        this.administrator = administrator;
        this.approvalDate = approvalDate;

    }

    public UserReview getUserReview() {return userReview;}
    public Administrator getAdministrator() {return administrator;}
    public LocalDate getApprovalDate() {return approvalDate;}

}