/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import org.springframework.batch.core.ItemProcessListener;

import cn.swift.batchprocessing.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:17:22
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummyItemProcessListener implements ItemProcessListener<Person, Person> {

    @Override
    public void beforeProcess(Person item) {
        log.info("execute ItemProcessListener.beforeProcess, process item: {}", item);
    }

    @Override
    public void afterProcess(Person item, Person result) {
        log.info("execute ItemProcessListener.afterProcess, process item: {}, result: {}", 
                item, result);
    }

    @Override
    public void onProcessError(Person item, Exception e) {
        log.info("execute ItemProcessListener.onProcessError, process item: {}, exception message: {}", 
                item, e.getMessage());
    }

}
