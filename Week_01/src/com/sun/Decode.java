package com.sun;


import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Base64;

/**
 * 对源文件进行转换
 */
public class Decode {
    public byte[] decodeByBase64(String input) {
        return Base64.getDecoder().decode(input);
    }

    public byte[] decodeBy255(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (255 - input[i]);
        }
        return input;
    }

    public byte[] decode(byte[] cLassBytes, String type) {
        if (type == null || type.isEmpty()) {
            return cLassBytes;
        } else if ("255".equals(type)) {
            return decodeBy255(cLassBytes);
        }
        return cLassBytes;
    }
}
