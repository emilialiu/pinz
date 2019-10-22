package com.dimine.mobile.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.BeanUtils;

import net.sf.cglib.beans.BeanMap;

import com.google.common.collect.Maps;

public class ListUtis {
	/**
	 * 实体转map
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key + "", beanMap.get(key));
			}
		}
		return map;
	}

	/**
	 * 保留两位小数
	 * 
	 * @param f
	 * @return
	 */
	public static double DoubleToFiexd(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 将 Map对象转化为JavaBean 此方法已经测试通过
	 * 
	 * @author wyply115
	 * @param type
	 *            要转化的类型
	 * @param map
	 * @return Object对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static <T> T convertMap2Bean(Map map, Class T) throws Exception {
		if (map == null || map.size() == 0) {
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(T);
		T bean = (T) T.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0, n = propertyDescriptors.length; i < n; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			String upperPropertyName = propertyName.toUpperCase();
			if (map.containsKey(upperPropertyName)) {
				Object value = map.get(upperPropertyName);
				// 这个方法不会报参数类型不匹配的错误。
				BeanUtils.copyProperty(bean, propertyName, value);
				// 用这个方法对int等类型会报参数类型不匹配错误，需要我们手动判断类型进行转换，比较麻烦。
				// descriptor.getWriteMethod().invoke(bean, value);
				// 用这个方法对时间等类型会报参数类型不匹配错误，也需要我们手动判断类型进行转换，比较麻烦。
				// BeanUtils.setProperty(bean, propertyName, value);
			}
		}
		return bean;
	}

	/**
	 * 将 JavaBean对象转化为 Map 此方法未测试
	 * 
	 * @author wyply115
	 * @param bean
	 *            要转化的类型
	 * @return Map对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static Map convertBean2Map(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0, n = propertyDescriptors.length; i < n; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	/**
	 * 将 List<Map>对象转化为List<JavaBean> 此方法已通过测试
	 * 
	 * @author wyply115
	 * @param type
	 *            要转化的类型
	 * @param map
	 * @return Object对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static <T> List<T> convertListMap2ListBean(
			List<Map<String, Object>> listMap, Class T) throws Exception {
		List<T> beanList = new ArrayList<T>();
		for (int i = 0, n = listMap.size(); i < n; i++) {
			Map<String, Object> map = listMap.get(i);
			T bean = convertMap2Bean(map, T);
			beanList.add(bean);
		}
		return beanList;
	}

	/**
	 * 将 List<JavaBean>对象转化为List<Map>
	 * 
	 * @author wyply115
	 * @param type
	 *            要转化的类型
	 * @param map
	 * @return Object对象
	 * @version 2016年3月20日 11:03:01
	 */
	public static List<Map<String, Object>> convertListBean2ListMap(
			List<Object> beanList) throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0, n = beanList.size(); i < n; i++) {
			Object bean = beanList.get(i);
			Map map = convertBean2Map(bean);
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 相除
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
