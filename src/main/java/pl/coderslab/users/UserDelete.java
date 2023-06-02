package pl.coderslab.users;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDelete", value = "/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID to delete
        int userId = Integer.parseInt(request.getParameter("id"));

        // Fetch the user information
        UserDao userDao = new UserDao();
        User user = userDao.read(userId);

        if (user != null) {
            // Set the user object as an attribute in the request
            request.setAttribute("user", user);

            // Forward the request to the delete confirmation page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/confirm-delete.jsp");
            dispatcher.forward(request, response);
        } else {
            // Handle the case when the user is not found
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        userDao.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/user/list");
    }
}
 