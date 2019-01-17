package wrappers.post;

import entities.Post;

public class GetPostResponse {
    private String webSafeKey;
    private int upVotes;
    private int downVotes;
    private int totalVotes;
    private String content;
    private long creationDate;
    private long lastUpdated;

    private GetPostResponse(String webSafeKey, int upVotes, int downVotes,
                            int totalVotes, String content, long creationDate, long lastUpdated) {
        this.webSafeKey = webSafeKey;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.totalVotes = totalVotes;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdated = lastUpdated;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public String getContent() {
        return content;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public void setTotalVotes(int totalVotes) {
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

    public static GetPostResponse from(Post post){
        return new GetPostResponse(post.getWebSafeKey(), post.getUpVotes(), post.getDownVotes(),
                post.getTotalVotes(), post.getContent(), post.getCreationDate(), post.getLastUpdated());
    }
}
