package com.example.tryyourhair.Singleton;

public class Singleton {
    private static Singleton uniqueInstance;
    private Boolean isChoseHair;
    private Boolean isConfirmedFace;
    private String ChoseHairURL;
    private String ChoseHairstyleName;
    private String ConfirmedFaceName;
    private byte[] ConfirmedFaceImage;


    private Singleton() {
        isChoseHair = false;
        isConfirmedFace = false;
    }
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    public void setChoseHair(Boolean choseHair) {
        isChoseHair = choseHair;
    }

    public Boolean getChoseHair() {
        return isChoseHair;
    }

    public void setConfirmedFace(Boolean confirmedFace) {
        isConfirmedFace = confirmedFace;
    }

    public Boolean getConfirmedFace() {
        return isConfirmedFace;
    }


    public void setChoseHairURL(String choseHairURL) {
        ChoseHairURL = choseHairURL;
    }

    public String getChoseHairURL() {
        return ChoseHairURL;
    }

    public void setChoseHairstyleName(String choseHairstyleName) {
        ChoseHairstyleName = choseHairstyleName;
    }

    public String getChoseHairstyleName() {
        return ChoseHairstyleName;
    }

    public void setConfirmedFaceImage(byte[] confirmedFaceImage) {
        ConfirmedFaceImage = confirmedFaceImage;
    }

    public byte[] getConfirmedFaceImage() {
        return ConfirmedFaceImage;
    }

    public String getConfirmedFaceName() {
        return ConfirmedFaceName;
    }

    public void setConfirmedFaceName(String confirmedFaceName) {
        ConfirmedFaceName = confirmedFaceName;
    }
}
