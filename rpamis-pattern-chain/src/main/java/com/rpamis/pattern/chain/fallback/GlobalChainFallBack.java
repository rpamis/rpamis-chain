package com.rpamis.pattern.chain.fallback;

import com.rpamis.pattern.chain.definition.ChainFallBack;

/**
 * 责任链全局降级接口
 *
 * @author benym
 * @date 2023/8/17 15:25
 */
public interface GlobalChainFallBack<T> extends ChainFallBack<T> {

}
