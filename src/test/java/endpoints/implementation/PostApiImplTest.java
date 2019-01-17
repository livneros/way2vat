package endpoints.implementation;

import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import entities.Post;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import repo.Repo;
import utils.EntityRegistration;
import wrappers.KeyWrapper;
import wrappers.post.AddPostRequest;
import wrappers.post.GetPostResponse;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PostApiImplTest {

    private static final String WEB_SAFE_KEY = "webSafeKey";
    private static final String CONTENT = "content";
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Repo repo = mock(Repo.class);
    private PostApiImpl impl = new PostApiImpl(repo);

    @SuppressWarnings("unused")
    public void setUp() {
        EntityRegistration entityRegistration = new EntityRegistration();
        ObjectifyService.begin();
        helper.setUp();
    }

    @Test
    public void addPost_simpleBody_success() {
        Post post = mock(Post.class);
        when(repo.save(post)).thenReturn(WEB_SAFE_KEY);
        KeyWrapper keyWrapper = impl.savePost(post);
        assertEquals(WEB_SAFE_KEY, keyWrapper.getKey());
    }

    @Test
    public void getPost_success() throws Exception {
        Post mockPost = mock(Post.class);
        when(repo.load(WEB_SAFE_KEY)).thenReturn(mockPost);
        when(mockPost.getContent()).thenReturn(CONTENT);
        GetPostResponse post = impl.getPost(WEB_SAFE_KEY);
        assertEquals(mockPost.getContent(), post.getContent());
    }

    @Test
    public void upVote_increment_by_one() throws BadRequestException {
        Post post = mock(Post.class);
        when(repo.load(WEB_SAFE_KEY)).thenReturn(post);
        impl.upVote(WEB_SAFE_KEY);
        verify(post, times(1)).incrementTotalVotes();
        verify(post, times(1)).incrementUpVote();
        verify(repo, times(1)).save(post);
    }
    @Test
    public void downVote_decrement_by_one() throws BadRequestException {
        Post post = mock(Post.class);
        when(repo.load(WEB_SAFE_KEY)).thenReturn(post);
        impl.downVote(WEB_SAFE_KEY);
        verify(post, times(1)).decrementTotalVote();
        verify(post, times(1)).decrementdownVote();
        verify(repo, times(1)).save(post);
    }


}