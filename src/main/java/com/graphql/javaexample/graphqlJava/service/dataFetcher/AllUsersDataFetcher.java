package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.UserDao;
import com.graphql.javaexample.graphqlJava.model.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return userDao.getAllUsers();
    }
}
