package com.rpamis.pattern.chain.entity;

import java.io.Serializable;

/**
 * 单个责任链结果存储实体
 *
 * @date 2023/3/7 14:41
 * @author benym
 */
public class ChainResult implements Serializable {

    private static final long serialVersionUID = 4901947213957067898L;

    /**
     * 当前链上的具体处理类Class
     */
    private Class<?> handlerClass;

    /**
     * 当前处理类处理结果
     */
    private boolean processResult;

    /**
     * 当前处理类消息
     */
    private String message;

    public ChainResult(Class<?> handlerClass, boolean processResult, String message) {
        this.handlerClass = handlerClass;
        this.processResult = processResult;
        this.message = message;
    }

    public Class<?> getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(Class<?> handlerClass) {
        this.handlerClass = handlerClass;
    }

    public boolean isProcessResult() {
        return processResult;
    }

    public void setProcessResult(boolean processResult) {
        this.processResult = processResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChainResult{" +
                "handlerClass=" + handlerClass +
                ", processResult=" + processResult +
                ", message='" + message + '\'' +
                '}';
    }
}
