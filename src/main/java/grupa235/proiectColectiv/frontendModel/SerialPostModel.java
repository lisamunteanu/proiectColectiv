package grupa235.proiectColectiv.frontendModel;

import java.io.Serializable;

public class SerialPostModel implements Serializable {

    private String name;
    private String image;
    private String genres;
    private String director;
    private String description;

    public SerialPostModel(String name, String image, String genres, String director, String description) {
        this.name = name;
        this.image = image;
        this.genres = genres;
        this.director = director;
        this.description = description;
    }

    public SerialPostModel() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SerialPostModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
