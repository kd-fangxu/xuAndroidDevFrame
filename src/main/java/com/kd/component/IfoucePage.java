package com.kd.component;

/**
 * 广告栏接口
 * 
 * @author xu
 *
 * @param <T>
 */
public interface IfoucePage<T> {

	public String getCurrentImageUrl(T t);

	public void onItemClick(T t);

}
