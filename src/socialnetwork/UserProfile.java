package socialnetwork;

public class UserProfile {
    private String username;
    private int age;
    private LinkedList wall;
    private LinkedList friends;
    private LinkedList follows;

    public UserProfile(String username, int age) {
        this.username = username;
        this.age = age;
        this.wall = new LinkedList();
        this.friends = new LinkedList();
        this.follows = new LinkedList();
    }

    public void postStatus(StatusUpdate status) {
        wall.addSorted(status);
    }

    public void addFriends(String friendUsername) {
        if (!friends.contains(friendUsername)) {
            friends.addLast(friendUsername);
        }

    }

    public void followCompany(String companyName){
        if (!follows.contains(companyName)){
            follows.addLast(companyName);
        }
    }

    public LinkedList getWall() {
        return wall;
    }

    public LinkedList getFriends() {
        return friends;
    }

    public LinkedList getFollows() {
        return follows;
    }

    @Override
    public String toString() {
        return "User Profile: " + username + ", " + age;
    }
}

