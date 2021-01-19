package com.summer.MyUtils;

import org.apache.commons.lang.StringUtils;


public class CustomStringUtils {

    /**
     * @return java.lang.String
     * @Author hongzw
     * @Description 对字符串脱敏
     * @Date 下午8:42 2021/1/19
     * @Param [start, end, source]
     **/
    public static String formatToMask(int start, int end, String source) {
        if (source == null || source.length() == 0) {
            return "";
        }
        String regex = "(\\w{" + start + "})(\\w+)(\\w{" + end + "})";

        return source.replaceAll(regex, "$1**********$3");   //   *的数量是   src.length -start-end
    }

    /**
     * @return java.lang.String
     * @Author hongzw
     * @Description 姓名脱敏，只显示第一个字
     * @Date 下午8:47 2021/1/19
     * @Param [fullName]
     **/
    public static String desensitizedName(String fullName) {
        if (StringUtils.isNotEmpty(fullName)) {
            String name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return fullName;
    }


    /**
     * @return java.lang.String
     * @Author hongzw
     * @Description 脱敏手机号
     * @Date 下午9:17 2021/1/19
     * @Param [phoneNumber]
     **/
    public static String desensitizedPhoneNumber(String phoneNumber) {
        if (StringUtils.isNotEmpty(phoneNumber)) {
            phoneNumber = phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
        }
        return phoneNumber;
    }
}
