## BottomTabView
[![GitHub](https://img.shields.io/github/license/Mystery0Tools/BottomTabView.svg)](https://github.com/Mystery0Tools/BottomTabView/blob/master/LICENSE) [![GitHub release](https://img.shields.io/github/release/Mystery0Tools/BottomTabView.svg)](https://github.com/Mystery0Tools/BottomTabView/releases) ![GitHub Release Date](https://img.shields.io/github/release-date/Mystery0Tools/BottomTabView.svg) [![JitPack](https://img.shields.io/jitpack/v/Mystery0Tools/BottomTabView.svg)](https://jitpack.io/#Mystery0Tools/BottomTabView)


**README：[中文](https://github.com/Mystery0Tools/BottomTabView/blob/master/README.MD "中文") | [English](https://github.com/Mystery0Tools/BottomTabView/blob/master/README_EN.MD "English")**
### Download
通过Gradle集成：将以下代码添加进您的项目级的Build.gradle最后：
```groovy
    allprojects {
    		repositories {
    			...
    			maven { url 'https://jitpack.io' }
    		}
    	}
```
然后添加依赖：
```groovy
	dependencies {
	        implementation 'com.github.Mystery0Tools:BottomTabView:x.x.x'
	}
```
或者通过Maven：添加Jitpack仓库到您的构建文件中：
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
添加依赖：
```xml
	<dependency>
	    <groupId>com.github.Mystery0Tools</groupId>
	    <artifactId>BottomTabView</artifactId>
	    <version>x.x.x</version>
	</dependency>
```
具体版本号请通过[Relase页面](https://github.com/Mystery0Tools/BottomTabView/releases "Relase页面")获取

### 具体效果
![](https://github.com/Mystery0Tools/BottomTabView/blob/master/resource/preview.gif)
### 如何使用

#### 往您的布局文件中添加控件
```xml
<vip.mystery0.bottomTabView.BottomTabView
	android:id="@+id/bottomTabView"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content" />
```

#### 配置属性
获取控件实例：
```java
BottomTabView bottomTabView = findViewById(R.id.bottomTabView);
```
除了设置菜单项和设置当前选中的项，其他的自定义配置都通过[BottomTabViewConfig类](https://github.com/Mystery0Tools/BottomTabView/blob/master/bottomTabView/src/main/java/vip/mystery0/bottomTabView/BottomTabViewConfig.kt "BottomTabViewConfig类")定义。

#### 构建菜单项
```java
List<BottomTabItem> menuList = new ArrayList<>();
menuList.add(new BottomTabItem("11", R.drawable.ic_android_black_24dp));
menuList.add(new BottomTabItem("12", R.drawable.ic_android_black_24dp));
menuList.add(new BottomTabItem("33133", R.drawable.ic_android_black_24dp));
```
菜单项必须是 BottomTabItem 类的对象，对应的构造函数传入的是现在的菜单名称和显示的 drawable 资源。
构造函数的第二个参数必须是 drawable 的资源引用，必须是矢量图资源（用于着色）。

#### 设置菜单项
```java
bottomTabView.setMenuList(menuList).init();
```
需要在设置之后调用一次 init 方法更新视图。

#### 自定义配置
```java
bottomTabView.setConfig(BottomTabViewConfig()
	.setSelectedColor(Color.BLACK)//设置选中的条目颜色
	.setUnSelectedColor(Color.BLUE)//设置未选中条目颜色
	.setMarginTop(16f)//设置距离顶部间距，单位dp
	.setMarginBottom(24f)//设置距离底部间距，单位dp
	.setItemIconSize(24f)//设置图标大小，单位dp
	.setItemTextSize(24f)//设置文字大小，单位sp
	.setLineHeight(1f)//设置顶部分割线高度，单位px
	.isShowRipple(true)//是否显示点击时水波效果
	.isShowGradientColors(false)//是否显示渐变图标（需要设置渐变的颜色数组）
	.setGradientColors(new int[]{Color.WHITE, Color.BLACK}))//设置渐变颜色数组
	.init();//更新视图
```

#### 自定义属性
| 属性  | 值类型  | 说明 |
| :------------: | :------------: | :------------: |
| selected_color  | color  |选中条目颜色|
| unselected_color  |color|  未选中条目颜色 |
| margin_top |  dimension | 距离顶部间距|
| margin_bottom  |  dimension | 距离底部间距|
| line_height | dimension  |分割线高度|
| item_text_size |  dimension  | 菜单项文字大小|
| item_icon_size |  dimension  |菜单项图标大小|
| show_ripple  | boolean  |是否显示点击水波效果|
| show_gradient_colors  |  boolean |是否显示渐变图标|
#### 设置当前选中条目
```java
bottomTabView.setCheckedItem(2);
```

#### 设置菜单项点击监听
```java
bottomTabView.setOnItemSelectedListener(new BottomTabView.OnItemSelectedListener() {
	@Override
	public void onItemSelected(@NotNull BottomTabItem bottomTabItem) {
		//Do Something you want to do
	}
});
```

## LICENSE
    Copyright (C) 2018 Mystery0.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
