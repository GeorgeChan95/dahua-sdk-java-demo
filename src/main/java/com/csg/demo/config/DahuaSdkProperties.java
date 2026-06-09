package com.csg.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "dahua-sdk")
public class DahuaSdkProperties {

    // Windows64属性 对应yaml中的 windows64 属性
    private PlatformLibraries windows64 = new PlatformLibraries();

    // Linux64属性 对应yaml中的 linux64 属性
    private PlatformLibraries linux64 = new PlatformLibraries();

    public PlatformLibraries getWindows64() {
        return windows64;
    }

    /**
     * 此方法在DahuaSdkProperties的构造方法中被调用
     * @param windows64 
     */
    public void setWindows64(PlatformLibraries windows64) {
        this.windows64 = windows64;
    }

    public PlatformLibraries getLinux64() {
        return linux64;
    }

    public void setLinux64(PlatformLibraries linux64) {
        this.linux64 = linux64;
    }

    /**
     * 方法主要实现了根据平台获取原生库列表
     * 1. 如果平台为Windows64,则返回windows64属性
     * 2. 如果平台为Linux64,则返回linux64属性
     * 3. 如果平台为其他,则返回空属性
     * @param platform
     * @return
     */
    public PlatformLibraries getLibraries(DahuaPlatform platform) {
        if (platform == DahuaPlatform.WINDOWS64) {
            return windows64;
        }
        if (platform == DahuaPlatform.LINUX64) {
            return linux64;
        }
        return new PlatformLibraries();
    }

    /**
     * 平台原生库配置
     * 包含原生库列表
     * @author George
     * @version 1.0
     * @since 2026-06-08
     */
    public static class PlatformLibraries {
        private List<String> libraries = new ArrayList<>();

        public List<String> getLibraries() {
            return libraries;
        }

        /**
         * 设置原生库列表
         * 加载时机：在DahuaSdkLifecycle的prepareNativeLibraries方法中加载
         * 加载方式：使用LibraryLoad.setExtractPath方法设置原生库路径
         * @param libraries
         */
        public void setLibraries(List<String> libraries) {
            this.libraries = libraries;
        }
    }
}
