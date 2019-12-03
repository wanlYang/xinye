package com.topshow.utils;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/4/10.
 */
@SuppressWarnings("restriction")
public class CustomStringUtil {

    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

    /**
     * 图片转字符串
     * 
     * @return
     */
    public static String getImageBinary(String imagePath, String imageType) {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        File f = new File(imagePath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, imageType, baos);
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转图片
     * 
     * @param base64String
     */
    public static boolean base64StringToImage(StringBuffer base64String, String toImagePath, String imageType) {
        try {
            BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64String.toString().getBytes());
            byte[] bytes1 = decoder.decodeBuffer(byteArrayInputStream);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            RenderedImage bi1 = ImageIO.read(bais);
            File w2 = new File(toImagePath);// 可以是jpg,png,gif格式
            if (!w2.exists()) {
                w2.createNewFile();
                System.out.println("no exist=====");
            }
            System.out.println("pass...........");
            return ImageIO.write(bi1, imageType, w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取时间随机数
     * 
     * @return
     * @throws Exception
     */
    public static String getExtension() throws Exception {
        try {
            // 线程睡会
            Thread.sleep(1);
            // 生成日期实体
            Calendar calendar = Calendar.getInstance();
            String extension = calendar.getTime().getTime() + "";
            return extension;// 专网及EZVP公网
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数组转换为字符串
     * 
     * @param array     数组对象
     * @param separator 分割字符
     * @return
     * @throws Exception
     */
    public static String getArrayToString(Object[] array, String separator) throws Exception {
        String resultStr = StringUtils.join(array, separator);
        return resultStr;
    }

}
