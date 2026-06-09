package com.csg.demo.dahua.support;

import com.csg.demo.config.DahuaPlatform;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 平台检测器
 * 根据操作系统名称和架构检测平台
 * 支持Windows64、Linux64、UNSUPPORTED
 * 
 * @author George
 * @version 1.0
 * @since 2026-06-08
 */
@Component
public class PlatformDetector {

    /**
     * 1. 获取操作系统名称和架构
     * 2. 判断是否为64位架构
     * 3. 判断操作系统是否为Windows64
     * 4. 判断操作系统是否为Linux64
     * 5. 返回平台
     * @return
     */
    public DahuaPlatform detect() {
        String osName = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        String arch = System.getProperty("os.arch", "").toLowerCase(Locale.ROOT);

        boolean is64Bit = "amd64".equals(arch) || "x86_64".equals(arch);
        if (!is64Bit) {
            return DahuaPlatform.UNSUPPORTED;
        }
        if (osName.startsWith("windows")) {
            return DahuaPlatform.WINDOWS64;
        }
        if (osName.startsWith("linux")) {
            return DahuaPlatform.LINUX64;
        }
        return DahuaPlatform.UNSUPPORTED;
    }

    /**
     * 获取当前平台描述
     * 返回操作系统名称和架构
     * 例如：Windows/x86_64
     * 例如：Linux/x86_64
     * 例如：Mac/x86_64
     * 例如：Unknown/Unknown
     * @return
     */
    public String currentPlatformDescription() {
        return System.getProperty("os.name", "unknown") + "/" + System.getProperty("os.arch", "unknown");
    }
}
