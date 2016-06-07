package baman.lankahomes.lk.magaelanga.models;

/**
 * Created by baman on 6/5/16.
 */

public class GridItem {
    private String id;
    private String image;
    private String title;
    private String distance;

    public GridItem() {
        super();
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){ return id; }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
