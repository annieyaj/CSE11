import java.util.ArrayList;

public class Post 
{
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    //original post
    public Post(String title, String content, User author)
    {
        this.title = title;
        this.content = content;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
        this.replyTo = null;
    }
    //comments
    public Post(String content, Post replyTo, User author)
    {
        this.title = null;
        this.content = content;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
        this.replyTo = replyTo;
    }
    //return title of the post
    public String getTitle()
    {
        return this.title;
    }
    //return the Post of that this Post is replying to
    public Post getReplyTo()
    {
        return this.replyTo;
    }
    public User getAuthor()
    {
        return this.author;
    }
    public int getUpvoteCount()
    {
        return this.upvoteCount;
    }
    public int getDownvoteCount()
    {
        return this.downvoteCount;
    }
    public void updateUpvoteCount(boolean isIncrement)
    {
        if (isIncrement)
        {
            this.upvoteCount++;
        }
        else
        {
            this.upvoteCount--;
        }
    }
    public void updateDownvoteCount(boolean isIncrement)
    {
        if (isIncrement)
        {
            this.downvoteCount++;
        }
        else
        {
            this.downvoteCount--;
        }
    }
    public ArrayList<Post> getThread()
    {
        ArrayList<Post> thread = new ArrayList<>();
        Post latest = this;
        while (latest != null)
        {
            thread.add(0, latest);
            latest = latest.getReplyTo();
        }
        return thread;
    }
    public String toString()
    {
        if (this.title != null)
        {
            return String.format("[%d|%d]\t%s\n\t%s", upvoteCount, downvoteCount, title, content);
        }
        else
        {
            return String.format("[%d|%d]\t%s", upvoteCount, downvoteCount, content);
        }
    }
}
