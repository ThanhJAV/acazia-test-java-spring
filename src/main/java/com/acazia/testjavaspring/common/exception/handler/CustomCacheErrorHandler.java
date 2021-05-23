package com.acazia.testjavaspring.common.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class CustomCacheErrorHandler implements CacheErrorHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomCacheErrorHandler.class);

	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		LOGGER.warn(exception.getMessage(), cache, key);

	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		LOGGER.warn(exception.getMessage(), cache, key);

	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		LOGGER.warn(exception.getMessage(), cache, key);

	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		LOGGER.warn(exception.getMessage(), cache);

	}
}
