/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.exception;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午5:44:08
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
public class SkipException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public SkipException(String message) {
        super(message);
    }

}
