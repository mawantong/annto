package cn.annto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by mwt on 2018/9/26.
 */

public abstract class ActivityUntil extends AppCompatActivity {

    private int layoutId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        initView();
        doWrok();
    }

    protected abstract void doWrok();

    /**
     * 该方法主要就是从注解中获取到注解入的值
     */
    private void initLayout() {
        //首先判断一下是否属于当前注解的子类 ；如果是提供给别人使用的话，务必要有判空操作
        boolean isannotation = this.getClass().isAnnotationPresent(LayoutBind.class);
        if (isannotation){
            //获取当前的注解
            LayoutBind annotation = getClass().getAnnotation(LayoutBind.class);
            //获取注解中的值，并将该值赋给全局变量layoutid，然后供setContentView使用
            layoutId = annotation.value();
            //吧layout布局引入
            setContentView(layoutId);
        }
    }

    /**
     * 初始化activity中生命的字段
     */
    private void initView() {
        //获取当前class对象
        Class<? extends ActivityUntil> aClass = this.getClass();
        //获取到该class中自定义的所有字段
        Field[] declaredFields = aClass.getDeclaredFields();
        //便利所有的字段
        for (Field field: declaredFields) {
            //所有的字段便利出来了，但是我们只需要那些被加过注解的字段
            if(null != field.getAnnotations() && field.isAnnotationPresent(ViewBind.class)){
                field.setAccessible(true);//所有字段都可访问修改
                //获取字段上的注解类
                ViewBind viewBind = field.getAnnotation(ViewBind.class);
                //获取该字段通过注解注入  值，也就是 R.id.textview .....
                int viewid = viewBind.value();
                //现在字段获取到了，对应view 的id也获取到了，那就应该赋值了
                try {
                    field.set(this,findViewById(viewid));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    Log.e("error","not find view id");
                }
            }
        }
    }
}
