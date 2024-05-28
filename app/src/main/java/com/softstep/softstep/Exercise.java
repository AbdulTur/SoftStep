package com.softstep.softstep;

public class Exercise {
    private int id;
    private String name;
    private String description;
    private String instructions;
    private String videoPath;

    private String tags;

    private String ImagePath;

    public Exercise(int id, String name, String description, String instructions, String videoPath, String tags, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.videoPath = videoPath;
        this.tags = tags;
        this.ImagePath = imagePath;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getInstructions() {
        return instructions;
    }


    public String getVideoPath() {
        return videoPath;
    }

    public String getTags(){return tags;}

    public String getImagePath(){return ImagePath;}
}
