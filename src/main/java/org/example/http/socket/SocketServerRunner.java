package org.example.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {

            var request = inputStream.readUTF();

            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                var response = scanner.nextLine();
                outputStream.writeUTF("Hello from server! " + response);
                request = inputStream.readUTF();
            }
        }
    }

    private static void singleRequest() throws IOException {
        try (var serverSocket = new ServerSocket(7777);
            // serverSocket.accept() возвращает Socket, т.е. тот клиент, который подключился к нашему серверу. Следовательно ожидается,
             // пока самый первый клиент не подключится к нашему серверу. Следовательно можно предположить, что наш serverSocket не может
             // работать в один момент времени более чем с одним соединением, потому что до тех пор, пока снова не вызовется accept() он
             // не может принимать connections. Метод блокирующий и мы будем ожидать в этой строке (16), пока кто-то не подключится.
             var socket = serverSocket.accept();
             // тут мы запишем респонс для клиента
             var outputStream = new DataOutputStream(socket.getOutputStream());
             // что пришло от клиента
             var inputStream = new DataInputStream(socket.getInputStream())) {
            System.out.println("Client request: " + inputStream.readUTF());

            outputStream.writeUTF("Hello from server!");
        }
    }
}
