package com.rpamis.chain.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储所有责任链执行结果，以及整个链按照策略的计算结果
 *
 * @date 2023/3/30 20:33
 * @author benym
 */
public class CompleteChainResult implements Serializable {

    private static final long serialVersionUID = -8236680440525390183L;

    /**
     * 整个链计算结果，如果有一个false则为false
     */
    private final boolean allow;

    /**
     * 所有处理类->处理结果Map
     */
    private final ConcurrentHashMap<Class<?>, Boolean> chainResultMap = new ConcurrentHashMap<>(16);

    private final List<ChainResult> chainResults;

    private void initMap(List<ChainResult> chainResults) {
        chainResults.forEach(chainResult -> chainResultMap.putIfAbsent(chainResult.getHandlerClass(), chainResult.isProcessResult()));
    }

    public CompleteChainResult(boolean allow, List<ChainResult> chainResults) {
        this.allow = allow;
        this.chainResults = chainResults;
        this.initMap(chainResults);
    }

    public boolean isAllow() {
        return allow;
    }

    public List<ChainResult> getChainResults() {
        return chainResults;
    }

    public <T> Boolean get(Class<T> cls) {
        return chainResultMap.get(cls);
    }
}
