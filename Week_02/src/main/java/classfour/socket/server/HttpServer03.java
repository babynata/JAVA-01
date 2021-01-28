package classfour.socket.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        try {
            ServerSocket serverSocket = new ServerSocket(8803);
            while (true) {
                final Socket socket = serverSocket.accept();
                executorService.execute(()->service(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        System.out.println(LocalDateTime.now() + " connected...");
        String body = "hello,nio";
        PrintWriter printWriter = null;
        try {
            Thread.sleep(5);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type:text/html;charset=utf-8");
            //加上消息长度，才能解析body部分
            printWriter.println("Content-Length:"+body.getBytes().length);
            //head和body用空行隔开
            printWriter.println();
            printWriter.println(body);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null) {
                printWriter.close();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
