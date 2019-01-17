package wrappers.post;

import entities.Post;

import java.util.List;
import java.util.stream.Collectors;

public class GetPostsResponse {
    private List<GetPostResponse> posts;

    private GetPostsResponse(List<GetPostResponse> posts) {
        this.posts = posts;
    }

    public GetPostsResponse() {
    }

    public List<GetPostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<GetPostResponse> posts) {
        this.posts = posts;
    }

    public static GetPostsResponse from(List<Post> posts){
        List<GetPostResponse> postsResponse = posts.stream().map(GetPostResponse::from).collect(Collectors.toList());
        return new GetPostsResponse(postsResponse);
    }
}
