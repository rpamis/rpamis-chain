package com.rpamis.pattern.chain.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记ChainBuilderManager的实现类
 *
 * @author benym
 * @date 2024/1/3 23:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChainBuilderManagerImpl {

}
