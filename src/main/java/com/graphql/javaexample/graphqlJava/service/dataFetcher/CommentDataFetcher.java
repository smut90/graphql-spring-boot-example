package com.graphql.javaexample.graphqlJava.service.dataFetcher;

import com.graphql.javaexample.graphqlJava.dao.CommentDao;
import com.graphql.javaexample.graphqlJava.model.Comment;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDataFetcher implements DataFetcher<Comment> {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment get(DataFetchingEnvironment environment) {
        String commentId = environment.getArgument("id");
        return commentDao.getComment(commentId);
    }
}
