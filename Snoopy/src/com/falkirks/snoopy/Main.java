package com.falkirks.snoopy;


import com.falkirks.snoopy.gui.SnoopyGUI;
import com.falkirks.snoopy.service.Service;
import org.reflections.Reflections;

import java.util.Set;


public class Main {

    public static void main(String[] args){
        new SnoopyGUI();
        Reflections reflections = new Reflections("com.falkirks.snoopy.service");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);
        for(Class<? extends Service> serviceClass : classes){
            System.out.println(serviceClass.getName());
        }
        /*
        Website website = new Website();
        for(SubnetPeer peer : Subnet.findHosts("192.168.1")){
            System.out.println(website.runTest(peer));
            System.out.println(peer);
        }
        */

    }
}
