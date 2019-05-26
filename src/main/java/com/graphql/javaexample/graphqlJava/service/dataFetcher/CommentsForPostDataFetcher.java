package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.CommentDao;
import com.graphql.javaexample.graphqlJava.model.Comment;
import com.graphql.javaexample.graphqlJava.model.Post;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentsForPostDataFetcher implements DataFetcher<List<Comment>> {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> get(DataFetchingEnvironment environment) {
        Post post = environment.getSource();
        return commentDao.getAllCommentsForAPost(post.getId());
    }
}
