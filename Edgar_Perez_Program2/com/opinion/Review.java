package com.opinion;

/*
 Author:     Edgar Perez
 Assignment: Program 1 - Reviews, Reduced Boilerplate
 Class:      CSC 2040
 */

import java.util.Objects;

/**
 * Immutable value type representing a single product review.
 * Specification:
 * - name: 1..64 chars, printable ASCII (32..126), no '#'
 * - rating: non-null (Rating.ONE/TWO/THREE)
 * - comment: 1..254 chars, printable ASCII, no '#'
 *
 * String form:
 *   &lt;name&gt; with rating of ONE star(s): &lt;comment&gt;
 */
public record Review(String name, Rating rating, String comment) {

    private static final int MAX_NAME_LEN = 64;
    private static final int MAX_COMMENT_LEN = 254;

    public Review {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(rating, "rating");
        Objects.requireNonNull(comment, "comment");

        if (name.length() < 1 || name.length() > MAX_NAME_LEN) {
            throw new IllegalArgumentException("Bad format: name length out of range");
        }
        if (comment.length() < 1 || comment.length() > MAX_COMMENT_LEN) {
            throw new IllegalArgumentException("Bad format: comment length out of range");
        }

        ensurePrintableAsciiNoHash("name", name);
        ensurePrintableAsciiNoHash("comment", comment);
    }

    public static Review create(String rawName, String rawRating, String rawComment) {
        Objects.requireNonNull(rawName, "Bad format: missing name");
        Objects.requireNonNull(rawRating, "Bad format: missing rating");
        Objects.requireNonNull(rawComment, "Bad format: missing comment");

        final Rating rating = Rating.valueOf(rawRating);
        return new Review(rawName, rating, rawComment);
    }

    private static void ensurePrintableAsciiNoHash(String label, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                throw new IllegalArgumentException("Bad format: " + label + " contains '#'");
            }
            if (c < 32 || c > 126) {
                throw new IllegalArgumentException("Bad format: " + label + " contains non-printable ASCII");
            }
        }
    }

    @Override
    public String toString() {
        return name + " with rating of " + rating.name() + " star(s): " + comment;
    }
}
