package endpoints.implementation;

import com.googlecode.objectify.Key;
import entities.Post;
import repo.Repo;
import utils.Utils;
import wrappers.KeyWrapper;
import wrappers.post.AddPostRequest;
import wrappers.post.EditPostRequest;
import wrappers.post.GetPostResponse;

import static utils.Utils.checkNotNull;

public class PostApiImpl {
    private Repo repo;

    public PostApiImpl(Repo repo) {
        this.repo = repo;
    }

    public KeyWrapper addPost(AddPostRequest addPostRequest){
        Post post = Post.from(addPostRequest);
        String postKey = repo.save(post).toWebSafeString();
        return KeyWrapper.From(postKey);
    }

    public void deletePost(String postKey) {
        repo.delete(postKey);
    }

    public GetPostResponse getPost(String postKey) {
        Post post = repo.load(postKey);
        return GetPostResponse.from(post);
    }

    public void editPost(String postKey, EditPostRequest editPostRequest) {
        Post post = repo.load(postKey);
        post.setContent(editPostRequest.getContent());
        repo.save(post);
    }

    public void upVote(String postKey) {
        Post post = repo.load(postKey);
        post.incrementUpVote();
        post.incrementTotalVotes();
        repo.save(post);
    }

    public void downVote(String postKey) {
        Post post = repo.load(postKey);
        post.decrementdownVote();
        post.decrementTotalVote();
        repo.save(post);
    }
}
