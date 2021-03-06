/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.spring.aop;

/**
 * @author Shuyang Zhou
 */
public class ChainableMethodAdviceInjector {

	public void afterPropertiesSet() {
		if (!isInjectCondition()) {
			return;
		}

		ChainableMethodAdvice newChainableMethodAdvice =
			getNewChainableMethodAdvice();

		if (newChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"New Chainable method advice is null");
		}

		ChainableMethodAdvice parentChainableMethodAdvice =
			getParentChainableMethodAdvice();

		if (parentChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"Parent chainable method advice is null");
		}

		newChainableMethodAdvice.nextMethodInterceptor =
			parentChainableMethodAdvice.nextMethodInterceptor;
		parentChainableMethodAdvice.nextMethodInterceptor =
			newChainableMethodAdvice;
	}

	public void setInjectCondition(boolean injectCondition) {
		_injectCondition = injectCondition;
	}

	public void setNewChainableMethodAdvice(
		ChainableMethodAdvice newChainableMethodAdvice) {

		_newChainableMethodAdvice = newChainableMethodAdvice;
	}

	public void setParentChainableMethodAdvice(
		ChainableMethodAdvice parentChainableMethodAdvice) {

		_parentChainableMethodAdvice = parentChainableMethodAdvice;
	}

	protected ChainableMethodAdvice getNewChainableMethodAdvice() {
		return _newChainableMethodAdvice;
	}

	protected ChainableMethodAdvice getParentChainableMethodAdvice() {
		return _parentChainableMethodAdvice;
	}

	protected boolean isInjectCondition() {
		return _injectCondition;
	}

	private boolean _injectCondition;
	private ChainableMethodAdvice _newChainableMethodAdvice;
	private ChainableMethodAdvice _parentChainableMethodAdvice;

}