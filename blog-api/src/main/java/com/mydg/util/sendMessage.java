package com.mydg.util;

/**
 * @author Yirany
 * @version 1.0
 * @since 8/31/2019
 **/

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.Date;


public class sendMessage {
    private static String APPKEY = "4d0a20e106e1c6719b78ff6b96b0706a";//开发者平台分配的appkey
    private static String APPSECRET = "5834245e5745";//安全码不需要提交，这样数据被截获也不能被修改，否则将不能被校验
    private static String NONCE = "hdfjlkjsdf67sdf9sdyf8";//随机数（最大长度128个字符）
    private static String TEMPLATEID = "14807528";//短信模板ID

    public static void main(String[] args) {
        System.out.println(sendMessage("18810012981", "hello"));
    }

    public static String sendMessage(String doctorPhoneNumber, String messageContent) {
        String curTime = String.valueOf((new Date()).getTime() / 1000L);//当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
        String checkSum = CheckSumBuilder.getCheckSum(APPSECRET, NONCE, curTime);//SHA1(AppSecret + Nonce + CurTime),三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)

        String url = "https://api.netease.im/sms/sendcode.action/" + "?templateid=" + TEMPLATEID + "&mobile="
                + doctorPhoneNumber + "&params=" + "[\"" + messageContent + "\"]";

        //spring RestTemplate发送请求
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");

        headers.set("AppKey", APPKEY);
        headers.set("Nonce", NONCE);
        headers.set("CurTime", curTime);
        headers.set("CheckSum", checkSum);
        headers.setContentType(type);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.postForObject(url, entity, String.class);
    }
}

class CheckSumBuilder {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
