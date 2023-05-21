package com.example.tryyourhair.Models;

public class GenerationData {
    private String ImageBase64;
    private String ImageName;
    private String HairstyleName;

    public GenerationData(String ImageBase64, String ImageName, String HairstyleName) {
        this.ImageBase64 = ImageBase64;
        this.ImageName = ImageName;
        this.HairstyleName = HairstyleName;
    }

    public void setImageBase64(String imageBase64) {
        ImageBase64 = imageBase64;
    }

    public String getImageBase64() {
        return ImageBase64;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setHairstyleName(String hairstyleName) {
        HairstyleName = hairstyleName;
    }

    public String getHairstyleName() {
        return HairstyleName;
    }
}
