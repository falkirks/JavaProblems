package com.falkirks.jock;

import com.falkirks.jock.exception.JockObjectProtectedException;

public class Main {

    public static void main(String[] args) {

        Jock<Integer> jock = new Jock<Integer>(1);
        try {
            Entry entry = new Entry("(.*)");
            entry.grantNode("read");
            System.out.println(entry.hasNode("read"));
            jock.allow(entry);
            System.out.println(jock.can("get", String.class));
        }
        catch (JockObjectProtectedException e){
            e.printStackTrace();
        }
    }
}
