package com.topshow.utils;

import com.alibaba.fastjson.JSONObject;
import com.sun.mail.util.MailSSLSocketFactory;
import com.topshow.constant.TopShowConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendSms {
    private static final String QUERY_PATH = "https://openapi.miaodiyun.com/distributor/sendSMS";
    private static final String ACCOUNT_SID = "5d03f1f5291045c19927459f2a4dd665";
    private static final String AUTH_TOKEN = "673bdea1b8f94dc5b0116fb59fce545a";

    public static String getSMSCode() {
        Random rand = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + rand.nextInt(10);
        }
        return code;
    }

    public static UtilsResult sendMessageCode(String code, String phone) {
        String timestamp = getTimestamp();
        String sig = getMD5(ACCOUNT_SID, AUTH_TOKEN, timestamp);
        String tamp = "【婉碧网络】您的验证码为" + code + "，请于" + '5' + "分钟内正确输入，如非本人操作，请忽略此短信。";
        OutputStreamWriter out = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(QUERY_PATH);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(TopShowConstant.REQUEST_METHOD_POST);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            String args = getQueryArgs(ACCOUNT_SID, tamp, phone, timestamp, sig, "JSON");
            out.write(args);
            out.flush();

            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        String respCode = jsonObject.getString("respCode");
        String respDesc = jsonObject.getString("respDesc");
        return new UtilsResult(Integer.valueOf(0), respDesc, Integer.valueOf(0), respCode);
    }

    public static String getQueryArgs(String accountSid, String smsContent, String to, String timestamp, String sig,
            String respDataType) {
        return "accountSid=" + accountSid + "&smsContent=" + smsContent + "&to=" + to + "&timestamp=" + timestamp
                + "&sig=" + sig + "&respDataType=" + respDataType;
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(getTimestamp());
    }
    public static String getMD5(String sid, String token, String timestamp) {
        StringBuilder result = new StringBuilder();
        String source = sid + token + timestamp;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] bytes = digest.digest(source.getBytes());
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    result.append("0" + hex);
                } else {
                    result.append(hex);
                }
            }
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return result.toString();
    }

    public static boolean sendMail(String email, String emailMsg) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "SMTP");
            props.setProperty("mail.host", "smtp.sina.com");

            props.setProperty("mail.smtp.auth", "true");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("yang_bin_0619@sina.com", "THEA2018###");
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.setFrom(new InternetAddress("yang_bin_0619@sina.com"));

            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject("玄煞网络科技");

            mimeMessage.setContent(emailMsg, "text/html;charset=utf-8");

            Transport.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
