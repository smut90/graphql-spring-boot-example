package com.graphql.javaexample.graphqlJava.dao;

import com.graphql.javaexample.graphqlJava.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class UserDao {

    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void loadData(){
        Stream.of(
                new User("U1","user1", "fname1", "lname1"),
                new User("U2", "user2", "fname2", "lname2"),
                new User("U3", "user3", "fname3", "lname3"),
                new User("U4", "user4", "fname4", "lname4"),
                new User("U5", "user5", "fname5", "lname5"),
                new User("U6", "user6", "fname6", "lname6"),
                new User("U7", "user7", "fname7", "lname7")
        ).forEach(user-> users.add(user));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) {
        return users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .get();
    }

    public User getUserByUserName(String userName) {
        return users
                .stream()
                .filter(user -> user.getUserName().equals(userName))
                .findAny()
                .get();
    }
}
