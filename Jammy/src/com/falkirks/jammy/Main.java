package com.falkirks.jammy;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends JFrame{

    protected boolean running;
    ByteArrayOutputStream out;

    public Main() {
        super("Capture Sound Demo");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container content = getContentPane();

        final JButton capture = new JButton("START");

        capture.setEnabled(true);

        ActionListener captureListener =
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        capture.setEnabled(false);
                        captureAudio();
                    }
                };
        capture.addActionListener(captureListener);
        content.add(capture, BorderLayout.NORTH);
    }

    private void captureAudio() {
        try {
            final AudioFormat format = getFormat();
            final TargetDataLine line = (TargetDataLine)
                    AudioSystem.getLine(new DataLine.Info(
                            TargetDataLine.class, format));
            line.open(format);
            line.start();

            final SourceDataLine outLine = (SourceDataLine)
                    AudioSystem.getLine(new DataLine.Info(
                            SourceDataLine.class, format));
            outLine.open(format);
            outLine.start();
            Runnable runner = new Runnable() {
                int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                public void run() {
                    running = true;
                    while (running) {
                        int count =
                                line.read(buffer, 0, buffer.length);
                        if (count > 0) {
                            outLine.write(buffer, 0, count);
                        }
                    }
                    outLine.flush();
                    outLine.close();
                }
            };
            Thread captureThread = new Thread(runner);
            captureThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-2);
        }
    }

    private AudioFormat getFormat() {
        float sampleRate = 10000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate,
                sampleSizeInBits, channels, signed, bigEndian);
    }

    public static void main(String args[]) {
        JFrame frame = new Main();
        frame.pack();
        frame.show();
    }
}
