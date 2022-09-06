package org.example.socket;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {

    @SneakyThrows
    public static void main(String[] args) {
        var inetAddress = Inet4Address.getByName("localhost");
        try (var socket = new Socket(inetAddress, 7777);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                var request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Response from server: " + inputStream.readUTF());
            }
        }
    }

    private static void singleResponse() throws IOException {
        // http - 80
        // https - 443
        // мы тут юзаем порт tcp
        var inetAddress = Inet4Address.getByName("localhost");

        try (var socket = new Socket(inetAddress, 7777);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream())) {

            outputStream.writeUTF("Hello World!");
            System.out.println("Response from server: " + inputStream.readUTF());
        }
    }
}
