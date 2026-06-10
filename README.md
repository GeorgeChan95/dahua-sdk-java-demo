# dahua-sdk-java-demo

基于大华 NetSDK（JNA）的 Spring Boot 二次开发示例项目，提供设备登录、云台控制、远程抓图、录像查询与下载、PTZ 位置查询、语音对讲等 REST / WebSocket 接口，支持 **Windows 64 位** 与 **Linux 64 位** 跨平台运行。

## 功能特性


| 模块        | 说明                                  |
| --------- | ----------------------------------- |
| 设备登录 / 注销 | 高安全等级登录，会话缓存，幂等登录                   |
| 自动重连      | SDK 断线回调 + 60 秒心跳检测离线自动重连           |
| 云台控制      | 八方向、变倍、变焦、光圈等 PTZ 命令                |
| 预置点       | 查询预置点列表、跳转预置点                       |
| PTZ 位置    | 查询水平/垂直/变倍/聚焦等位置信息                  |
| 远程抓图      | 异步回调转同步，返回 JPEG 字节并本地保存             |
| 录像查询      | 按时间段查询录像文件列表                        |
| 录像下载      | 异步下载 MP4 文件，支持进度查询                  |
| 语音对讲      | WebSocket 双向流式传输 G711A / G711U 裸音频帧 |


## 技术栈

- Java 8
- Spring Boot 2.3.4
- Spring WebSocket
- JNA 5.19.0
- Lombok、Hutool、Apache Commons Lang3
- 大华 NetSDK V3.061（JNA 绑定）

## 环境要求

- JDK 1.8+
- Maven 3.6+
- 操作系统：Windows 64 位 或 Linux 64 位
- 可访问的大华设备（IPC / NVR / 球机等）

## 快速开始

### 1. 准备 SDK 原生库

将大华官方 SDK 提供的原生动态库放入 `src/main/resources/libs/` 对应目录：

```
src/main/resources/libs/
├── win64/          # Windows 64 位
│   ├── dhnetsdk.dll
│   ├── dhconfigsdk.dll
│   ├── avnetsdk.dll
│   └── ...
└── linux64/        # Linux 64 位
    ├── libdhnetsdk.so
    ├── libdhconfigsdk.so
    ├── libavnetsdk.so
    └── ...
```

库文件列表以 `application.yaml` 中 `dahua-sdk` 配置为准，也可参考 `src/main/resources/dynamic-lib-load.xml`。

> 原生库体积较大，通常不纳入版本控制，需从大华官方 SDK 包中手动拷贝。

### 2. 编译

```bash
mvn clean package -DskipTests
```

### 3. 启动

```bash
java -jar target/dahua-sdk-java-demo.jar
```

或在 IDE 中直接运行 `com.csg.demo.DemoApplication`。

默认服务端口：**8877**

## 配置说明

主要配置位于 `src/main/resources/application.yaml`：

```yaml
server:
  port: 8877

dahua-sdk:
  windows64:
    libraries:
      - classpath:libs/win64/dhnetsdk.dll
      # ...
  linux64:
    libraries:
      - classpath:libs/linux64/libdhnetsdk.so
      # ...
```

应用启动时由 `DahuaSdkLifecycle` 自动检测当前平台、提取原生库并完成 SDK 初始化。

## 项目结构

```
dahua-sdk-java-demo/
├── src/main/java/com/csg/demo/
│   ├── DemoApplication.java              # 启动入口
│   ├── config/                           # Spring 配置（WebSocket、SDK 属性）
│   ├── controller/
│   │   └── DahuaDeviceController.java    # REST 接口
│   ├── websocket/
│   │   └── DahuaTalkWebSocketHandler.java # 语音对讲 WebSocket
│   ├── service/
│   │   ├── DhSdkService.java             # 业务接口
│   │   └── impl/DhSdkServiceImpl.java      # 平台路由实现
│   ├── dto/                              # 请求 / 响应 DTO
│   └── dahua/
│       ├── lifecycle/DahuaSdkLifecycle.java  # SDK 生命周期
│       ├── support/                      # 会话管理、回调注册、心跳
│       ├── enums/PtzCommand.java         # 云台命令枚举
│       └── lib/                          # NetSDK JNA 绑定
└── src/main/resources/
    ├── application.yaml
    ├── dynamic-lib-load.xml
    └── libs/                             # SDK 原生库（需手动放置）
```

## API 接口

所有 REST 接口前缀：`/dahua/device`

除登录 / 注销外，其余接口在未登录时会根据请求中的 `username` / `password` **自动登录**。

### 设备登录 / 注销


| 方法   | 路径        | 说明                                                  |
| ---- | --------- | --------------------------------------------------- |
| POST | `/login`  | 登录设备（Query 参数：`ip`, `port`, `username`, `password`） |
| POST | `/logout` | 注销设备（Query 参数：`ip`, `port`）                         |


### 云台控制


| 方法   | 路径              | 说明      |
| ---- | --------------- | ------- |
| POST | `/control`      | 云台控制    |
| POST | `/ptz/location` | 查询云台位置  |
| POST | `/preset/list`  | 查询预置点列表 |
| POST | `/preset/goto`  | 跳转预置点   |


**云台控制请求示例：**

