package com.rpamis.pattern.chain.builder;

import com.rpamis.pattern.chain.fluent.Add;
import com.rpamis.pattern.chain.plugin.ChainBuilder;

/**
 * 可变数据责任链Builder
 *
 * @author benym
 * @date 2023/12/25 17:22
 */
@ChainBuilder(value = "VariableChain")
public interface VariableChainPipelineBuilder<T> extends Add<T> {

}
