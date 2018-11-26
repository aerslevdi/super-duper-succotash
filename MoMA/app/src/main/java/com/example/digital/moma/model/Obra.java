package com.example.digital.moma.model;

public class Obra {
    private String image;
    private String name;
    private String artistId;

    public Obra(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                '}';
    }
}
