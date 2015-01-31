package com.falkirks.snoopy.lan;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Subnet {
    private ArrayList<SubnetPeer> subnetPeers;

    public static ArrayList<SubnetPeer> findHosts(String subnet){
        HashMap<String, Future<Boolean>> futureList = new HashMap<String, Future<Boolean>>();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 1; i < 254; i++){
            String host = subnet + "." + i;
            futureList.put(host, executor.submit(new PeerPingTask(host)));
        }
        executor.shutdown();
        while (!executor.isTerminated());

        ArrayList<SubnetPeer> peers = new ArrayList<SubnetPeer>();
        for (int i = 1; i < 254; i++){
            try {
                if (futureList.get(subnet + "." + i).get()) {
                    peers.add(new SubnetPeer(subnet + "." + i));
                }
            }
            catch (ExecutionException e){
                e.printStackTrace();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return peers;
    }

}
