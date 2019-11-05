package com.dto;

import java.io.InputStream;

public class ImageHolder {
    private String ImageName;
    private InputStream image;

    public ImageHolder(String imageName, InputStream image) {
        ImageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
