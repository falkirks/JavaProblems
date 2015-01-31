package com.falkirks.snoopy.gui;

import com.falkirks.snoopy.lan.SubnetPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SnoopyGUI {
    private JFrame frame;
    private ArrayList<SubnetPeer> subnetPeers;
    private JPanel topPanel;
    public SnoopyGUI() {
        subnetPeers = new ArrayList<SubnetPeer>();
        frame = new JFrame();
        topPanel = new JPanel();
        createAndShowGUI();
    }
    public void updatePeers(){
        SubnetPeer[] peerArray = subnetPeers.toArray(new SubnetPeer[subnetPeers.size()]);

        topPanel.removeAll();
        JList<SubnetPeer> listbox = new JList<SubnetPeer>( peerArray );
        topPanel.add( listbox, BorderLayout.CENTER );
        frame.revalidate();
        frame.repaint();
    }
    public void addPeer(SubnetPeer peer){
        subnetPeers.add(peer);
        updatePeers();
    }
    public void addPeers(ArrayList<SubnetPeer> subnetPeers){
        for(SubnetPeer subnetPeer : subnetPeers){
            if(!this.subnetPeers.contains(subnetPeer)){
                this.subnetPeers.add(subnetPeer);
            }
        }
        updatePeers();
    }
    private void createAndShowGUI() {

        frame.setTitle("Snoopy");
        frame.setSize(400, 300);
        frame.setBackground(Color.gray);

        topPanel.setLayout( new BorderLayout() );
        frame.getContentPane().add( topPanel );
        topPanel.add(new JLabel("Loading subnet...", null, JLabel.CENTER));
        frame.setVisible(true);
    }
}
