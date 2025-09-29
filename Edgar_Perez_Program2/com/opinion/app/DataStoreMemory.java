/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import java.time.LocalDate;
import java.util.*;

import static java.util.Locale.ROOT;

//In-memory singleton DataStore: non-persistent, creates default admin
 //enforces uniqueness, and emits events to observers
public class DataStoreMemory implements DataStore {

    private static DataStoreMemory instance;
    public static synchronized DataStoreMemory getInstance() {
        if (instance == null) instance = new DataStoreMemory();
        return instance;
    }

    private final Map<String, User> usersByEmail = new HashMap<>();
    private final List<UserReview> userReviews = new ArrayList<>();
    private final List<DataStoreObserver> observers = new ArrayList<>();

    private DataStoreMemory() {

        //default admin: email admin@review.com, regDate = today, no last access
        Administrator admin = new Administrator("admin@review.com", "Admin", LocalDate.now());
        usersByEmail.put(key(admin.getEmail()), admin);
        emit(EventType.ADD_USER, "Default admin created", admin);
    }

    @Override
    public Optional<User> findUserByEmail(final String email) {
        return email == null ? Optional.empty() : Optional.ofNullable(usersByEmail.get(key(email)));
    }

    @Override
    public User addUser(final User user) {
        Objects.requireNonNull(user, "user");
        String k = key(user.getEmail());
        if (usersByEmail.containsKey(k)) throw new IllegalArgumentException("User already exists: " + user.getEmail());
        usersByEmail.put(k, user);
        emit(EventType.ADD_USER, "User added: " + user.getEmail(), user);
        return user;
    }

    @Override
    public User updateUser(final User user) {
        Objects.requireNonNull(user, "user");
        String k = key(user.getEmail());
        if (!usersByEmail.containsKey(k)) throw new IllegalArgumentException("No such user: " + user.getEmail());
        usersByEmail.put(k, user);
        emit(EventType.UPDATE_USER, "User updated: " + user.getEmail(), user);
        return user;
    }

    @Override
    public UserReview addUserReview(final UserReview ur) {
        Objects.requireNonNull(ur, "userReview");
        String reviewerK = key(ur.getReviewer().getEmail());
        String product = ReviewBridge.getName(ur.getReview());

        boolean exists = userReviews.stream().anyMatch(x ->
                key(x.getReviewer().getEmail()).equals(reviewerK) &&
                        ReviewBridge.getName(x.getReview()).equalsIgnoreCase(product)
        );
        if (exists) throw new IllegalArgumentException("Reviewer already reviewed this product");

        userReviews.add(ur);
        emit(EventType.ADD_USER_REVIEW,
                "UserReview added for product: " + product + " by " + ur.getReviewer().getEmail(), ur);
        return ur;
    }

    @Override
    public List<UserReview> searchUserReviews(final String substring) {
        String needle = (substring == null ? "" : substring).toLowerCase(ROOT);
        List<UserReview> out = new ArrayList<>();
        for (UserReview ur : userReviews) {
            String name = ReviewBridge.getName(ur.getReview());
            String comment = ReviewBridge.getComment(ur.getReview());
            if ((name != null && name.toLowerCase(ROOT).contains(needle)) ||
                    (comment != null && comment.toLowerCase(ROOT).contains(needle))) {
                out.add(ur);
            }
        }
        return out;
    }

    @Override
    public ReviewApproval addApproval(final UserReview ur, final Administrator admin, final LocalDate date) {
        Objects.requireNonNull(ur, "userReview");
        Objects.requireNonNull(admin, "admin");
        ReviewApproval ra = new ReviewApproval(ur, admin, date);
        ur.addApproval(ra); //package-private; same package
        emit(EventType.ADD_REVIEW_APPROVAL,
                "Approval by " + admin.getEmail() + " for " + ReviewBridge.getName(ur.getReview()), ra);
        return ra;
    }

    @Override
    public void addObserver(final DataStoreObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer"));
    }

    //---- helpers ----
    private static String key(final String email) { return email.toLowerCase(ROOT); }

    private void emit(final EventType type, final String msg, final Object payload) {
        Event e = new Event(type, msg, payload);
        for (DataStoreObserver o : observers) o.onEvent(e);
    }
}
