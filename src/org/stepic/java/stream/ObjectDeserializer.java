package org.stepic.java.stream;

import java.io.*;
import java.util.Arrays;

public class ObjectDeserializer {
    public static void main(String[] args) {

        byte[] byteBuffer = null;
        try (ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
             ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream)) {

            objOutStream.writeInt(3);
            objOutStream.writeObject(new Animal("Cat"));
            objOutStream.writeObject(new Animal("Cat2"));
            objOutStream.writeObject(new Animal("Dog"));
//            objOutStream.writeObject(new Object());

            byteOutStream.flush();
            byteBuffer = byteOutStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(Arrays.toString(byteBuffer));
        Animal[] animals = deserializeAnimalArray(byteBuffer);
        System.out.println(Arrays.toString(animals));
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] result = new Animal[0];
        int amObject, idx;

        try (ByteArrayInputStream byteInStream = new ByteArrayInputStream(data);
             ObjectInputStream objInStream = new ObjectInputStream(byteInStream)) {

            amObject = objInStream.readInt();
            if (amObject > 0) {
                result = new Animal[amObject];
                for (idx = 0; idx < amObject; idx++) {
                    result[idx] = (Animal) objInStream.readObject();
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return result;
    }

}
