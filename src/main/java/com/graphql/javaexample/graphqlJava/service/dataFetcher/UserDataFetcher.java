package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.UserDao;
import com.graphql.javaexample.graphqlJava.model.Comment;
import com.graphql.javaexample.graphqlJava.model.Post;
import com.graphql.javaexample.graphqlJava.model.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(DataFetchingEnvironment environment) {
        String userName = environment.getArgument("userName");
        return userDao.getUserById(userName);
    }

    public DataFetcher getUserForPost(){
        return dataFetchingEnvironment -> {
            Post post = dataFetchingEnvironment.getSource();
            return userDao.getUserById(post.getUserId());
        };
    }

    public DataFetcher getUserForComment(){
        return dataFetchingEnvironment -> {
            Comment comment = dataFetchingEnvironment.getSource();
            return userDao.getUserById(comment.getUserId());
        };
    }
}
