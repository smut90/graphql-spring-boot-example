package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.PostDao;
import com.graphql.javaexample.graphqlJava.model.Post;
import com.graphql.javaexample.graphqlJava.model.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsForUserDataFetcher implements DataFetcher<List<Post>> {

    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> get(DataFetchingEnvironment environment) {
        User user = environment.getSource();
        return postDao.getAllPostsForAnUser(user.getId());
    }
}
