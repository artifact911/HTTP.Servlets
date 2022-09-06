package org.example.socket;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

public class SocketRunner {

    @SneakyThrows
    public static void main(String[] args) {
        // http - 80
        // https - 443
        // мы тут юзаем порт tcp

        var inetAddress = Inet4Address.getByName("google.com");

        try (var socket = new Socket(inetAddress, 80);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream())) {

            outputStream.writeUTF("Hello World!");
            var response = inputStream.readAllBytes();
            System.out.println(response.length);
        }
    }
}
