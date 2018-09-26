package cn.annto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 -
 * Created by mwt on 2018/9/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface ViewBind {

    //当只有一个属性的时候，可以使用value来表示，在使用时可以省略掉key和=
    public int value();
}
