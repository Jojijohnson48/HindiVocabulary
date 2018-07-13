package com.example.android.miwok;


public class Word {
    private String mDefaultTranslation;
    private String mHindiTranslation;
    private int mImgResID=NO_IMAGE;
    private int maudioResID;
    private static final int NO_IMAGE=-1;

    public Word(String defaultTranslation, String hindiTranslation,int audioResID){
        mDefaultTranslation=defaultTranslation;
        mHindiTranslation=hindiTranslation;
        maudioResID=audioResID;
        }

    public Word(String defaultTranslation, String hindiTranslation, int imgResID,int audioResID){
        mDefaultTranslation=defaultTranslation;
        mHindiTranslation=hindiTranslation;
        mImgResID=imgResID;
        maudioResID=audioResID;


    }
    public boolean there_is_image(){
        return mImgResID!=NO_IMAGE;
    }
    public String getdefaultTranslation(){
        return mDefaultTranslation;
    }
    public String gethindiTranslation(){
        return mHindiTranslation;
    }
    public int getImage(){
        return mImgResID;
    }
    public int getAudio(){
        return maudioResID;
    }


}
