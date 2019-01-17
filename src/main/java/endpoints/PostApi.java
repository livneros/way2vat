package endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import endpoints.implementation.PostApiImpl;
import repo.Repo;
import wrappers.KeyWrapper;
import wrappers.post.AddPostRequest;
import wrappers.post.EditPostRequest;
import wrappers.post.GetPostResponse;

import static com.google.api.server.spi.config.ApiMethod.HttpMethod.*;

@Api(
        name = "way2vat",
        version = "v1"
)
public class PostApi {

    private Repo repo = new Repo();
    private PostApiImpl impl = new PostApiImpl(repo);

    @ApiMethod(
            name = "addPost",
            path = "/post",
            httpMethod = POST
    )
    public KeyWrapper addPost(AddPostRequest addPostRequest){
        return impl.addPost(addPostRequest);
    }

    @ApiMethod(
            name = "deletePost",
            path = "/post/{postKey}",
            httpMethod = DELETE
    )
    public void deletePost(@Named("postKey") String postKey){
        impl.deletePost(postKey);
    }

    @ApiMethod(
            name = "getPost",
            path = "/post/{postKey}",
            httpMethod = GET
    )
    public GetPostResponse getPost(@Named("postKey") String postKey){
        return impl.getPost(postKey);
    }

    @ApiMethod(
            name = "editPost",
            path = "/post/{postKey}",
            httpMethod = PUT
    )
    public void editPost(@Named("postKey") String postKey, EditPostRequest editPostRequest){
        impl.editPost(postKey, editPostRequest);
    }
    @ApiMethod(
            name = "upVote",
            path = "/post/{postKey}/upVote",
            httpMethod = POST
    )
    public void upVote(@Named("postKey") String postKey){
        impl.upVote(postKey);
    }
    @ApiMethod(
            name = "downVote",
            path = "/post/{postKey}/downVote",
            httpMethod = POST
    )
    public void downVote(@Named("postKey") String postKey){
        impl.downVote(postKey);
    }
}
