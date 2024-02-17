package com.rpamis.chain.core.fluent;

import com.rpamis.chain.core.entities.CompleteChainResult;

/**
 * Apply接口
 *
 * @author benym
 * @date 2023/8/21 16:54
 */
public interface Apply<T> {

    /**
     * 责任链流水线执行入口
     *
     * @param handlerData handlerData
     * @return boolean
     */
    CompleteChainResult apply(T handlerData);
}