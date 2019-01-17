package endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.BadRequestException;
import endpoints.implementation.PostApiImpl;
import repo.Repo;
import wrappers.KeyWrapper;
import wrappers.post.AddPostRequest;
import wrappers.post.EditPostRequest;
import wrappers.post.GetPostResponse;
import wrappers.post.GetPostsResponse;

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
    public void deletePost(@Named("postKey") String postKey) throws BadRequestException {
        impl.deletePost(postKey);
    }

    @ApiMethod(
            name = "getPost",
            path = "/post/{postKey}",
            httpMethod = GET
    )
    public GetPostResponse getPost(@Named("postKey") String postKey) throws BadRequestException {
        return impl.getPost(postKey);
    }

    @ApiMethod(
            name = "getPosts",
            path = "/post",
            httpMethod = GET
    )
    public GetPostsResponse getPosts(){
        return impl.getPosts();
    }

    @ApiMethod(
            name = "getPostsFrom",
            path = "/post/from/{from}",
            httpMethod = GET
    )
    public GetPostsResponse getPostsFrom(@Named("from") long from){
        return impl.getPostsFrom(from);
    }

    @ApiMethod(
            name = "editPost",
            path = "/post/{postKey}",
            httpMethod = PUT
    )
    public void editPost(@Named("postKey") String postKey, EditPostRequest editPostRequest) throws BadRequestException {
        impl.editPost(postKey, editPostRequest);
    }
    @ApiMethod(
            name = "upVote",
            path = "/post/{postKey}/upVote",
            httpMethod = POST
    )
    public void upVote(@Named("postKey") String postKey) throws BadRequestException {
        impl.upVote(postKey);
    }
    @ApiMethod(
            name = "downVote",
            path = "/post/{postKey}/downVote",
            httpMethod = POST
    )
    public void downVote(@Named("postKey") String postKey) throws BadRequestException {
        impl.downVote(postKey);
    }
}
