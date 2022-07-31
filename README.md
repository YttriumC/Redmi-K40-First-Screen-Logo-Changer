# Redmi-K40-First-Screen-Logo-Changer
修改红米K40手机的开机第一屏Logo界面(logo分区)
------------
### 鸣谢:[小米 9se 手机自定义启动画面 splash/logo](https://doobom.me/mi9se-splash-logo-image-modify)
~~因为是直接修改logo.img文件, 可以dd出手机logo分区, 也可以在线刷包中提取.~~ 更新后的程序不需要原文件, 可以自行制作文件
```
#需要root权限
dd if=/dev/block/bootdevice/by-name/logo of=./logo.img bs=1048576 count=64
```
图片需使用24位bmp位图, Photoshop存储为BMP Windows 24位. 分辨率 1080x2400
一个正常的bmp文件大小应该是7.41MB.

使用下面的命令刷入logo分区
```
fastboot flash logo logo.img
fastboot reboot
```
也可以dd回去, 将上面的 if 和 of 的路径对换即可 
接下来就是享受全新的开机界面!

### 开机图像 文件夹中有我最新制作的开机第一屏和开机动画. 打包好的bootanimation.zip将放在Release中以供下载.

#### 版权声明: 所有图片均收集自互联网, 版权归属于原作者, 如有侵权请联系删除. 代码为GPLv3协议.

## Known Issue:

刷入**MIUI13**后logo失效, MIUI13貌似没有使用LOGO分区, 从线刷包里提取出来的原图与手机上显示的不一样.
