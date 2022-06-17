/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import org.springframework.batch.core.ItemReadListener;

import cn.swift.batchprocessing.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:16:16
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummyItemReadListener implements ItemReadListener<Person> {

    @Override
    public void beforeRead() {
        log.info("execute ItemReadListener.beforeRead");
    }

    @Override
    public void afterRead(Person item) {
        log.info("execute ItemReadListener.afterRead, read item: {}", item);
    }

    @Override
    public void onReadError(Exception ex) {
        log.info("execute ItemReadListener.beforeRead");
    }

}
