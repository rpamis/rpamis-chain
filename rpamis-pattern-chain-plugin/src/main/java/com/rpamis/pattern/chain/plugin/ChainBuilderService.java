package com.rpamis.pattern.chain.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记ChainBuilder实现类
 *
 * @author benym
 * @date 2024/1/9 17:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ChainBuilderService {

}
