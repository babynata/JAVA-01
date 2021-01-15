import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;


public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args){
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        try {
//            Class<?> clazz = Class.forName("Hello", true, helloClassLoader);
            Class<?> clazz = helloClassLoader.loadClass("Hello");
            Method method = clazz.getDeclaredMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(".", "Hello.xlass"));
            byte[] deBytes = decode(bytes);
            Class<?> clazz = this.defineClass(name, deBytes, 0, deBytes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] decode(byte[] bytes) {
        byte[] deBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            deBytes[i] = (byte) (255 - bytes[i]);
        }
        return deBytes;
    }
}
