package com.example.wotrd.fitnessliveserver.tools;

import java.io.IOException;
import java.util.List;

public class VideoThumbnailsTools {
    /**
     * 使用ffmpeg截取视频的缩略图
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
     * java调用cmd使用ffmpeg命令处理ffmpeg
     * @param videoPath  源视频文件路径
     * @param imagePath  截取帧的图片存放路径
     * @throws Exception
     */
    public static boolean fetchFrame(String videoPath, String imagePath) {
            //ffmpeg -i xxx.mp4 -y -f image2 -t 0.001 -s 125x125 xxx.jpg
            List cmd = new java.util.ArrayList();
            cmd.add("ffmpeg");// 视频提取工具的位置
            cmd.add("-i");
            cmd.add(videoPath);
            cmd.add("-y");
            cmd.add("-f");
            cmd.add("image2");
            cmd.add("-t");
            cmd.add("0.001");
            cmd.add("-s");
            cmd.add("500x500");
            cmd.add(imagePath);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(cmd);
            try {
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }
}
