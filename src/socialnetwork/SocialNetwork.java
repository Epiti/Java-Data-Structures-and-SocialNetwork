package socialnetwork;

public class SocialNetwork implements iSocialNetwork{

    private Dictionary profiles;

    public SocialNetwork(){
        profiles = new Dictionary();
    }

    // user profile creation
    public void createUserProfile(String username, int age) {
    if (profiles.find(username) != null) {
            System.out.println("\n User " + username + " already exists!");
            return;
        }
        UserProfile user = new UserProfile(username, age);
        profiles.add(username, user);
    }

    //Corporation profile creation
    public void createCorporateProfile(String companyName) {
        if (profiles.find(companyName) != null) {
            System.out.println("\n Corporate " + companyName + " already exists!");
            return;
        }
        CorporateProfile corporate = new CorporateProfile(companyName);
        profiles.add(companyName, corporate);
    }

    public void printWallOf(String username) {
        Object hold = profiles.find(username);
        if(hold == null || !(hold instanceof UserProfile)){
            System.out.println("\n Profile" + username + " does not exist!");
            return;
        }

        // I WROTE THIS PIECE OF CODE WITH GPT'S HELP.

        UserProfile user = (UserProfile) hold;
        LinkedList statuses = user.getWall(); // get all user's status
        LinkedList ads = new LinkedList(); // collect all ads from corporate profile

        for (int i = 0; i < profiles.size(); i++) {
            Object value = profiles.getValueAt(i);

            if (value instanceof CorporateProfile) {
                CorporateProfile corp = (CorporateProfile) value;
                LinkedList corpAds = corp.getWall();

                // Insert each ad in sorted order
                for (int j = 0; j < corpAds.size(); j++) {
                    ads.addSorted((StatusUpdate) corpAds.get(j));
                }
            }
        }

        // Print 4 statuses then 1 ad
        System.out.println("\n Wall of " + username + ":");

        int m = 0;
        int n = 0;

        while (m < statuses.size() || n < ads.size()) {

            // Print up to 4 statuses
            for (int k = 0; k < 4 && m < statuses.size(); k++) {
                System.out.println(statuses.get(m));
                m++;
            }
            // Then print 1 ad (if any left)
            if (n < ads.size()) {
                System.out.println(ads.get(n));
                n++;
            }
        }


    }

