
<!--lang: java-->
####效果如图：

![](https://github.com/ruzhan123/DirectionControlView/raw/master/gif/slide.gif)

</br>

我的博客：[详解](https://ruzhan123.github.io/2016/07/03/2016-07-03-14-DirectionControlView%E4%B8%80%E4%B8%AA%E6%96%B9%E5%90%91%E6%8E%A7%E5%88%B6%E8%87%AA%E5%AE%9A%E4%B9%89View/)


这是一个可识别上滑，下滑，左滑，右滑，方向识别与控制的自定义View。核心类就一个：DirectionControlView.java


使用方式如下：



1，把DirectionControlView.java拷贝到你的工程里，布局文件中直接使用：


```java

    <com.zhan.directioncontrolview.widget.DirectionControlView
        android:id="@+id/main_dcv"
        android:background="#41E194"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />


```


2，找到DirectionControlView对象，设置状态监听。

```java
	
		mDirectionControlView = (DirectionControlView) findViewById(R.id.main_dcv);
		mDirectionControlView.setControlStateListener(this);
	
	    @Override
	    public void singleClick() {
	        //单击
	    }
	
	    @Override
	    public void longClick() {
	        //长按
	    }
	
	    @Override
	    public void doubleClick() {
	       //双击
	    }
	
	    @Override
	    public void leftSlide() {
	        //左滑
	    }
	
	    @Override
	    public void rightSlide() {
	        //右滑
	    }
	
	    @Override
	    public void upSlide() {
	        //上滑
	    }
	
	    @Override
	    public void downSlide() {
	       //下滑
	    }


```

现在可以正常使用DirectionControlView了。


---


注意，上下左右滑动,在这里只做了最简单的判断和处理，方向识别核心代码如下：


```java

	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	        Log.i(TAG, "onFling");
	
	        float offsetX = e1.getX() - e2.getX();//X方向偏移量
	        float offsetY = e1.getY() - e2.getY();//Y方向偏移量
	
	        if (Math.abs(offsetX) > Math.abs(offsetY)) {//左滑或者右滑
	            if (e1.getX() - e2.getX() > MIN_OFFSET_VALUE) {
	                if (mDirectionControlListener != null) {//左滑
	                    mDirectionControlListener.leftSlide();
	                }
	            } else {
	                if (mDirectionControlListener != null) {//右滑
	                    mDirectionControlListener.rightSlide();
	                }
	            }
	        } else {//上滑或者下滑
	            if (e1.getY() - e2.getY() > MIN_OFFSET_VALUE) {
	                if (mDirectionControlListener != null) {//上滑
	                    mDirectionControlListener.upSlide();
	                }
	            } else {
	                if (mDirectionControlListener != null) {//下滑
	                    mDirectionControlListener.downSlide();
	                }
	            }
	        }
	        return true;
	    }


```

如果你想让这个View做更多的事，或者优化的更好，可以自行修改DirectionControlView.java
