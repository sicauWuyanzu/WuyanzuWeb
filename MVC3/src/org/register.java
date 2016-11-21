package org;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name="register", urlPatterns={"/register"})
public class register extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errMsg = "";
        RequestDispatcher rd;
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String conPass = request.getParameter("conPass");

        if((username.length()==0)|| (pass.length()==0)||!pass.equals(conPass))
            errMsg += "注册失败，请检查用户名和密码非空，并确定密码一致";

        else {
            try {
                model dd = new model("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mvc",
                        "root", "a99589958");
                ResultSet rs = dd.query("select password from mvc where username = ?", username);
                if(rs.next()) {
                    errMsg += "用户已经存在，请重新注册用户名";
                }
                else {
                    boolean addUser = dd.insert("insert into mvc(username, password) values(?, ?)",username, pass);
                    if(!addUser) {
                        errMsg += "注册用户出现错误";

                    }


                    HttpSession session = request.getSession(true);
                    session.setAttribute("name", username);
                    rd = request.getRequestDispatcher("/login.jsp");

                    rd.forward(request, response);
                }

            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        if(errMsg != null && !errMsg.equals("")){
            rd = request.getRequestDispatcher("/register.jsp");
            request.setAttribute("err", errMsg);
            rd.forward(request, response);
        }
    }
}