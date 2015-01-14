package com.falkirks.jock;

import com.falkirks.jock.exception.JockObjectProtectedException;

import java.util.ArrayList;

public class Jock {
    private Object object;
    private ArrayList<Entry> whiteList;

    public Jock(Object object){
        this.object = object;
        this.whiteList = new ArrayList<Entry>();
        this.whiteList.add(new Entry(getExternalCaller().getClassName()));
    }
    public Object get() throws JockObjectProtectedException {
        if(cant("get")) throw new JockObjectProtectedException();

        return this.object;
    }
    public void set(Object object) throws JockObjectProtectedException{
        if(cant("set")) throw new JockObjectProtectedException();
        //TODO maybe enforce type
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
}