    public void postStatus(String username, String content, int privacy, int ageLimit, int timestamp) {
        Object hold = profiles.find(username);
        if (hold == null || !(hold instanceof UserProfile)) {
            System.out.println("\n User" + username + "not found.");
            return;
        }
        UserProfile user = (UserProfile) hold;
        StatusUpdate status = new StatusUpdate(content, username, privacy, ageLimit, timestamp);
        user.postStatus(status);
    }

    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp) {
        Object hold = profiles.find(companyName);
        if (hold == null || !(hold instanceof CorporateProfile)) {
            System.out.println("\n Company not found: " + companyName);
            return;
        }

        CorporateProfile company = (CorporateProfile) hold;
        StatusUpdate ads = new StatusUpdate(companyName, ad, ageLimit, paid, timestamp);
        company.postAd(ads);
    }

    @Override
    public void connect(String username1, String username2) {
        UserProfile userOne =(UserProfile) profiles.find(username1);
        UserProfile userTwo =(UserProfile) profiles.find(username2);
        if(userOne == null || userTwo == null){
            System.out.println("\n Users dont exist!");
            return;
        }
        userOne.addFriends(username2);
        userTwo.addFriends(username1);

    }

    @Override
    public void printFriendListOf(String username) {
        Object hold = profiles.find(username);
        if (!(hold instanceof UserProfile)) {
            System.out.println("\n User " + username + " does not exist!");
            return;
        }
        UserProfile user = (UserProfile) hold;
        LinkedList friends = user.getFriends();

        System.out.println("\n Friends of " + username + ":");
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

    }

    @Override
    public void printWallOfAFriend(String username, String friendUsername) {
        Object hold = profiles.find(username);
        Object friendHold = profiles.find(friendUsername);
        if(!(hold instanceof UserProfile)){
            System.out.println("\n User " + username + " does not exist!");
            return;
        }
        if(!(friendHold instanceof UserProfile)){
            System.out.println("\n Friend " + friendUsername + " does not exist!");
            return;
        }
        UserProfile user = (UserProfile) hold;
        UserProfile friend = (UserProfile) friendHold;

        if(!friend.getFriends().contains(username)){
            System.out.println("\nYou are not friend with "+friendUsername);
            return;

        }

        LinkedList statuses = friend.getWall();
        System.out.println("\n Wall of your friend " + friendUsername + ":");
        for (int i = 0; i < statuses.size(); i++) {
            StatusUpdate status = (StatusUpdate) statuses.get(i);
            if(status.getPrivacy() == 0 || status.getPrivacy()==1 && friend.getFriends().contains(username)){
                System.out.print(status);

            }
        }

    }

    @Override
    public void follow(String username, String corporateName) {
        UserProfile user = (UserProfile) profiles.find(username);
        CorporateProfile company = (CorporateProfile) profiles.find(corporateName);
        if(user == null) {
            System.out.println("\n User " + username + " not found!");
            return;
        } if(company == null) {
            System.out.println("\n Company " + corporateName + " not found!");
            return;
        }
        user.followCompany(corporateName);

    }


    private LinkedList getNeighbors(String username, boolean includeCorporate) {
        LinkedList neighbors = new LinkedList();
        Object hold = profiles.find(username);

        if (!(hold instanceof UserProfile))
            return neighbors;


        UserProfile user = (UserProfile) hold;


        LinkedList friends = user.getFriends();
        for (int i = 0; i < friends.size(); i++) {
            neighbors.addLast(friends.get(i));
        }

        if (includeCorporate) {
            LinkedList follows = user.getFollows();
            for (int i = 0; i < follows.size(); i++) {
                neighbors.addLast(follows.get(i));
            }
        }

        return neighbors;
    }


    private int myDistance(String start, String end, boolean includeCorporate, Dictionary predecessors) {
        if (start.equals(end)) return 0;

        Queue queue = new Queue();
        Dictionary visited = new Dictionary();

        queue.push(start);
        visited.add(start," ");

        while (!queue.empty()) {
            String current = (String) queue.pop();
            LinkedList neighbors = getNeighbors(current, includeCorporate);

            for (int i = 0; i < neighbors.size(); i++) {
                String neighbor = (String) neighbors.get(i);

                if (visited.find(neighbor) == null) {
                    visited.add(neighbor, current);
                    if (predecessors != null) predecessors.add(neighbor, current);

                    if (neighbor.equals(end)) {

                        int distance = 0;
                        String hold = neighbor;
                        while (true) {
                            String parent = (String) visited.find(hold);
                            if (parent == null) break;
                            distance ++;
                            hold = parent;
                        }
                        return distance;
                    }
                    queue.push(neighbor);
                }
            }
        }

        return -1;          // unreachable
    }
    private LinkedList myPath(String start, String end, boolean includeCorporate) {
        LinkedList path = new LinkedList();
        Dictionary predecessors = new Dictionary();
        int distance = myDistance(start, end, includeCorporate, predecessors);
        if (distance == -1) return path;

        String step = end;
        while (step != null) {
            path.addFirst(step);
            step = (String) predecessors.find(step);
        }

        return path;
    }

    @Override
    public int distance(String username1, String username2) {
        int distance = myDistance(username1, username2, true, null);
        //System.out.println("\n Distance: " + distance);
        return distance;
    }

    @Override
    public void printPath(String username1, String username2) {
        LinkedList path = myPath(username1, username2, true);
        System.out.println("\n Path: " + path);

    }

    @Override
    public int distanceExcludeCorporate(String username1, String username2) {
        int dis = myDistance(username1, username2, false, null);
        //System.out.println("\n Distance without companies: " + dis);
        return dis;
    }

    @Override
    public void printPathExcludeCorporate(String username1, String username2) {
        LinkedList path = myPath(username1, username2, false);
        System.out.println("\n Path excluding corporate: " + path);

    }


}
