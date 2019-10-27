package com.util;

public class PathUtil {
    public static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String basePath;
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:\\图片空间\\";
        } else {
            basePath = "\\home\\xiangze\\image\\";
        }
        basePath = basePath.replace("\\", separator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);

    }
}
