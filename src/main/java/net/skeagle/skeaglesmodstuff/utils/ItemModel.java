package net.skeagle.skeaglesmodstuff.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemModel {

    ItemModelType value() default ItemModelType.GENERATED;

    enum ItemModelType {
        GENERATED,
        HANDHELD
    }

}
