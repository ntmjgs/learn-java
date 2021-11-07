package jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XClassLoader extends ClassLoader{
    protected Class<?> findClass(String fileName) {
        byte[] fileBytes = loadClassFromXFile(fileName);
        return defineClass(fileName, fileBytes, 0, fileBytes.length);
    }

    private byte[] loadClassFromXFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName+".xlass");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int next;
        try{
            while ((next = inputStream.read()) != -1){
                outputStream.write(next);

            }
        }catch(IOException e){
            e.printStackTrace();
        }
        byte[] processedArray = processByteArray(outputStream.toByteArray());
        return processedArray;

    }

    private byte[] processByteArray(byte[] toByteArray) {
        int len = toByteArray.length;
        byte[] processedArray = new byte[len];
        for(int i = 0; i < len; i++){
            processedArray[i] = (byte)(255 - toByteArray[i]);

        }
        return processedArray;
    }


}
