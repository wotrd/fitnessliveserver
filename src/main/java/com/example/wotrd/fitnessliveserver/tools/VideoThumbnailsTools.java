package com.example.wotrd.fitnessliveserver.tools;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class VideoThumbnailsTools {
    /**
     * 使用javacv截取视频的缩略图
     */
  /*  public static void main(String []args){
        try {
            fetchFrame("F:/a.mp4", "F:/test4.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径
     * @throws Exception
     */
    public static boolean fetchFrame(String videofile, String framefile)
            throws Exception {
        FFmpegFrameGrabber ff=null;
        try{
            long start = System.currentTimeMillis();
            File targetFile = new File(framefile);
                ff = new FFmpegFrameGrabber(videofile);
            ff.start();
            int lenght = ff.getLengthInFrames();
            int i = 0;
            Frame f = null;
            while (i < lenght) {
                // 过滤前5帧，避免出现全黑的图片，依自己情况而定
                f = ff.grabFrame();
                if ((i > 5) && (f.image != null)) {
                    break;
                }
                i++;
            }
            opencv_core.IplImage img = f.image;
            int owidth = img.width();
            int oheight = img.height();
            // 对截取的帧进行等比例缩放
            int width = 800;
            int height = (int) (((double) width / owidth) * oheight);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                    0, 0, null);
            ImageIO.write(bi, "jpg", targetFile);
        //ff.flush();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if (null!=ff) ff.stop();
        }
        //System.out.println(System.currentTimeMillis() - start);
        return true;
    }
}
