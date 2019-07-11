package com.lzqs.zhangyushu.paramUtil;

public class ParamCheckUtils {
    /**
     * 如果有任意参数为空，返回true
     * @param args
     * @return
     */
    public static Boolean paramIsNull(Object... args) {
        for (Object arg : args) {
            if (arg == null)
                return true;
        }
        return false;
    }

    //测试代码
    public static void main(String[] args) {
        System.out.println(paramIsNull(22, 1234));
    }
}
