package com.falkirks.jock;

import com.falkirks.jock.exception.BadJockDeclarationException;
import com.falkirks.jock.exception.JockObjectProtectedException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Jock<ObjectType> {
    private ObjectType object;
    private ArrayList<Entry> whiteList;

    public Jock(ObjectType object){
        this.object = object;
        this.whiteList = new ArrayList<Entry>();
        this.whiteList.add(new Entry(getExternalCaller().getClassName()));
        try {
            doJockSetup(Class.forName(getExternalCaller().getClassName()));
        }
        catch(ClassNotFoundException e){}
    }
    public ObjectType get() throws JockObjectProtectedException {
        if(cant("get")) throw new JockObjectProtectedException();

        return this.object;
    }
    public void set(ObjectType object) throws JockObjectProtectedException{
        if(cant("set")) throw new JockObjectProtectedException();
        this.object = object;
    }
    public boolean can(String action){
        return can(action, getExternalCaller().getClassName());
    }
    public boolean can(String action, Class classObject){
        for (Entry entry : whiteList){
            if(entry.matches(classObject) && entry.hasNode(action)){
                return true;
            }
        }
        return false;
    }
    public boolean can(String action, String className){
        try {
            return can(action, Class.forName(className));
        }
        catch(ClassNotFoundException e){
            return false;
        }
    }
    public boolean cant(String action){
        return !can(action);
    }
    public boolean cant(String action, Class classObject){
        return !can(action, classObject);
    }
    public boolean cant(String action, String className){
        return !can(action, className);
    }
    public void allow(Entry entry) throws JockObjectProtectedException{
        if(cant("allow")) throw new JockObjectProtectedException();
        this.whiteList.add(entry);
    }
    /*
        This function needs work.
     */
    public void deny(Entry entry) throws JockObjectProtectedException{
        if(cant("deny")) throw new JockObjectProtectedException();
        for(Entry testEntry : whiteList){
            if(testEntry.matches(entry.getEntry())){
                whiteList.remove(entry);
            }
        }
    }
    private static StackTraceElement[] getTrace(){
        return Thread.currentThread().getStackTrace();
    }
    private static StackTraceElement getExternalCaller(){
        for(StackTraceElement element : getTrace()){
            //System.out.println(element.getClassName());
            if(!element.getClassName().equals(Jock.class.getName()) && !element.getClassName().equals("java.lang.Thread")){
                return element;
            }
        }
        return null;
    }
    private void doJockSetup(Class classObject){
        for (Field field : classObject.getDeclaredFields()) {
            if (field.getType() == getClass()) {
                if (Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                }
                else {
                    throw new BadJockDeclarationException();
                }
            }
        }
    }
}
