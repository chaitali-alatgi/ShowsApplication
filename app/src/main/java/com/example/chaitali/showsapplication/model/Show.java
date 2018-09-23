package com.example.chaitali.showsapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show {

    @SerializedName ("summary")
    private String summary;

    @SerializedName ("image")
    private Image image;

    @SerializedName ("_links")
    private Links links;

    @SerializedName ("premiered")
    private String premiered;

    @SerializedName ("rating")
    private Rating rating;

    @SerializedName ("runtime")
    private int runtime;

    @SerializedName ("weight")
    private int weight;

    @SerializedName ("language")
    private String language;

    @SerializedName ("type")
    private String type;

    @SerializedName ("url")
    private String url;

    @SerializedName ("officialSite")
    private String officialSite;

    @SerializedName ("network")
    private Network network;

    @SerializedName ("schedule")
    private Schedule schedule;

    @SerializedName ("webChannel")
    private Object webChannel;

    @SerializedName ("genres")
    private List<String> genres;

    @SerializedName ("name")
    private String name;

    @SerializedName ("id")
    private int id;

    @SerializedName ("externals")
    private Externals externals;

    @SerializedName ("updated")
    private int updated;

    @SerializedName ("status")
    private String status;

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setWebChannel(Object webChannel) {
        this.webChannel = webChannel;
    }

    public Object getWebChannel() {
        return webChannel;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setExternals(Externals externals) {
        this.externals = externals;
    }

    public Externals getExternals() {
        return externals;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public int getUpdated() {
        return updated;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}