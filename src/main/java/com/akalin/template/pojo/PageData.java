package com.akalin.template.pojo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 封装数据库表字段和前台传参通用类
 * 因为springboot中mybatis返回map集合，数据库只能通过大写字母的字段
 * 获取数据，所以在本类中默认将所有key为大写字母的全部转为小写字母
 * 方便从数据库获取值，但其它地方用时请留意如果将key设置为大写字母
 * 取值的时候只能通过小写字母获取，大写字母无法获取，切记。。切记。。
 * Created by dusc on 2019/2/22 0022.
 */
public class PageData extends HashMap implements Map{

    private static final long serialVersionUID = 1L;

    Map map = null;
    HttpServletRequest request;
    public PageData(HttpServletRequest request){
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        while (entries.hasNext()) {
            String value = "";
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value += values[i] + ",";
                }
                if(values.length > 0 ) {
                    value = value.substring(0, value.length()-1);
                }
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        map = returnMap;
    }

    public PageData() {
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if(map.get(key) instanceof Object[]) {
            Object[] arr = (Object[])map.get(key);
            obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        return String.valueOf(get(key));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        if (key instanceof String){
            String newKey = ((String) key).toLowerCase();
            return map.put(newKey, value);
        }
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {

        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {

        return map.containsValue(value);
    }

    public Set entrySet() {

        return map.entrySet();
    }

    public boolean isEmpty() {

        return map.isEmpty();
    }

    public Set keySet() {

        return map.keySet();
    }

    @SuppressWarnings("unchecked")
    public void putAll(Map t) {

        map.putAll(t);
    }

    public int size() {

        return map.size();
    }

    public Collection values() {

        return map.values();
    }
}
