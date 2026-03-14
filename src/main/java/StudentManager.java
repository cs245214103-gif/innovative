import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

public class StudentManager {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);

        server.createContext("/", exchange -> {

            String html = """
            <html>
            <body>
            <h2>Student Record Manager</h2>

            <form action="/add" method="get">
            Roll No: <input name="roll"><br><br>
            Name: <input name="name"><br><br>
            Marks: <input name="marks"><br><br>
            <input type="submit" value="Add Student">
            </form>

            </body>
            </html>
            """;

            exchange.sendResponseHeaders(200, html.length());
            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server started at http://localhost:9090");
    }
}
