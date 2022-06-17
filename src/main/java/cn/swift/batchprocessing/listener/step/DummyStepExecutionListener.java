/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:13:22
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummyStepExecutionListener implements StepExecutionListener{

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("execute StepExecutionListener.beforeStep [{}]", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("execute StepExecutionListener.afterStep [{}]", stepExecution.getStepName());
        return ExitStatus.COMPLETED;
    }

}
