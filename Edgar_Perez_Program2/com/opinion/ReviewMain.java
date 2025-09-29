package com.opinion;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

/*
 * Author:     Edgar Perez
 * Assignment: Program 1 - Reviews, Reduced Boilerplate
 * Class:      CSC 2040
*/

public final class ReviewMain {

    private static final int EXIT_BAD_USAGE = 2;
    private static final int EXIT_IO_ERROR = 3;
    private static final int EXIT_BAD_DATA = 4;

    private ReviewMain() { }

    public static int run(String[] args, PrintStream out, PrintStream err) {
        if (args == null || args.length != 1) {
            err.println("USAGE: java com.opinion.ReviewMain <path-to-reviews>");
            return EXIT_BAD_USAGE;
        }

        final List<Review> reviews;
        try {
            reviews = ReviewReader.read(Path.of(args[0]));
        } catch (IOException e) {
            err.println("Error reading file: " + e.getMessage());
            return EXIT_IO_ERROR;
        } catch (IllegalArgumentException e) {
            err.println(e.getMessage());
            return EXIT_BAD_DATA;
        }

        out.println(reviews.size());
        if (reviews.isEmpty()) {
            return 0;
        }

        Review lowest = reviews.get(0);
        Review highest = reviews.get(0);
        for (int i = 1; i < reviews.size(); i++) {
            Review r = reviews.get(i);
            if (r.rating().getStars() < lowest.rating().getStars()) {
                lowest = r;
            }
            if (r.rating().getStars() > highest.rating().getStars()) {
                highest = r;
            }
        }

        out.println(lowest);
        out.println(highest);
        return 0;
    }

    public static void main(String[] args) {
        System.exit(run(args, System.out, System.err));
    }
}
