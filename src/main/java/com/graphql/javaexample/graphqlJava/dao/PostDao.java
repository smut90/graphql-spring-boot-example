package com.graphql.javaexample.graphqlJava.dao;

import com.graphql.javaexample.graphqlJava.model.Post;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PostDao {

    private List<Post> posts = new ArrayList<>();

    @PostConstruct
    private void loadData(){
        Stream.of(
                new Post("P1","U1 first post", "This is the first post of U1", "U1"),
                new Post("P2","U1 second post", "This is the second post of U1", "U1"),
                new Post("P3","U2 first post", "This is the first post of U2", "U2"),
                new Post("P4","U3 first post", "This is the first post of U3", "U3"),
                new Post("P5","U3 second post", "This is the second post of U3", "U3"),
                new Post("P6","U3 third post", "This is the third post of U3", "U3"),
                new Post("P7","U4 first post", "This is the first post of U4", "U4"),
                new Post("P8","U7 first post", "This is the first post of U7", "U7")
        ).forEach(post-> posts.add(post));
    }

    public List<Post> getAllPosts(){
        return posts;
    }

    public Post getPost(String id) {
        return posts
                .stream()
                .filter(post -> post.getId().equals(id))
                .findAny()
                .get();
    }

    public List<Post> getAllPostsForAnUser(String userId){
        return posts
                .stream()
                .filter(post -> post.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
