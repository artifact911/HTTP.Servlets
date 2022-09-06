package org.example.http.client;

import java.io.IOException;
import java.net.URL;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        // но URL.class мы можем еще использовать для чтения файла на компе!
        var url = new URL("file:C:\\Users\\anduser\\IdeaProjects\\HTTP.Servlets\\src\\main\\java\\org\\example\\http\\socket\\DatagramRunner.java");
        var urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }


    private static void chekGoogle() throws IOException {
        var url = new URL("https://www.google.com");

        // GET запрос
        var urlConnection = url.openConnection();
        // вернет список Headers
        var headerFields = urlConnection.getHeaderFields();
        // вернет body
//        urlConnection.getInputStream();
        var content = new String(urlConnection.getInputStream().readAllBytes());
//        urlConnection.getContent();

        // POST запрос
        // выставляем это в true
        urlConnection.setDoOutput(true);
        // открываем OutputStream
        try (var outputStream = urlConnection.getOutputStream()) {
            // и тут считываем то, что хотим передать в body
//            outputStream.write();
        }
        System.out.println();
    }
}
