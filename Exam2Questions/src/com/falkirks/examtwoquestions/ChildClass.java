package com.falkirks.examtwoquestions;

import java.lang.reflect.Method;

public class ChildClass extends SuperClass{
    public void doStuff(){
        try {
            for(Method method : this.getClass().getSuperclass().getDeclaredMethods()){
                method.setAccessible(true);
                System.out.println(method);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
