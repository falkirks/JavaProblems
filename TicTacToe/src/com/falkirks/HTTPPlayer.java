package com.falkirks;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPPlayer extends BasePlayer{
    public HTTPPlayer(Board board) {
        super(board);
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/test", new ConnectionHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }
        catch (IOException e) {
            sendMessage("Your IO is screwed.");
        }
    }
    static class ConnectionHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            System.out.print(t.getRequestURI());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    void doMove() {

    }

    void showState(int state) {

    }
}
