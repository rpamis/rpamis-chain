package com.rpamis.pattern.chain.builder;

import com.rpamis.pattern.chain.generic.ChainTypeReference;

/**
 * 责任链工厂
 *
 * @author benym
 * @date 2023/8/21 17:45
 */
public class ChainPipelineFactory {

    private ChainPipelineFactory() {
        throw new IllegalStateException("ChainPipelineFactory class prohibited instantiation");
    }

    /**
     * 创建责任链Builder实例
     *
     * @param chainTypeReference 责任链泛型包装类
     * @param <T>                责任链泛型
     * @return ChainPipelineBuilder 责任链Builder实例
     */
    public static <T> ChainPipelineBuilder<T> createChain(ChainTypeReference<T> chainTypeReference) {
        return new ChainPipelineBuilderImpl<>(chainTypeReference);
    }
}
