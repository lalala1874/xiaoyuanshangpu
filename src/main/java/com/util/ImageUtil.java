package com.util;

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
    public static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 处理缩略图加水印
     * @param
     * @param targetAddr
     * @return 相对路径
     */
    public static String generateThumbnail(InputStream thumbnailInputStream ,String fileName, String targetAddr) {
        String realFileName = getRandomFileName();//正确
        String extension = getFileExtension(fileName);//正确
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current targetAddr:"+targetAddr);

        logger.debug("current relativeAddr is :"+relativeAddr);


        makeDirPath(targetAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :"+PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200)
                    .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File("D:\\Java项目空间\\xiaoyuanshangpu\\target\\classes\\test02.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return relativeAddr;
    }

public static  File transferCommonsMultipartFiletoFile(CommonsMultipartFile file){

        File file1=new File(file.getOriginalFilename());
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
        String realFileParentPath = PathUtil.getImgBasePath() +targetAddr;
        File dirPath = new File(realFileParentPath);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

    }

//
//    public static void main(String[] args) throws IOException {
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        System.out.println(path);
//        String basepath = URLDecoder.decode(path, "utf-8");
//        System.out.println(basepath);
//        Thumbnails.of(new File(PathUtil.getImgBasePath() + "test01.jpg")).size(350, 350)
//                .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(basepath + "/test02.jpg")), 0.9f)
//                .toFile(new File(PathUtil.getImgBasePath() + "test12.jpg"));
//
//    }


}
