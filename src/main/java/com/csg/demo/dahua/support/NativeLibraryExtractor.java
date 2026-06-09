package com.csg.demo.dahua.support;

import com.csg.demo.config.DahuaPlatform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * 大华 SDK 原生库提取器。
 * <p>
 * JNA 的 {@code Native.load()} 只能加载文件系统上的真实路径，无法直接加载 jar 包内
 * 或 {@code classpath:} 资源。因此启动时需要先把 application.yaml 中配置的
 * {@code classpath:libs/win64/*.dll} / {@code classpath:libs/linux64/*.so}
 * 复制到临时目录，再交给 {@link com.csg.demo.dahua.lib.LibraryLoad#setExtractPath(String)} 使用。
 * </p>
 * <p>
 * 调用时机：{@link com.csg.demo.dahua.lifecycle.DahuaSdkLifecycle#start()} 中，
 * 在访问 {@link com.csg.demo.dahua.lib.NetSDKLib#NETSDK_INSTANCE} 之前执行。
 * </p>
 *
 * @author George
 * @since 2026-06-08
 */
@Slf4j
@Component
public class NativeLibraryExtractor {

    private final ResourceLoader resourceLoader;

    public NativeLibraryExtractor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 将指定平台所需的全部原生库复制到同一临时目录。
     * <p>
     * 所有依赖库必须位于同一目录，否则 {@code dhnetsdk.dll} 加载时可能找不到
     * {@code dhconfigsdk.dll}、{@code avnetsdk.dll} 等同级依赖。
     * </p>
     *
     * @param platform         当前运行平台（windows64 / linux64）
     * @param libraryLocations 原生库资源路径列表，来自 {@code dahua-sdk.*.libraries} 配置，
     *                         格式通常为 {@code classpath:libs/win64/dhnetsdk.dll}
     * @return 复制完成后的临时目录绝对路径，供 {@link com.csg.demo.dahua.lib.LibraryLoad} 加载使用
     * @throws IOException              创建临时目录或复制文件失败
     * @throws IllegalStateException  未配置任何库，或某个 classpath 资源不存在
     */
    public Path extract(DahuaPlatform platform, List<String> libraryLocations) throws IOException {
        if (CollectionUtils.isEmpty(libraryLocations)) {
            throw new IllegalStateException("缺少大华SDK原生库配置，当前系统为： " + platform.getConfigKey());
        }

        // 按平台创建独立临时目录，避免多实例或不同平台库文件互相覆盖,临时目录名称格式为：dahua-sdk-platform-
        // createTempDirectory 方法会创建一个临时目录,并返回目录的绝对路径(临时目录会自动拼接时间戳字符串)
        // 示例：platform = windows64,则 targetDirectory = /tmp/dahua-sdk-windows64-1717836000000
        // 示例：platform = linux64,则 targetDirectory = /tmp/dahua-sdk-linux64-1717836000000
        Path targetDirectory = Files.createTempDirectory("dahua-sdk-" + platform.getConfigKey() + "-");
        log.info("创建临时目录成功，目录路径为：{}", targetDirectory.toAbsolutePath());
        for (String libraryLocation : libraryLocations) { // 遍历原生库列表,将原生库复制到临时目录
            // 加载jar包内的资源，例如：classpath:libs/win64/avnetsdk.dll
            Resource resource = resourceLoader.getResource(libraryLocation);
            // 如果原生库资源不存在,则抛出异常
            if (!resource.exists()) {
                throw new IllegalStateException("大华SDK原生库资源未找到: " + libraryLocation);
            }
            copyResource(resource, libraryLocation, targetDirectory);
        }
        return targetDirectory;
    }

    /**
     * 将单个原生库资源复制到目标目录。
     * <p>
     * 开发环境（IDE 运行）时资源可能在文件系统上；打包成 jar 包后资源在 jar 包内部，
     * 此时只能通过 {@link InputStream} 读取并写出到临时文件，两种方式本方法均支持。
     * </p>
     *
     * @param resource        Spring 解析后的资源对象
     * @param location        原始配置路径，用于在无法从 Resource 获取文件名时兜底解析
     * @param targetDirectory 目标临时目录
     */
    private void copyResource(Resource resource, String location, Path targetDirectory) throws IOException {
        // 获取原生库文件名,根据原生库路径获取原生库文件名
        String filename = resource.getFilename();
        // 如果原生库文件名为空,则从原生库路径截取文件名
        // 部分 classpath 资源（尤其 jar 内嵌套路径）getFilename() 可能为空，从配置路径截取文件名
        if (!StringUtils.hasText(filename)) {
            // 示例：location = classpath:libs/win64/avnetsdk.dll,则 filename = avnetsdk.dll
            filename = location.substring(location.lastIndexOf('/') + 1);
        }

        // targetDirectory.resolve(filename) 在临时目录下拼出每个原生库要复制到的完整目标路径。
        // 示例：targetDirectory = /tmp/dahua-sdk-windows64-1717836000000, filename = avnetsdk.dll
        // 则 target = /tmp/dahua-sdk-windows64-1717836000000/avnetsdk.dll
        Path target = targetDirectory.resolve(filename);
        // 读取原生库资源,将原生库资源读取到临时文件
        try (InputStream inputStream = resource.getInputStream()) {
            // 复制原生库资源到临时文件
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        }
        // Linux 下 .so 需要可执行权限，否则 dlopen 可能失败
        // setExecutable: 设置文件为可执行
        // true: 设置为可执行
        // false: 不设置为可执行
        target.toFile().setExecutable(true, false); // 设置原生库文件为可执行
    }
}
