import java.util.ArrayList;

public class User 
{
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    public User(String username)
    {
        this.username = username;
        this.posts = new ArrayList<>();
        this.upvoted = new ArrayList<>();
        this.downvoted = new ArrayList<>();
        this.karma = 0;
    }
    public void addPost(Post post)
    {
        if (post != null)
        {
            posts.add(post);
            updateKarma();
        }
    }
    public void updateKarma()
    {
        int count = 0;
        for (int i = 0; i < posts.size(); i++)
        {
            Post p = posts.get(i);
            count += p.getUpvoteCount() - p.getDownvoteCount();
        }
        this.karma = count;
    }
    public int getKarma()
    {
        return this.karma;
    }
    public void upvote(Post post)
    {
        if (post == null || upvoted.contains(post) || post.getAuthor() == this)
        {
            return;
        }
        if (downvoted.contains(post))
        {
            downvoted.remove(post);
            post.updateDownvoteCount(false);
        }
        post.updateUpvoteCount(true);
        upvoted.add(post);
        post.getAuthor().updateKarma();
    }
    public void downvote(Post post)
    {
        if (post == null || downvoted.contains(post) || post.getAuthor() == this)
        {
            return;
        }
        if (upvoted.contains(post))
        {
            upvoted.remove(post);
            post.updateUpvoteCount(false);
        }
        post.updateDownvoteCount(true);
        downvoted.add(post);
        post.getAuthor().updateKarma();
    }
    public Post getTopPost()
    {
        Post top = null;
        for (Post p : posts) 
        {
            if (p.getTitle() != null) 
            {
                int score = p.getUpvoteCount() - p.getDownvoteCount();
                if (top == null) 
                {
                    top = p;
                } 
                else 
                {
                    int topScore = top.getUpvoteCount() - top.getDownvoteCount();
                    if (score > topScore) 
                    {
                        top = p;
                    }
                }
            }
        }
        return top;
    }
    public Post getTopComment()
    {
        Post top = null;
        for (Post p : posts) 
        {
            if (p.getTitle() == null) 
            {
                int score = p.getUpvoteCount() - p.getDownvoteCount();
                if (top == null) 
                {
                    top = p;
                } 
                else 
                {
                    int topScore = top.getUpvoteCount() - top.getDownvoteCount();
                    if (score > topScore) 
                    {
                        top = p;
                    }
                }
            }
        }
        return top;
    }
    public ArrayList<Post> getPosts()
    {
        return this.posts;
    }
    public String toString()
    {
        return String.format("u/%s Karma: %d", username, karma);
    }
}
