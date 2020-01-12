package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class Image implements Serializable {

    private String image;

    public Image(String image) {
        this.image = image;
    }

    public Image(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image='" + image + '\'' +
                '}';
    }
}
