package com.util;

import com.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    public static String basepath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    public static final SimpleDateFormat dateFromat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final Random randow = new Random();
    public static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 处理缩略图加水印
     *
     * @param
     * @param targetAddr
     * @return 相对路径
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        String relativeAddr = targetAddr + realFileName + extension;
        makeDirPath(targetAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\Java项目空间\\xiaoyuanshangpu\\1.png")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return relativeAddr;
    }


    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        String relativeAddr = targetAddr + realFileName + extension;
        makeDirPath(targetAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(337,640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\Java项目空间\\xiaoyuanshangpu\\1.png")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return relativeAddr;
    }

    public static File transferCommonsMultipartFiletoFile(CommonsMultipartFile file) {

        File file1 = new File(file.getOriginalFilename());
        try {
            file.transferTo(file1);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return file1;

    }

    /**
     * getRandomFileName
     * 获取随机文件名
     *
     * @return
     */

    public static String getRandomFileName() {
        int runnum = randow.nextInt(89999) + 10000;
        String nowTimeStr = dateFromat.format(new Date());

        return nowTimeStr + runnum;
    }

    /**
     * 获取文件后缀
     *
     * @param
     * @return
     */
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));


    }

    /**
     * 创建文件的目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

    }

    public static void deleteFileOrPath(String storePath){
           File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
           if (fileOrPath.exists()){
               if (fileOrPath.isDirectory()){
                   File file[]=fileOrPath.listFiles();
                   for (File f:file
                        ) {
                       f.delete();
                   }
               }
               fileOrPath.delete();
           }
    }

}
