package org.stepic.java.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamConvertCrLf {

    static boolean isDevMode = false;

    public static void main(String[] args) {
        processDev();
//        processData();
    }

    static void processData() {
        try {
            int prevByte, nextByte;

            prevByte = System.in.read();
            if (prevByte >= 0) {
                while ((nextByte = System.in.read()) >= 0) {
                    if (prevByte != 13 || nextByte != 10) {
                        sendOutData(prevByte);
                    }
                    prevByte = nextByte;
                }
                sendOutData(prevByte);
            }
            System.out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendOutData(int data) {
        if (isDevMode) {
            System.out.println(data);
        } else {
            System.out.write(data);
        }
    }

    static void processDev() {
        isDevMode = true;
        setupInStream();
        processData();
    }

    static void setupInStream() {
        InputStream inputStream = new ByteArrayInputStream( new byte[] {65, 13, 10, 10, 13});
        System.setIn(inputStream);
    }
}
