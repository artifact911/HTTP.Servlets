package org.example.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            // accept() принимает соединение от нашего клиента и представлен в виде класса Socket
            var socket = server.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void processSocket(Socket socket) {
        try (socket;
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream())) {

            // step 1 обработка реквеста
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));

            // step 2 отправка респонса
            var body = Files.readAllBytes(Path.of("src/main/resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes(StandardCharsets.UTF_8);
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            // TODO 7/09/22 log error message
            e.printStackTrace();
        }
    }
}
