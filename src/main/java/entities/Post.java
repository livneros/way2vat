package entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import utils.Utils;
import wrappers.post.AddPostRequest;

import javax.annotation.Nonnull;
import javax.rmi.CORBA.Util;

@Entity
public class Post implements EntityRoot{
    @Id
    private Long id;
    private int upVotes;
    private int downVotes;
    @Index
    private int totalVotes;
    private String content;
    @Index
    private long creationDate;
    private long lastUpdated;

    private Post(String content, long creationDate, long lastUpdated) {
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdated = lastUpdated;
    }

    public Post() {
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public Long getId() {
        return id;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    @Nonnull
    public String getContent() {
        return Utils.convertNullToEmptyString(content);
    }

    public long getCreationDate() {
        return creationDate;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    private void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    private void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    private void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void incrementUpVote(){
        this.setUpVotes(getUpVotes() + 1);
    }
    public void decrementdownVote(){
        this.setDownVotes(getDownVotes() - 1);
    }
    public void incrementTotalVotes(){
        this.setTotalVotes(getTotalVotes() + 1);
    }
    public void decrementTotalVote(){
        this.setTotalVotes(getTotalVotes() - 1);
    }

    @Override
    public Key<? extends EntityRoot> getKey() {
        return Key.create(Post.class, id);
    }

    public String getWebSafeKey() {
        return getKey().toWebSafeString();
    }

    @Override
    public Ref<? extends EntityRoot> getRef() {
        return Ref.create(getKey());
    }

    public static Post from(AddPostRequest addPostRequest) {
        long creationDate = Utils.getCurrentMilliseconds();
        String content = addPostRequest.getContent();
        return new Post(content, creationDate, creationDate);
    }

}
