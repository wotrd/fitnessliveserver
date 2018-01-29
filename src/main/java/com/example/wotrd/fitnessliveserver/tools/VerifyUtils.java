package com.example.wotrd.fitnessliveserver.tools;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wkj_pc on 2017/6/8.
 */
public class VerifyUtils {

    /**
     * 功能：手机号验证
     * @param phonenum
     * @return
     */
    public static boolean verifyPhonenum(String phonenum){
            Pattern p = null;
            Matcher m = null;
            boolean b = false;
            if (StringUtils.isBlank(phonenum)) {
                return b;
            }
            p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
            m = p.matcher(phonenum);
            b = m.matches();
            return b;
    }
    public static boolean verifyIdcard(String idcard){
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        if (StringUtils.isBlank(idcard)) {
            return b;
        }
        p = Pattern.compile("/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/"); // 验证手机号
        m = p.matcher(idcard);
        b = m.matches();
        return b;
    }
    public static boolean verifyEmail(String email){
        if (StringUtils.isBlank(email)) {
            return false;
        }
//		final String regEx="/[^@]+@[^@]/";
            final String regEx="^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            final Pattern pat = Pattern.compile(regEx);
            boolean flag = false;
            Matcher matcher = pat.matcher(email);
            flag = matcher.matches();
            return flag;
    }

}
