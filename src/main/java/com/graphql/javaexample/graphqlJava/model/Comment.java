package com.graphql.javaexample.graphqlJava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String text;
    private String userId;
    private String postId;
}
