package com.falkirks.jock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Entry {
    private String entry;
    private boolean isRegex;
    private boolean allowChildren;

    private ArrayList<String> accessNodes;

    public Entry(Object entry, boolean allowChildren, ArrayList<String> accessNodes) {
        this.accessNodes = accessNodes;
        this.allowChildren = allowChildren;
        isRegex = false;
        if(entry instanceof String) {
            this.entry = (String) entry;
            try{
                Pattern.compile(this.entry);
                isRegex = true;
            }
            catch (PatternSyntaxException e){}
        }
        else if(entry instanceof Class){
            this.entry = ((Class) entry).getName();
        }
        else{
            this.entry = entry.getClass().getName();
        }
    }
    public Entry(Object entry, boolean allowChildren, String[] accessNodes){
        this(entry, allowChildren, new ArrayList<String>(Arrays.asList(accessNodes)));
    }
    public Entry(Object entry, boolean allowChildren){
        this(entry, allowChildren, new ArrayList<String>());

        accessNodes.add("set");
        accessNodes.add("get");
        accessNodes.add("allow");
        accessNodes.add("deny");
    }
    public Entry(Object entry){
        this(entry, false);
    }
    boolean matches(Class classObject){
        if(allowChildren && matches(classObject.getSuperclass())){
            return true;
        }
        if(isRegex){
            return classObject.getName().matches(entry);
        }
        else{
            return classObject.getName().equals(entry);
        }
    }

    public String getEntry() {
        return entry;
    }

    public boolean isRegex() {
        return isRegex;
    }

    public boolean isAllowChildren() {
        return allowChildren;
    }

    boolean matches(String string){
        if(isRegex){
            return string.matches(entry);
        }
        else{
            return string.equals(entry);
        }
    }
    public void grantNode(String accessNode){
        if(accessNodes.indexOf(accessNode) == -1) accessNodes.add(accessNode);
    }
    public void takeNode(String accessNode){
        accessNodes.remove(accessNode);
    }
    public boolean hasNode(String accessNode){
        return accessNodes.indexOf(accessNode) != -1;
    }
}
