package jvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        XClassLoader loader = new XClassLoader();
        Class<?> xclass = loader.findClass("Hello");
        Object obj = xclass.newInstance();
        Method method = xclass.getMethod("hello");
        method.invoke(obj);
    }
}
