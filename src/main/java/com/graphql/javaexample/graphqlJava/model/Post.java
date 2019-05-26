package com.graphql.javaexample.graphqlJava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String id;
    private String title;
    private String text;
    private String userId;
}
