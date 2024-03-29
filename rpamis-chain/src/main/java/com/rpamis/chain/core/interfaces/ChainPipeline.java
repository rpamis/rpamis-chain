package com.rpamis.chain.core.interfaces;


import com.rpamis.chain.core.entity.ChainException;
import com.rpamis.chain.core.entity.CompleteChainResult;

/**
 * 泛型责任链流水线接口
 *
 * @param <T> <T>
 * @author benym
 * @date 2023/3/8 18:24
 */
public interface ChainPipeline<T> {

    /**
     * 添加Handler处理
     *
     * @param handler 具体的Handler处理类
     * @return ChainPipeline<T>责任链流水线
     */
    ChainPipeline<T> addHandler(ChainHandler<T> handler);

    /**
     * 流水线执行Handler处理
     *
     * @param handlerData 需要处理的数据
     */
    void doHandler(T handlerData);

    /**
     * 流水线执行Handler后的处理
     */
    void afterHandler();

    /**
     * 责任链执行策略
     *
     * @param strategy strategy
     * @return ChainPipeline
     */
    ChainPipeline<T> strategy(ChainStrategy<T> strategy);

    /**
     * 责任链流水线执行入口
     *
     * @param handlerData handlerData
     * @return boolean
     * @throws ChainException ChainException
     */
    CompleteChainResult start(T handlerData) throws ChainException;
}
