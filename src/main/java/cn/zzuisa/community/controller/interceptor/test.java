package cn.zzuisa.community.controller.interceptor;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ao
 * @date 2020-01-24 00:49
 */
public class test {
    public static void main(String[] args) {
        Map<String, Object> a = new HashMap<>();
        a = null;
        System.out.printf(""+ObjectUtils.isEmpty(a));
        System.out.printf(""+a);
    }
}
