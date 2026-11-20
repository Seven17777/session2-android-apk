# Practice 4 Android APK 自动构建

作者：姚其其 / Seven17777。应用是一个离线计数器，支持加一、重置及退出后保留计数，不申请网络或其他敏感权限。

## 验收步骤

1. 推送至 main，或在 Actions 中手动运行 Build Android APK。
2. 检查构建、lint 和 APK 签名验证步骤成功。
3. 从 Releases 下载 `app-debug.apk`；也可从 Actions artifact 下载。
4. 如有 Android 6.0 及以上设备，可安装后测试加一、重置、退出再打开；目前作者没有安卓设备，不能将构建成功当作真机测试通过。

## 原理与答辩

- Gradle Wrapper 固定 Gradle 8.11.1；Android Gradle Plugin 固定8.9.2；Java 17，compile/target SDK 35。版本兼容关系应匹配。
- assembleDebug 将源码、资源和 Manifest 打包成 APK；lintDebug 检查静态问题；apksigner 验证签名。
- Debug APK 同样有签名，使用构建环境自动生成的调试密钥，可以安装，不适合生产分发。独立 runner 的调试密钥可能不同，再安装新版时可能需卸载旧版。
- 正式版本需要妥善保管且保持一致的签名密钥，放在 Secrets，不能提交到仓库。此作业仅构建 debug APK，无需上传生产密钥。
- Artifact 是工作流产物，Release 提供持久版本入口；本仓库两者都提供。
- PR 只有读权限，发布任务仅在 main 构建成功后运行。

参考：https://developer.android.com/build/releases/agp-8-9-0-release-notes
