package org.stepic.java.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamCheckSum {
    public static void main(String[] args) {
        InputStream stream = new ByteArrayInputStream( new byte[] {0x33, 0x45, 0x01});
        try {
            int result = checkSumOfStream(stream);
            System.out.println("Result: "+ result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int result = 0, curByte;

        while ((curByte = inputStream.read()) >= 0) {
            result = Integer.rotateLeft(result, 1) ^ curByte;
        }
        return result;
    }
}
