package com.falkirks.snoopy;


import com.falkirks.snoopy.gui.SnoopyGUI;
import com.falkirks.snoopy.lan.Subnet;
import com.falkirks.snoopy.service.Service;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;


public class Main {

    public static void main(String[] args){
        SnoopyGUI snoopyGUI = new SnoopyGUI();
        Subnet subnet = new Subnet("192.168.1");

        Reflections reflections = new Reflections("com.falkirks.snoopy.service");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);
        for(Class<? extends Service> serviceClass : classes){
            subnet.testAll(serviceClass);
        }
        snoopyGUI.addPeers(subnet.getSubnetPeers());
    }
}
