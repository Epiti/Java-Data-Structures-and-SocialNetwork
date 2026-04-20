package socialnetwork;

public class CorporateProfile {
    private String companyName;
    private LinkedList wall;

    public CorporateProfile(String companyName) {
        this.companyName = companyName;
        this.wall = new LinkedList();
    }

    public void postAd(StatusUpdate ad) {
        wall.addSorted(ad);
    }

    public LinkedList getWall() {
        return wall;
    }

    @Override
    public String toString() {
        return "Corporate Profile: " + companyName;
    }
}

