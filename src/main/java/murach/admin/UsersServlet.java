// src/main/java/murach/admin/UsersServlet.java
package murach.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import murach.business.User;
import murach.data.UserDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", urlPatterns = {"/userAdmin"})
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "display_users";

        switch (action) {
            case "display_user": {
                String email = request.getParameter("email");
                User user = UserDB.selectUser(email);
                request.setAttribute("user", user);
                getServletContext()
                        .getRequestDispatcher("/user.jsp")
                        .forward(request, response);
                break;
            }
            case "update_user": {
                // lấy dữ liệu từ form
                Long userId = request.getParameter("userId") != null
                        ? Long.valueOf(request.getParameter("userId")) : null;
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");

                User u = new User();
                u.setUserId(userId);
                u.setEmail(email);
                u.setFirstName(firstName);
                u.setLastName(lastName);

                UserDB.updateUser(u);

                // quay về danh sách
                List<User> users = UserDB.selectUsers();
                request.setAttribute("users", users);
                getServletContext()
                        .getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            }
            case "delete_user": {
                Long userId = Long.valueOf(request.getParameter("userId"));
                User u = new User();
                u.setUserId(userId);
                UserDB.deleteUser(u);

                List<User> users = UserDB.selectUsers();
                request.setAttribute("users", users);
                getServletContext()
                        .getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            }
            case "display_users":
            default: {
                List<User> users = UserDB.selectUsers();
                request.setAttribute("users", users);
                getServletContext()
                        .getRequestDispatcher("/index.jsp")
                        .forward(request, response);
            }
        }
    }
}
