package com.slince.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.slince.security.core.properties.SecurityProperties;

/**
 * 使SecurityProperties中配置的配置读取器生效
 * @author 95307
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
