package classfour.socket.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class HttpServer01{

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8801);
            while (true) {
                Socket socket = serverSocket.accept();
                service(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        System.out.println(LocalDateTime.now() + " connected...");
        String body = "hello,nio";
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type:text/html;charset=utf-8");
            //加上消息长度，才能解析body部分
            printWriter.println("Content-Length:"+body.getBytes().length);
            //head和body用空行隔开
            printWriter.println();
            printWriter.println(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
