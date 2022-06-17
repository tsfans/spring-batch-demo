/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import cn.swift.batchprocessing.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:18:30
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummyItemWriteListener implements ItemWriteListener<Person> {

    @Override
    public void beforeWrite(List<? extends Person> items) {
        log.info("execute ItemWriteListener.beforeWrite, write items: {}", items);
    }

    @Override
    public void afterWrite(List<? extends Person> items) {
        log.info("execute ItemWriteListener.afterWrite, write items: {}", items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Person> items) {
        log.info("execute ItemWriteListener.onWriteError, write items: {}, exception message: {}", 
                items, exception.getMessage());
    }

}
