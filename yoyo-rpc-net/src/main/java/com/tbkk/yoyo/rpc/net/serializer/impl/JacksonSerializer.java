package com.tbkk.yoyo.rpc.net.serializer.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbkk.yoyo.rpc.common.exception.YoyoRpcException;
import com.tbkk.yoyo.rpc.net.serializer.AbstractSerializer;


import java.io.IOException;

/**
 * Jackson serializer
 *
 * 		1、obj need private and set/get；
 * 		2、do not support inner class；
 *
 * @author tbkk 2019-9-25 18:02:56
 */
public class JacksonSerializer extends AbstractSerializer {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    
    /** bean、array、List、Map --> json 
     * @param <T>*/
    @Override
	public <T> byte[] serialize(T obj) {
		try {
			return objectMapper.writeValueAsBytes(obj);
		} catch (JsonProcessingException e) {
			throw new YoyoRpcException(e);
		}
	}

    /** string --> bean、Map、List(array) */
    @Override
	public <T> Object deserialize(byte[] bytes, Class<T> clazz)  {
		try {
			return objectMapper.readValue(bytes, clazz);
		} catch (JsonParseException e) {
			throw new YoyoRpcException(e);
		} catch (JsonMappingException e) {
			throw new YoyoRpcException(e);
		} catch (IOException e) {
			throw new YoyoRpcException(e);
		}
	}

}
