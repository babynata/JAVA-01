package classfour.socket.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class HttpServer02 {

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8802);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> service(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        System.out.println(LocalDateTime.now() + " connected...");
        String body = "hello,nio";
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false);
            printWriter.write("HTTP/1.1 200 OK\r\n");
            printWriter.write("Content-type:text/html;charset=utf-8\r\n");
            //加上消息长度，才能解析body部分
            printWriter.write("Content-Length:" + body.getBytes().length + "\r\n");
            //head和body用空行隔开
            printWriter.write("\r\n");
            printWriter.write(body);
            printWriter.flush();
//            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
