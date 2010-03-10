/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * <a href="OrderFactoryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OrderFactoryUtil {

	public static Order asc(String propertyName) {
		return getOrderFactory().asc(propertyName);
	}

	public static Order desc(String propertyName) {
		return getOrderFactory().desc(propertyName);
	}


	public static void addOrderByComparator(
			DynamicQuery query, OrderByComparator obc) {

		if (obc == null) {
			return;
		}
		
		String[] orderBys = obc.getOrderByFields();
		boolean isAscending = obc.isAscending();
		for (String orderBy : orderBys) {
			if (isAscending) {
				query.addOrder(asc(orderBy));
			}
			else {
				query.addOrder(desc(orderBy));
			}
		}
	}


	public static OrderFactory getOrderFactory() {
		return _orderFactory;
	}

	public void setOrderFactory(OrderFactory orderFactory) {
		_orderFactory = orderFactory;
	}

	private static OrderFactory _orderFactory;

}