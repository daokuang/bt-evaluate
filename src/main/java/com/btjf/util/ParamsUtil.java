package com.btjf.util;

import com.ancun.aosp.utils.StringUtils;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by yj on 2018/11/20.
 */
public class ParamsUtil {

    /**
     * 签名
     * @param paraMap
     * @param key
     * @return
     */
    public static String sign(Map<String, String> paraMap, String key){
        return AESUtils.encrypt(formatUrlMap(paraMap,false, false), key);
    }

    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap
     *            要排序的Map对象
     * @param urlEncode
     *            是否需要URLENCODE
     * @param keyToLower
     *            是否需要将Key转换为全小写 true:key 转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(paraMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "奥迪");
        String sign = sign(map, "2e6ccaabe2f429bf");
        System.out.println(sign);
        sign = AESUtils.decrypt(sign, "2e6ccaabe2f429bf");
        System.out.println(sign);
    }
}
