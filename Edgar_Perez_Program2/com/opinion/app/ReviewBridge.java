/*
 Author: Edgar Perez
 Assignment: Program 2
 Class: CSC 2040
 */
package com.opinion.app;

import com.opinion.Rating;
import com.opinion.Review;

/*Adapter between Program 2 and Program 1's {@link Review}/{@link Rating}.
 Uses the P1 record accessors (name(), rating(), comment()).
 */
public final class ReviewBridge {

    private ReviewBridge() { }

    /*
     Builds a Program 1 Review from Program 2 inputs.
     @param productName product name
     @param ratingStars integer rating (1..3)
     @param comment comment text
     */
    public static Review buildReview(final String productName,
                                     final int ratingStars,
                                     final String comment) {
        return new Review(productName, toEnum(ratingStars), comment);
    }

    //@return product name (record accessor)
    public static String getName(final Review r) { return r.name(); }

    //@return numeric star rating (1..3) via enum
    public static int getStars(final Review r) { return r.rating().getStars(); }

    //@return comment text (record accessor)
    public static String getComment(final Review r) { return r.comment(); }

    private static Rating toEnum(final int stars) {
        return switch (stars) {
            case 1 -> Rating.ONE;
            case 2 -> Rating.TWO;
            case 3 -> Rating.THREE;
            default -> throw new IllegalArgumentException("Stars must be 1..3");
        };
    }
}
