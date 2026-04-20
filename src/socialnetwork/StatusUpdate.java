package socialnetwork;

public class StatusUpdate implements Comparable {
    private String author;
    private String content;
    private int ageLimit;
    private int privacy;       // 0 = public, 1 = friends, 2 = private
    private int timestamp;
    private boolean isAd;
    private boolean paid;     // only relevant if isAd is true


    // for normal status
    public StatusUpdate(String content, String author, int privacy, int ageLimit, int timestamp) {
        this.author = author;
        this.content = content;
        this.ageLimit = ageLimit;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.isAd = false;
        this.paid = false;
    }

    // Constructor for ads
    public StatusUpdate(String author, String content , int ageLimit, boolean paid, int timestamp) {
        this.author = author;
        this.content = content;
        this.ageLimit = ageLimit;
        this.paid = paid;
        this.timestamp = timestamp;
        this.isAd = true;
        this.privacy = 0;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public boolean getIsAd() {
        return isAd;
    }

    public int getPrivacy(){
        return privacy;
    }

    @Override
    public int compareTo(Object o) {
        StatusUpdate other = (StatusUpdate) o;
        return this.timestamp - other.timestamp;  // ascending chronological order
    }

    @Override
    public String toString() {
        if (isAd) {
            return "Author: " + author + ", Ad: " + content + ", AgeLimit: " + ageLimit + ", Paid: " + paid + ", Timestamp: " + timestamp;
        } else {
            return "StatusUpdate: " + timestamp + ", Author: " + author + ", Privacy: " + privacy + ", AgeLimit: " + ageLimit + ", Content: " + content;
        }
    }
}

