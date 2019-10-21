package com.xc.datasource;

/**
 * 当前数据源处理
 * <p/>
 * Created by xiongying on 16/8/2.
 */
public class HandleDataSource {
    public static final ThreadLocal<Boolean> holder = new ThreadLocal<Boolean>();

    public static void putDataSource(boolean readOnly) {
        holder.set(readOnly);
    }

    public static Boolean getDataSource() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
