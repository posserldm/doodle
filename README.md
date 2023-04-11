## 涂鸦 App

### 项目说明
此项目仅供学习交流使用

### 实现效果如下
![效果图](./readmeImages/20230411185918.jpg)

### 相关知识点与项目相关代码
|相关知识点|项目相关代码链接|
|---------|--------|
|自定义View|[自定义View实现画板](./app/src/main/java/posserldm/doodle/panel/MainPaintView.kt)|
|Toolbar实现自定义Tiltle|[参见androidx.appcompat.widget.Toolbar标签](./app/src/main/res/layout/activity_main.xml)|
|Glide图型转换|[参见MainActivity.kt中的initUserIcon()方法](./app/src/main/java/posserldm/doodle/MainActivity.kt)|
|RecycleView基本使用|利用RecycleView实现颜色选择器与笔画大小选择器的展示。[apdapt与holder实现](./app/src/main/java/posserldm/doodle/tools/adapter/)。[Fragment中对RecycleView的具体调用](./app/src/main/java/posserldm/doodle/tools/fragment/)|
|ViewPager2的基本使用|[ViewPager2实现颜色选择、笔画大小面板选择容器实现](./app/src/main/java/posserldm/doodle/tools/fragment/PaintToolPanelFragment.kt)|
|Viewmodel实现多个Fragment之间的数据通信|参考[MainActivity]()中的initVMs方法与[工具面板](./app/src/main/java/posserldm/doodle/tools/fragment/)中的相关调用|
|ViewBinding的基本使用|[ViewBinding基本使用](./app/src/main/java/posserldm/doodle/MainActivity.kt) binding属性为具体实现|
|动态权限声明|[动态权限申请](./app/src/main/java/posserldm/doodle/MainActivity.kt) requestPermissions()方法有具体实现|
|Dialog的基本使用|[Dialog基本实现](./app/src/main/java/posserldm/doodle/dialog/)|
|Bitmap相关知识点|[Bitmap利于bitmap实现导入背景图片与导出作品功能](./app/src/main/java/posserldm/doodle/panel/MainPaintView.kt) 具体实现为getBitmap()方法、fun saveImageToGallery(bitmap: Bitmap)方法与setBackgroundImage(bitmap: Bitmap)|
|调用系统相册基本实现|参见[MainActivity.kt中的registerForImageActivity()方法](./app/src/main/java/posserldm/doodle/MainActivity.kt)|

### 码字不易，喜欢的朋友能否打赏碎银几文?
![](./readmeImages/20230411202145.jpg)
![](./readmeImages/20230411202156.jpg)