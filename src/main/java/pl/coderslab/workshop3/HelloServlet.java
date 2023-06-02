package pl.coderslab.workshop3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        testDao();
    }

    public void destroy() {
    }

    public void testDao(){

        UserDao dao = new UserDao();
        dao.addUser(new User("Email", "Username","password"));

    }
}