```json
{
  "ip": "192.168.1.64",
  "port": 37777,
  "username": "admin",
  "password": "admin123",
  "channelId": 0,
  "command": 0,
  "stop": false
}
```

`command` 对应 SDK `NET_PTZ_ControlType` 值，也可参考 `PtzCommand` 枚举：


| 命令         | 值   | 说明   |
| ---------- | --- | ---- |
| UP         | 0   | 向上   |
| DOWN       | 1   | 向下   |
| LEFT       | 2   | 向左   |
| RIGHT      | 3   | 向右   |
| LEFT_UP    | 32  | 向左上  |
| RIGHT_UP   | 33  | 向右上  |
| LEFT_DOWN  | 34  | 向左下  |
| RIGHT_DOWN | 35  | 向右下  |
| ZOOM_ADD   | 4   | 变倍放大 |
| ZOOM_DEC   | 5   | 变倍缩小 |
| FOCUS_ADD  | 6   | 变焦拉近 |
| FOCUS_DEC  | 7   | 变焦拉远 |
| IRIS_ADD   | 8   | 光圈放大 |
| IRIS_DEC   | 9   | 光圈缩小 |


`stop=false` 表示开始动作，`stop=true` 表示停止动作。

### 远程抓图


| 方法   | 路径         | 说明                         |
| ---- | ---------- | -------------------------- |
| POST | `/capture` | 远程抓图，成功返回 `image/jpeg` 字节流 |


抓图文件同时保存至 `./capture/` 目录。

### 录像


| 方法   | 路径                        | 说明          |
| ---- | ------------------------- | ----------- |
| POST | `/record/list`            | 查询录像文件列表    |
| POST | `/record/download`        | 异步下载录像（MP4） |
| POST | `/record/download/status` | 查询下载任务进度    |


**录像查询请求示例：**

```json
{
  "ip": "192.168.1.64",
  "port": 37777,
  "username": "admin",
  "password": "admin123",
  "channelId": 0,
  "startTime": "2026-06-10 00:00:00",
  "endTime": "2026-06-10 06:00:00",
  "maxCount": 100
}
```

- 时间格式：`yyyy-MM-dd HH:mm:ss`
- 单次查询 / 下载时间跨度不超过 **6 小时**
- 下载文件保存至 `./record/` 目录，格式为 **MP4**

**下载任务状态：**


| 字段       | 说明                                         |
| -------- | ------------------------------------------ |
| taskId   | 任务 ID                                      |
| status   | PENDING / DOWNLOADING / COMPLETED / FAILED |
| progress | 进度 0~100                                   |
| filePath | 本地文件路径                                     |


### 语音对讲


| 方法   | 路径              | 说明            |
| ---- | --------------- | ------------- |
| POST | `/talk/formats` | 查询设备支持的对讲编码格式 |


**WebSocket 端点：**

```
ws://{host}:8877/dahua/ws/talk?ip={ip}&port={port}&username={username}&password={password}&channelId={channelId}&encoding={G711A|G711U}
```

- 连接建立后自动开启 SDK 对讲
- 客户端通过 **二进制消息** 发送上行 G711 裸音频帧
- 设备端音频通过 **二进制消息** 回传
- 连接关闭时自动停止对讲并释放资源

## 架构设计

```
Controller / WebSocket Handler
        │
        ▼
   DhSdkService（平台路由）
        │
   ┌────┴────┐
   ▼         ▼
Linux      Windows
Session    Session
Manager    Manager
   │         │
   └────┬────┘
        ▼
AbstractDhDeviceSession（synchronized SDK 调用）
        │
        ▼
   NetSDKLib（JNA）
```

### 线程安全

- 同一设备的 SDK 操作通过 `AbstractDhDeviceSession` 的 `synchronized` 方法串行执行
- 会话缓存使用 `ConcurrentHashMap`，登录幂等，避免频繁重复登录
- 异步回调（抓图、录像下载、语音对讲）通过独立 Registry 管理，JNA Callback 保持强引用防止 GC

### 自动重连

1. SDK 全局断线 / 重连回调（`DahuaSdkLifecycle` 注册）
2. `DhHeartbeatService` 每 60 秒检测已登录设备在线状态，离线时触发 `relogin()`

## 本地输出目录


| 目录             | 内容           |
| -------------- | ------------ |
| `./capture/`   | 远程抓图 JPEG 文件 |
| `./record/`    | 录像下载 MP4 文件  |
| `./sdklog/`    | SDK 运行日志     |
| `./logs/demo/` | 应用日志         |


## 常见问题

### 启动报 `defaultSockJsTaskScheduler` / `NullBean` 错误

`@EnableScheduling` 与 `@EnableWebSocket` 共存时可能冲突。项目已在 `WebSocketConfig` 中显式声明 `taskScheduler` Bean 解决此问题。

### 登录失败

- 确认设备 IP、端口（默认 37777）、用户名密码正确
- 确认 SDK 原生库已正确放置且与操作系统匹配
- 查看 `sdklog/` 和 `logs/demo/error.log` 中的 SDK 错误码

### 抓图 / 对讲无响应

- 确认设备型号支持对应能力
- NVR 多通道设备对讲需指定正确的 `channelId`
- 对讲编码需与设备支持的格式一致（先调用 `/talk/formats` 查询）

