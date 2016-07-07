# LoadingView
a simple loadingview for android with animation


简单的带有动画效果的加载控件


screenshot



![gif](https://github.com/ldoublem/LoadingView/blob/master/screen/%E6%95%88%E6%9E%9C.gif)

![chromelogo](https://github.com/ldoublem/LoadingView/blob/master/screen/chromelogo.png)

![mac](https://github.com/ldoublem/LoadingView/blob/master/screen/computer.png)





## LVChromeLogo canvas 
```
LVChromeLogo 
use canvas paint a view like chrome logo
1,Draw three fans of 120 degrees
2,Draw three equilateral triangle
3,Draw shadow
4,Draw center circle
```

## LoadingView Usage 使用
LVLineWithText
```
     LVLineWithText.setValue(50);//0-100
```
LVFinePoiStar
```
    LVFinePoiStar.setDrawPath(false);//Draw the path of the Star
```

LVBattery

![HORIZONTAL](https://github.com/ldoublem/LoadingView/blob/master/screen/bHORIZONTAL.png)![VERTICAL](https://github.com/ldoublem/LoadingView/blob/master/screen/bVERTICAL.png)

```
    LVBattery.setBatteryOrientation(LVBattery.BatteryOrientation.VERTICAL);//LVBattery.BatteryOrientation.HORIZONTAL
    LVBattery.setShowNum(true);//show number in battery
```
LVNews
```
   LVNews.setValue(50);//0-100
```
if value has 100,you want animation you can
```
   LVNews.startAnim();
```
LVBlock
```
   LVBlock.isShadow(false);//show shadow true or false
```




other view
```java
     v.startAnim();
     v.stopAnim();
```






## About me

An android developer in Hangzhou.

If you want to make friends with me, You can email to me.
my [email](mailto:1227102260@qq.com) :smiley:


License
=======

    The MIT License (MIT)

	Copyright (c) 2016 ldoublem

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.





