package com.kyanite.esign.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: qiuxiao
 * Date: 2018/1/11
 * Time: 下午6:00
 * Description:
 */
public class SignatureUtil {

    public static final String FIELD_SIGN = "signature";

    /**
     * 判断签名是否正确，否则返回false。使用MD5签名。
     *
     * @param object 校验对象
     * @param key    API秘钥
     * @param sign   签名
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Object object, String key, String sign) throws Exception {
        // 校验签名
        Map<String, String> map = SignatureUtil.convertObjectToMap(object);
        // 带上sign
        map.put(SignatureUtil.FIELD_SIGN, sign);

        return isSignatureValid(map, key);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
     *
     * @param data Map类型数据
     * @param key  API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data     Map类型数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(SignatureUtil.FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(SignatureUtil.FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key  API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SignatureUtil.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) {// 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * dto转成map
     *
     * @param obj
     * @return
     */
    public static Map<String, String> convertObjectToMap(Object obj) {
        Map<String, String> hashMap = new HashMap<String, String>();
        if (null == obj) {
            return null;
        }
        @SuppressWarnings("rawtypes")
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        if (null == fields) {
            return null;
        }
        for (Field field : fields) {
            PropertyDescriptor pd;
            try {
                String name = field.getName();
                pd = new PropertyDescriptor(field.getName(), cls);
                Method readMethod = pd.getReadMethod();
                String value = String.valueOf(readMethod.invoke(obj));
                if (!"null".equals(value) || "sign".equals(name)) {
                    hashMap.put(name, value);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return hashMap;
    }

    public enum SignType {
        MD5("MD5"),
        HMACSHA256("HMAC-SHA256");

        SignType(String encryptionType) {
            this.encryptionType = encryptionType;
        }

        private String encryptionType;

        public String getEncryptionType() {
            return encryptionType;
        }

        public void setEncryptionType(String encryptionType) {
            this.encryptionType = encryptionType;
        }
    }

}
