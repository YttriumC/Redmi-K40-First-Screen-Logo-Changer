# Redmi-K40-First-Screen-Logo-Changer
修改红米K40手机的开机第一屏Logo界面(logo分区)
------------
### 鸣谢:[小米 9se 手机自定义启动画面 splash/logo](https://doobom.me/mi9se-splash-logo-image-modify)
因为是直接修改logo.img文件, 可以dd出手机logo分区, 也可以在线刷包中提取.
```
#需要root权限
dd if=/dev/block/bootdevice/by-name/logo of=./logo.img bs=1048576 count=64
```
图片需使用24位bmp位图, Photoshop存储为bmp Windows 24位.

#### 版权声明: 所有图片均收集自互联网, 版权归属于原作者, 如有侵权请联系删除.