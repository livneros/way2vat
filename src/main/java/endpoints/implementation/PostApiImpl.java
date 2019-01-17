package endpoints.implementation;

import com.google.api.server.spi.response.BadRequestException;
import entities.Post;
import repo.Repo;
import utils.Utils;
import wrappers.KeyWrapper;
import wrappers.post.AddPostRequest;
import wrappers.post.EditPostRequest;
import wrappers.post.GetPostResponse;
import wrappers.post.GetPostsResponse;

import java.util.List;



public class PostApiImpl {

    private static final Integer PAGE_SIZE = 2;
    private static final String PAGINATION_BY = "creationDate";
    private Repo repo;

    public PostApiImpl(Repo repo) {
        this.repo = repo;
    }

    public KeyWrapper addPost(AddPostRequest addPostRequest){
        Post post = Post.from(addPostRequest);
        return savePost(post);
    }

    KeyWrapper savePost(Post post) {
        String postKey = repo.save(post);
        return KeyWrapper.From(postKey);
    }

    public void deletePost(String postKey) throws BadRequestException {
        repo.delete(postKey);
    }

    public GetPostResponse getPost(String postKey) throws BadRequestException {
        Post post = repo.load(postKey);
        return GetPostResponse.from(post);
    }

    public void editPost(String postKey, EditPostRequest editPostRequest) throws BadRequestException {
        Post post = repo.load(postKey);
        post.setContent(editPostRequest.getContent());
        repo.save(post);
    }

    public void upVote(String postKey) throws BadRequestException {
        Post post = repo.load(postKey);
        post.incrementUpVote();
        post.incrementTotalVotes();
        repo.save(post);
    }

    public void downVote(String postKey) throws BadRequestException {
        Post post = repo.load(postKey);
        post.decrementdownVote();
        post.decrementTotalVote();
        repo.save(post);
    }

    public GetPostsResponse getPosts() {
        List<Post> posts = repo.load(Post.class);
        return GetPostsResponse.from(posts);
    }

    public GetPostsResponse getPostsFrom(long from) {
        List<Post> posts = repo.load(Post.class, PAGE_SIZE, new Repo.FilterPair(PAGINATION_BY + " <", from));
        return GetPostsResponse.from(posts);
    }
}

