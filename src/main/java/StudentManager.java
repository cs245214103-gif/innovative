import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

class Student {
    int roll;
    String name;
    int marks;

    Student(int r, String n, int m) {
        roll = r;
        name = n;
        marks = m;
    }
}

public class StudentManager {

    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);

        // Home page
        server.createContext("/", exchange -> {

            String html = """
            <html>
            <body>
            <h2>Student Record Manager</h2>

            <h3>Add Student</h3>
            <form action="/add">
            Roll No: <input name="roll"><br><br>
            Name: <input name="name"><br><br>
            Marks: <input name="marks"><br><br>
            <input type="submit" value="Add Student">
            </form>

            <br>
            <a href="/view">View Students</a>

            <br><br>

            <h3>Delete Student</h3>
            <form action="/delete">
            Roll No: <input name="roll"><br><br>
            <input type="submit" value="Delete Student">
            </form>

            </body>
            </html>
            """;

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, html.length());

            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        });

        // Add Student
        server.createContext("/add", exchange -> {

            String query = exchange.getRequestURI().getQuery();
            Map<String,String> params = parseQuery(query);

            int roll = Integer.parseInt(params.get("roll"));
            String name = params.get("name");
            int marks = Integer.parseInt(params.get("marks"));

            students.add(new Student(roll,name,marks));

            String response = "<html><body><h3>Student Added</h3><a href='/'>Go Back</a></body></html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        // View Students
        server.createContext("/view", exchange -> {

            StringBuilder html = new StringBuilder();
            html.append("<html><body><h2>Student List</h2>");

            for(Student s : students){
                html.append("Roll: ").append(s.roll)
                    .append(" | Name: ").append(s.name)
                    .append(" | Marks: ").append(s.marks)
                    .append("<br>");
            }

            html.append("<br><a href='/'>Go Back</a></body></html>");

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, html.length());

            OutputStream os = exchange.getResponseBody();
            os.write(html.toString().getBytes());
            os.close();
        });

        // Delete Student
        server.createContext("/delete", exchange -> {

            String query = exchange.getRequestURI().getQuery();
            Map<String,String> params = parseQuery(query);

            int roll = Integer.parseInt(params.get("roll"));

            students.removeIf(s -> s.roll == roll);

            String response = "<html><body><h3>Student Deleted</h3><a href='/'>Go Back</a></body></html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();

        System.out.println("Server started at http://localhost:9090");
    }

    // Helper function
    static Map<String,String> parseQuery(String query){

        Map<String,String> map = new HashMap<>();

        if(query == null) return map;

        for(String param : query.split("&")){
            String[] pair = param.split("=");
            map.put(pair[0], pair[1]);
        }

        return map;
    }
}
