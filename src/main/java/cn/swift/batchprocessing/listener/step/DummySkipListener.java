/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import org.springframework.batch.core.SkipListener;

import cn.swift.batchprocessing.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:19:21
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummySkipListener implements SkipListener<Person, Person> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.info("execute SkipListener.onSkipInRead, message: {}", t.getMessage());
    }

    @Override
    public void onSkipInWrite(Person item, Throwable t) {
        log.info("execute SkipListener.onSkipInWrite, item: {}, message: {}", 
                item, t.getMessage());
    }

    @Override
    public void onSkipInProcess(Person item, Throwable t) {
        log.info("execute SkipListener.onSkipInProcess, item: {}, message: {}", 
                item, t.getMessage());
    }

}
