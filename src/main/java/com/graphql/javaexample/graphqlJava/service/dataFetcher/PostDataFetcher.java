package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.PostDao;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDataFetcher {

    @Autowired
    private PostDao postDao;

    public DataFetcher getPost() {
        return dataFetchingEnvironment -> {
            String postId = dataFetchingEnvironment.getArgument("id");
            return postDao.getPost(postId);
        };
    }
}
