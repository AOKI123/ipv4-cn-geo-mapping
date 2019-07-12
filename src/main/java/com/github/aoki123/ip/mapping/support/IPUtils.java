package com.github.aoki123.ip.mapping.support;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * IP地址相关工具方法
 */
public class IPUtils {

    // @formatter:off
    private static final String IPV4_BASIC_PATTERN_STRING =
            "(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}" + // initial first field, 1-255
                    "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}" + // following 2 fields, 0-255 followed by .
                    "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])"; // final field, 0-255

    private static final Pattern IPV4_PATTERN =
            Pattern.compile("^" + IPV4_BASIC_PATTERN_STRING + "$");
    // @formatter:on

    /**
     * IP地址计算出可用于排序/检索的数值 <br/>
     * <p>
     * IP地址: ip4.ip3.ip2.ip1 <br/>
     * 算法: ip4*256*256*256 + ip3*256*256 + ip2*256 + ip1
     *
     * @param ip a valid IPV4 address
     * @return score
     */
    public static long ipv4Score(String ip) {

        long score = 0;
        String[] ipsections = ip.split("\\.");

        // ip4*256*256*256
        score += Long.parseLong(ipsections[0], 10) << 24;

        // ip3*256*256
        score += Long.parseLong(ipsections[1], 10) << 16;

        // ip2*256
        score += Long.parseLong(ipsections[2], 10) << 8;

        // ip1
        score += Long.parseLong(ipsections[3], 10);

        return score;
    }

    /**
     * 判断是否为有效的IPV4地址信息
     *
     * @param ip
     * @return boolean
     */
    public static boolean isValidIPv4Address(final String ip) {

        return !StringUtils.isEmpty(ip) && IPV4_PATTERN.matcher(ip).matches();
    }

}
