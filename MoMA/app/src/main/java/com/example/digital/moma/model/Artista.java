package com.example.digital.moma.model;

public class Artista {
    private String id;
    private String name;
    private String nationality;
    private String influenced_by;

    public Artista() {
    }

    public Artista(String id, String name, String nationality, String influenced_by) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.influenced_by = influenced_by;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInfluenced_by() {
        return influenced_by;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", influenced_by='" + influenced_by + '\'' +
                '}';
    }
}
