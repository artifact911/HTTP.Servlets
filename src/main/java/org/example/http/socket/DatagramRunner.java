package org.example.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramRunner {

    // тут мы будем взаимодействовать по протоколу UDP
    public static void main(String[] args) throws IOException {
        var inetAddress = InetAddress.getByName("localhost");
        try (var datagramSocket = new DatagramSocket()) {
            var bytes = "Hello from UDP client".getBytes();
            // мы продесонстироуем что порты с одинаковым номером 7777 но на разных протокола TCP и UDP нормально вместе живут
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(packet);
        }
    }
}
