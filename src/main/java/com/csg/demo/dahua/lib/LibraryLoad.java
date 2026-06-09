package com.csg.demo.dahua.lib;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * 大华 NetSDK 原生动态库加载工具类。
 * <p>
 * 供 JNA 在调用 {@code Native.load()} 之前，解析库名、确定平台目录、
 * 必要时从 classpath 解压 .dll/.so 到本地目录，并返回可供加载的绝对路径。
 * </p>
 * <p>
 * 典型调用链：{@link NetSDKLib#NETSDK_INSTANCE} → {@link #getLoadLibrary(String)} → JNA 加载。
 * 业务项目中通常在调用前先执行 {@link #setExtractPath(String)}，将库目录指向
 * {@link com.csg.demo.dahua.support.NativeLibraryExtractor} 准备好的临时目录。
 * </p>
 *
 * @author 
 * @version 1.0
 * @since 2020/11/14
 */
@Slf4j
public class LibraryLoad {

  /** 操作系统类型标识：Windows */
  private static final String ARCH_WINDOWS = "win";
  /** 操作系统类型标识：Linux */
  private static final String ARCH_LINUX = "linux";
  /** 操作系统类型标识：macOS */
  private static final String ARCH_MAC = "mac";
  /** 架构位数：64 位 */
  private static final int PREFIX_64 = 64;
  /** 架构位数：32 位 */
  private static final int PREFIX_32 = 32;
  /** 架构标识：ARM */
  private static final String PREFIX_ARM = "ARM";
  /** Windows 动态库扩展名 */
  private static final String EXTERNAL_WIN = ".dll";
  /** Linux 动态库扩展名 */
  private static final String EXTERNAL_LINUX = ".so";
  /** macOS 动态库扩展名 */
  private static final String EXTERNAL_MAC = ".dylib";

  /** 解析 dynamic-lib-load.xml 的工具，懒加载单例 */
  private static DynamicParseUtil dynamicParseUtil;

  /** 当前平台对应的资源子目录名，如 win64、linux64 */
  private static String currentFold;

  /**
   * 原生库最终加载/解压的目标目录。
   * windows下：C:\Users\xxx\AppData\Local\Temp\dahua-sdk-windows64-123456789\
   * linux下：/tmp/dahua-sdk-linux64-123456789/
   * 默认为 {@code java.io.tmpdir}，可通过 {@link #setExtractPath(String)} 覆盖。
   */
  private static String EXTRACT_PATH = System.getProperty("java.io.tmpdir");

  /**
   * 设置原生库的加载根目录。
   * <p>
   * 必须在首次调用 {@link #getLoadLibrary(String)} 之前设置（若需自定义路径）。
   * 业务项目中由 {@link com.csg.demo.dahua.lifecycle.DahuaSdkLifecycle} 在 SDK 初始化前调用，
   * 将路径指向已通过 yaml 配置复制好的临时目录。
   * </p>
   *
   * @param path 原生库所在文件夹的绝对路径，JNA 将从该目录加载 dhnetsdk、dhconfigsdk 等
   */
  public static void setExtractPath(String path) {
    EXTRACT_PATH = path;
  }

  /**
   * 获取指定逻辑库名对应的、可供 JNA {@code Native.load()} 使用的绝对路径。
   * <p>
   * 这是整个类的核心入口，{@link NetSDKLib} 加载 NETSDK_INSTANCE / CONFIG_INSTANCE 时均调用此方法。
   * </p>
   * <p>
   * 实际流程：
   * <ol>
   *   <li>识别当前 OS/架构，得到 win64、linux64 等平台目录名</li>
   *   <li>首次调用时读取 classpath 下的 dynamic-lib-load.xml，批量解压该平台所需的全部依赖库</li>
   *   <li>将逻辑库名转换为带前缀/后缀的真实文件名（如 dhnetsdk → dhnetsdk.dll 或 libdhnetsdk.so）</li>
   *   <li>拼接 {@link #EXTRACT_PATH} 与文件名，返回绝对路径</li>
   * </ol>
   * </p>
   *
   * @param libraryName 逻辑库名，如 dhnetsdk、dhconfigsdk、ImageAlg
   * @return 原生库绝对路径，例如 {@code C:/Users/.../Temp/dahua-sdk-windows64-xxx/dhnetsdk.dll}
   */
  public static String getLoadLibrary(String libraryName) {
    currentFold = getLibraryFold(); // win64 或 amd64
    if (dynamicParseUtil == null) { // 解压循环在项目里多余，但初始化 dynamicParseUtil 仍然必要。
      try {
        dynamicParseUtil =
            new DynamicParseUtil(
                LibraryLoad.class.getClassLoader().getResourceAsStream("dynamic-lib-load.xml"));
      } catch (ParserConfigurationException | IOException | SAXException e) {
        e.printStackTrace();
      }
    }
    String fullName = getLibraryName(libraryName);
    String path = EXTRACT_PATH;
    if (!(EXTRACT_PATH.endsWith("/") || EXTRACT_PATH.endsWith("\\"))) {
      path = EXTRACT_PATH + "/";
    }
    System.out.println("加载到的原生SDK资源库为: " + path + fullName);
    return path + fullName;
  }

  /**
   * 将逻辑库名转换为当前平台下的真实文件名（含前缀与扩展名）。
   * <p>
   * 转换规则示例：
   * <ul>
   *   <li>Windows：dhnetsdk → dhnetsdk.dll</li>
   *   <li>Linux：dhnetsdk → libdhnetsdk.so</li>
   *   <li>Linux：libcrypto → libcrypto.so（已有 lib 前缀则不再重复添加）</li>
   * </ul>
   * 库名大小写及别名校正依赖 {@link DynamicParseUtil#compareLibName(String, String)}，
   * 与 dynamic-lib-load.xml 中声明的名称对齐。
   * </p>
   *
   * @param libName 逻辑库名，如 dhnetsdk、ImageAlg、libeay32
   * @return 带扩展名的完整文件名，如 dhnetsdk.dll、libdhnetsdk.so
   */
  private static String getLibraryName(String libName) {
    String dir = currentFold;
    String libPrefix = "";
    String libExtension = EXTERNAL_WIN;

    if (!dir.contains("win")) {
      libPrefix = "lib";
      if (dir.contains("linux")) {
        libExtension = EXTERNAL_LINUX;
      } else {
        libExtension = EXTERNAL_MAC;
      }
    }
    libName = dynamicParseUtil.compareLibName(currentFold, libName);
    // 库名已以 lib 开头时不再追加 lib 前缀，避免 liblibcrypto.so
    return (libName.startsWith("lib") ? "" : libPrefix) + libName + libExtension;
  }

  /**
   * 根据当前 JVM 的运行环境，推断原生库所在的平台目录名。
   * <p>
   * 返回值用于：
   * <ul>
   *   <li>classpath 资源路径前缀（win64/、linux64/ 等）</li>
   *   <li>{@link DynamicParseUtil#getLibsSystem(String)} 选取 xml 中对应平台的库列表</li>
   * </ul>
   * 映射示例：Windows amd64 → win64，Linux amd64 → linux64，macOS → mac64。
   * </p>
   *
   * @return 平台目录名，如 win64、linux64、linux32、mac64、linuxARM
   */
  private static String getLibraryFold() {
    String osType;
    String osName = System.getProperty("os.name");
    if (osName.toLowerCase().startsWith("linux")) {
      osType = ARCH_LINUX;
    } else if (osName.toLowerCase().startsWith("mac")
        || osName.toLowerCase().startsWith("darwin")) {
      osType = ARCH_MAC;
    } else if (osName.toLowerCase().startsWith("windows")) {
      osType = ARCH_WINDOWS;
    } else {
      osType = "";
    }
    String arch = System.getProperty("os.arch");
    arch = arch.toLowerCase().trim();
    if ("i386".equals(arch) || "i686".equals(arch) || "x86".equals(arch)) {
      arch = PREFIX_32 + "";
    } else if ("x86_64".equals(arch) || "amd64".equals(arch)) {
      arch = PREFIX_64 + "";
    } else if (arch.startsWith("arm")) {
      arch = PREFIX_ARM + "";
    } else {
      arch = PREFIX_ARM + "";
    }
    log.info("\n原生库所在平台目录名：{}", (osType + arch));
    return osType + arch;
  }
}
