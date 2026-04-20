package socialnetwork;

public class Main {

    public static void main(String[] args) {


        SocialNetwork social = new SocialNetwork();

        // Creation of users
        social.createUserProfile("Joseph", 25);
        social.createUserProfile("Epiti", 30);
        social.createUserProfile("Anna", 25);
        social.createUserProfile("David", 30);
        // Creation of  corporate profile
        social.createCorporateProfile("CompB");
        social.createCorporateProfile("CompC");
        // Post status updates
        social.postStatus("Joseph", "Say cheesee!", 0, 0, 1);
        social.postStatus("Epiti", "Good morning!", 1, 0, 2);
        social.postStatus("Anna", "Good afternoon!", 2, 0, 3);
        social.postStatus("Anna", "heyyyy!", 0, 0, 4);
        social.postStatus("David", "yoooo!", 0, 0, 4);
        //connect with other users
        social.connect("Joseph","Anna");
        social.connect("Anna","David");
        social.connect("David","Epiti");
        //follow companies
        social.follow("Joseph","CompC");
        social.follow("David","CompB");
        // Post ads
        social.postAd("CompB", "We sell Computers!", 0, true, 3);
        // Print wall
        social.printWallOf("Epiti");
        social.printWallOf("Joseph");
        social.printWallOf("Anna");
        //print friend's list
        social.printFriendListOf("Joseph");
        //print wall of friend
        social.printWallOfAFriend("Joseph", "Anna");
        int d= social.distance("Joseph", "Epiti");
        social.printPath("Joseph","Epiti");
        social.distanceExcludeCorporate("Joseph", "Anna");
        social.printPathExcludeCorporate("Joseph", "Anna");
        System.out.println("Distance: " + d);



    }
}