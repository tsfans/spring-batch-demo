/**
 * Copyright (C) 2020 ~ 2021 Meituan. All Rights Reserved.
 */
package cn.swift.batchprocessing.listener.step;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author:   		HuLin
 * @date:    		2021年4月22日 下午2:14:24
 * @description:    DESCRIPTION
 * @version:   		v1.0
 */
@Slf4j
public class DummyChunkListener implements ChunkListener{

    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("execute ChunkListener.beforeChunk");
    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.info("execute ChunkListener.afterChunk");
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.info("execute ChunkListener.afterChunkError");
    }

}
