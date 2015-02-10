package com.falkirks.snoopy;


import com.falkirks.snoopy.gui.SnoopyGUI;
import com.falkirks.snoopy.lan.Subnet;
import com.falkirks.snoopy.service.Service;
import org.apache.commons.net.util.SubnetUtils;
import org.reflections.Reflections;

import java.net.*;
import java.util.ArrayList;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws UnknownHostException, SocketException{

        SnoopyGUI snoopyGUI = new SnoopyGUI();
        Subnet subnet = new Subnet();

        snoopyGUI.addPeers(subnet.getSubnetPeers());

        Reflections reflections = new Reflections("com.falkirks.snoopy.service");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);
        for(Class<? extends Service> serviceClass : classes){
            subnet.testAll(serviceClass);
            snoopyGUI.updatePeers();
        }
    }
    public static String subnetStringFromMask(int subnetMask){
        String out = "";
        for(int i = 0; i < 4; i++){
            if(subnetMask >= 8){
                out += ".255";
            }
            else{
                out += ".0";
            }
            subnetMask = subnetMask-8;
        }
        return out.substring(1);
    }
}
