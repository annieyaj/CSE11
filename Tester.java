public class Tester {
    public static void main(String[] args) {
        User u1 = new User("CSE11Students");
        User u2 = new User("A");
        User u3 = new User("B");

        Post p1 = new Post("First Post", "This is our class's first post!", u1);
        u1.addPost(p1);

        Post c1 = new Post("Nice, I am in the class", p1, u2);
        u2.addPost(c1);

        Post c2 = new Post("I am also in the class!", p1, u3);
        u3.addPost(c2);

        u2.upvote(p1);
        u3.downvote(c1);
        u1.upvote(c2);

        //should not +1 or -1 again
        u2.upvote(p1);
        u3.downvote(c1);

        //+1/-1 on own post
        u1.upvote(p1);
        u1.downvote(p1);

        // Test karma
        System.out.println("User1 Karma: " + u1.getKarma());
        System.out.println("User2 Karma: " + u2.getKarma());
        System.out.println("User3 Karma: " + u3.getKarma());

        System.out.println("Thread from Comment 2:");
        for (Post p : c2.getThread()) 
        {
            System.out.println(p);
        }

        System.out.println("Top Post by CSE11Students \n" + u1.getTopPost());
        System.out.println("Top Comment by CSE11Students \n" + u1.getTopComment());
        System.out.println("Top Post by A \n" + u2.getTopPost());
        System.out.println("Top Comment by A \n" + u2.getTopComment());
        System.out.println("Top Post by B \n" + u3.getTopPost());
        System.out.println("Top Comment by B \n" + u3.getTopComment());

        System.out.println("Main Post: \n" + p1);
        System.out.println("Comment 1: \n" + c1);
        System.out.println("Comment 2: \n" + c2);
        System.out.println("User1: " + u1);
        System.out.println("User2: " + u2);
        System.out.println("User3: " + u3);

        System.out.println("User1 Posts:");
        for (Post p : u1.getPosts()) {
            System.out.println(p);
        }

        u1.addPost(null);
        u2.downvote(null);
        u3.upvote(null);

        p1.updateUpvoteCount(true);
        p1.updateDownvoteCount(true);
        System.out.println("Updated Main Post: \n" + p1);

        p1.updateUpvoteCount(false);
        p1.updateDownvoteCount(false);
        System.out.println("Latest Main Post: \n" + p1);

        u1.updateKarma();
        u2.updateKarma();
        u3.updateKarma();
        System.out.println("Karmas for u1 u2 u3: " + u1.getKarma() + " " + u2.getKarma() + " " + u3.getKarma());
    }
}