package com.falkirks.jock;


public class Example {
    final Jock<String> stringJock;

    public Example(String string) {
        this.stringJock = new Jock<String>(string);
        System.out.println(this.stringJock);
    }

    public void setStringJock(Jock<String> stringJock) {
        //this.stringJock = stringJock;
    }
}
