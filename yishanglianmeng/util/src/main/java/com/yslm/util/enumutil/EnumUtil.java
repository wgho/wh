package com.yslm.util.enumutil;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yslm.util.validate.ValidateUtil;


/**
 * enum 工具类
 */
public class EnumUtil {

    private EnumUtil() {
    }

    public static <T extends Enum<T>> int encode(EnumSet<T> set) {
        if (set == null) {
            return 0;
        }

        int ret = 0;

        for (T val : set) {
            ret |= 1 << val.ordinal();
        }

        return ret;
    }

    public static <T extends Enum<T>> EnumSet<T> decode(Class<T> type, int code) {
        Map<Integer, T> codeMap = new HashMap<Integer, T>();

        for (T val : EnumSet.allOf(type)) {
            codeMap.put(val.ordinal(), val);
        }

        EnumSet<T> result = EnumSet.noneOf(type);
        while (code != 0) {
            int ordinal = Integer.numberOfTrailingZeros(code);
            code ^= Integer.lowestOneBit(code);
            result.add(codeMap.get(ordinal));
        }

        return result;
    }
    
    /**
     * 根据value获取enum对象
     * 
     * @return
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getEnumByValue(String value, Class<T> c) {
        if (!ValidateUtil.isEmpty(value)) {
            for (Object o : c.getEnumConstants()) { 
                if (o.toString().equals(value)) {
                    return (T) o;
                }
            }
        }
        return null;
    }
    
    /**
     * 根据name获取enum对象
     * 
     * @return
     */
    public static <T extends Enum<T>> T getEnumByName(String Value,Class<T> type) {

        for (T val : EnumSet.allOf(type)) {
        	
        	if(val.name().equals(Value)){
        		return val;
        	}
        }

    	return  null;
    }
    
    
    /**
     * 
     * @param set
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List<String> getEnumNamesByEnumSet(EnumSet<?> set) {
    	List<String> list = new ArrayList<String>();
    	
    	if(null == set || set.size() == 0){
    		return list;
    	}
        for(Enum enum1 : set){
        	list.add(enum1.name());
        }

    	return  list;
    }
    
}
