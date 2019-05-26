package com.graphql.javaexample.graphqlJava.dao;

import com.graphql.javaexample.graphqlJava.model.Comment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CommentDao {

    private List<Comment> comments = new ArrayList<>();

    @PostConstruct
    private void loadData(){
        Stream.of(
                new Comment("C1", "Awesome post @U1", "U1", "P1"),
                new Comment("C2", "Wow @u1", "U1", "P1"),
                new Comment("C3", "whattt!! @U1", "U1", "P2"),
                new Comment("C4", "@U3 yeahhh", "U3", "P4"),
                new Comment("C5", "@U7 so true...!!!", "U7", "P8"),
                new Comment("C6", "@U7 hahahaa LOL", "U7", "P8")
        ).forEach(comment -> comments.add(comment));
    }

    public List<Comment> getAllComments(){
        return comments;
    }

    public Comment getComment(String id) {
        return comments
                .stream()
                .filter(comment -> comment.getId().equals(id))
                .findAny()
                .get();
    }

    public List<Comment> getAllCommentsForAPost(String postId){
        return comments
                .stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }
}
