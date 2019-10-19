package com.example.libraryserver.tools;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author wkj_pc
 * @date 2017/6/5
 */
public class KaptchaUtils {
    public static void createKaptcha(HttpServletRequest request,HttpServletResponse response
            ,DefaultKaptcha defaultKaptcha) throws IOException{
        byte [] captchaAsJpeg =null;
        ByteArrayOutputStream jpegOutputstream =new ByteArrayOutputStream();
        //生产验证码并保存在session中
        String createText = defaultKaptcha.createText();
        request.getSession().setAttribute("verifyCode",createText);
        //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage bufferedImage=defaultKaptcha.createImage(createText);
        try {
            ImageIO.write(bufferedImage,"jpg",jpegOutputstream);
        } catch (IOException e1) {
                response.sendError(HttpServletResponse.SC_NO_CONTENT);
                return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaAsJpeg=jpegOutputstream.toByteArray();
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream=response.getOutputStream();
        outputStream.write(captchaAsJpeg);
        outputStream.flush();
        outputStream.close();
    }
}
