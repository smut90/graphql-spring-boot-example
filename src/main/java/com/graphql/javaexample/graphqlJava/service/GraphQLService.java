package com.graphql.javaexample.graphqlJava.service;

import com.graphql.javaexample.graphqlJava.service.dataFetcher.AllPostsDataFetcher;
import com.graphql.javaexample.graphqlJava.service.dataFetcher.AllUsersDataFetcher;
import com.graphql.javaexample.graphqlJava.service.dataFetcher.CommentsForPostDataFetcher;
import com.graphql.javaexample.graphqlJava.service.dataFetcher.PostDataFetcher;
import com.graphql.javaexample.graphqlJava.service.dataFetcher.PostsForUserDataFetcher;
import com.graphql.javaexample.graphqlJava.service.dataFetcher.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:users.graphql")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;

    @Autowired
    private UserDataFetcher userDataFetcher;

    @Autowired
    private AllPostsDataFetcher allPostsDataFetcher;

    @Autowired
    private PostDataFetcher postDataFetcher;

    @Autowired
    private PostsForUserDataFetcher postsForUserDataFetcher;

    @Autowired
    private CommentsForPostDataFetcher commentsForPostDataFetcher;

    @PostConstruct
    private void loadGraphQLSchema() throws IOException {
        //load graphQL schema file in resources folder
        File graphQLScheamFile = resource.getFile();
        //parse graphQL schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(graphQLScheamFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allUsers", allUsersDataFetcher)
                        .dataFetcher("allPosts", allPostsDataFetcher)
                        .dataFetcher("user", userDataFetcher)
                        .dataFetcher("post", postDataFetcher.getPost())
                ).type("User", typeWiring -> typeWiring
                        .dataFetcher("posts", postsForUserDataFetcher)
                ).type("Post", typeWiring -> typeWiring
                        .dataFetcher("author", userDataFetcher.getUserForPost())
                        .dataFetcher("comments", commentsForPostDataFetcher)
                ).type("Comment", typeWiring -> typeWiring
                        .dataFetcher("author", userDataFetcher.getUserForComment())
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
