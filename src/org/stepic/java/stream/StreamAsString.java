package org.stepic.java.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StreamAsString {
    public static void main(String[] args) {
        InputStream stream = new ByteArrayInputStream( new byte[] {48, 49, 50, 51});
        try {
            System.out.println(readAsString(stream, StandardCharsets.US_ASCII));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        StringBuilder stringBuilder = new StringBuilder();
        int inputBuff;

        while ((inputBuff = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) inputBuff);
        }
        return stringBuilder.toString();
    }
}
