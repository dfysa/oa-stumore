package top.fyl.oastumore.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author dfysa
 * @data 25/11/2023 下午8:19
 * @description
 */
public class Md5Util {

    /**
     * 对源数据生成 MD5 摘要
     *
     * @param source 源数据
     * @return string
     */
    public static String md5Digest(String source) {
        return DigestUtil.md5Hex(source);
    }

    /**
     * 对源数据进行加盐加密
     * @param source 源数据
     * @param salt 颜值
     * @return string
     */

    public static String md5Digest(String source, Integer salt) {
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        String target = new String(chars);
        return md5Digest(target);
    }

    public static void main(String[] args) {
        System.out.println(md5Digest("123456", 188));

    }
}