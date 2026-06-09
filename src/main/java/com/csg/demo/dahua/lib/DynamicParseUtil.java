package com.csg.demo.dahua.lib;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 47081
 * @version 1.0
 * @description
 * @date 2021/3/10
 */
public class DynamicParseUtil {
  private DynamicLibParseHandler handler;
  private SAXParserFactory saxParserFactory;
  private SAXParser saxParser;

  /**
   * 适配各系统动态库名称大小写不同,以及lib前缀造成的找不到库的问题
   *
   * @param currentSystem 当前系统:win64,win32,linux64,linux32,mac64
   * @param libName 动态库名称
   * @return
   */
  public String compareLibName(String currentSystem, String libName) {
    String dynamicLibName = libName;
    List<String> libs = handler.getLibsBySystem(currentSystem);
    if (currentSystem.equalsIgnoreCase("win64")) {
      return findLibs(libs, libName);
    }
    if (libName.startsWith("lib")) {
      dynamicLibName = libName.substring(3);
    }
    return findLibs(libs, dynamicLibName);
  }

  private String findLibs(List<String> libs, String libName) {
    for (String lib : libs) {
      if (libName.equalsIgnoreCase(lib)) {
        return lib;
      }
    }
    return "";
  }

  public List<String> getLibsSystem(String system) {
    return handler.getLibsBySystem(system);
  }

  private DynamicParseUtil() throws ParserConfigurationException {
    // 获取SAX分析器的工厂实例，专门负责创建SAXParser分析器
    saxParserFactory = SAXParserFactory.newInstance();
    // 获取SAXParser分析器的实例
    try {
      saxParser = saxParserFactory.newSAXParser();
      handler = new DynamicLibParseHandler();
    } catch (ParserConfigurationException | SAXException e) {
      throw new ParserConfigurationException();
    }
  }

  public DynamicParseUtil(InputStream inputSteam)
      throws ParserConfigurationException, IOException, SAXException {
    this();
    saxParser.parse(inputSteam, handler);
  }

  /**
   * 继承 SAX 的 DefaultHandler，负责解析 dynamic-lib-load.xml，把各平台需要的原生库列表读进内存
   */
  private static class DynamicLibParseHandler extends DefaultHandler {
    // 存储在dynamic-lib-load.xml中的各平台需要的原生库列表
    private final HashMap<String, List<String>> dynamics = new HashMap<>();
    // 合法的平台标签名，用于识别 XML 元素
    private final List<String> systems = Arrays.asList("win64", "win32", "linux64", "linux32", "mac64", "linuxARM");
    // 当前正在解析的平台标签名
    private String currentDynamicSystem;
    // 当前平台需要的原生库列表
    private List<String> libs;

    /**
     * 按平台名查询已解析的库列表。
     * 1. 根据平台名查询已解析的库列表
     * 2. 如果查询不到，则返回空列表
     * 3. 如果查询到，则返回对应的库列表
     * @param system
     * @return
     */
    public List<String> getLibsBySystem(String system) {
      return dynamics.get(system);
    }

    /**
     * SAX 解析开始时的回调方法，在解析 XML 文档时调用。
     */
    @Override
    public void startDocument() throws SAXException {
      super.startDocument();
    }

    /**
     * 遇到开始标签时触发，用于识别「进入某个平台节点」。
     * 1. 如果标签名在合法平台列表中，则设置 currentDynamicSystem 为当前平台名
     * 2. 如果 libs 为空，则创建一个空列表
     * @param uri
     * @param localName
     * @param qName 标签名，如 win64、linux64、lib
     * @param attributes 标签属性（本 XML 基本不用）
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {
      super.startElement(uri, localName, qName, attributes);
      if (systems.contains(qName)) {
        currentDynamicSystem = qName;
        if (libs == null) {
          libs = new ArrayList<>();
        }
      }
    }

    /**
     * 遇到结束标签时触发，用于识别「离开某个平台节点」。
     * - 把 currentDynamicSystem 和 libs 存入 dynamics
     * - 将 libs 置为 null，为下一个平台做准备
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      super.endElement(uri, localName, qName);
      if (systems.contains(qName)) {
        // 保存到hashmap中
        dynamics.put(currentDynamicSystem, libs);
        // 清除libs
        libs = null;
      }
    }

    /**
     * 遇到字符内容时触发，用于识别「平台节点内的原生库列表」。
     * - 如果字符内容不为空，则将字符内容添加到 libs 列表中
     * @param ch 字符数组
     * @param start 字符数组起始索引
     * @param length 字符数组长度
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      super.characters(ch, start, length);
      String lib = new String(ch, start, length);
      if (!lib.trim().isEmpty()) { // trim() 后非空才 add，过滤换行、缩进等空白
        libs.add(lib);
      }
    }
  }
}
