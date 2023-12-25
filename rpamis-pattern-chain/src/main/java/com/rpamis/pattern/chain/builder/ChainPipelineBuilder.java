package com.rpamis.pattern.chain.builder;

/**
 * 责任链Builder接口
 *
 * @author benym
 * @date 2023/8/21 16:55
 */
public interface ChainPipelineBuilder<T> {

    /**
     * 构建串行责任链
     *
     * @return SerialChainPipelineBuilder
     */
    SerialChainPipelineBuilder<T> chain();

    /**
     * 构建串行责任链, 并指定chainId
     *
     * @param chainId chainId
     * @return SerialChainPipelineBuilder
     */
    SerialChainPipelineBuilder<T> chain(String chainId);

    /**
     * 构建并行责任链
     *
     * @return ParallelChainPipelineBuilder
     */
    ParallelChainPipelineBuilder<T> parallelChain();

    /**
     * 构建并行责任链, 并指定chainId
     *
     * @param chainId chainId
     * @return ParallelChainPipelineBuilder
     */
    ParallelChainPipelineBuilder<T> parallelChain(String chainId);

    /**
     * 构建可变责任链
     *
     * @return VariableChainPipelineBuilder
     */
    VariableChainPipelineBuilder<T> variableChain();

    /**
     * 构建可变责任链, 并指定chainId
     *
     * @param chainId chainId
     * @return VariableChainPipelineBuilder
     */
    VariableChainPipelineBuilder<T> variableChain(String chainId);
}
