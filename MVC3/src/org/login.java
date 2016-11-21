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

@WebServlet(name="login", urlPatterns={"/login"})
public class login extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errMsg = "";
        RequestDispatcher rd;
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        try {
            model dd = new model("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/ukelili",
                    "root", "a99589958");

            // 查询结果集
            ResultSet rs = dd.query("select password from u_user where username = ?", username);
            if(rs.next()){
                //用户名和密码匹配
                if(rs.getString("password").equals(pass)) {
                    //获取session对象
                    HttpSession session = request.getSession(true);
                    session.setAttribute("name", username);

                    //获取转发对象
                    rd = request.getRequestDispatcher("/welcome.jsp");

                    // 转发请求
                    rd.forward(request, response);
                } else {
                    errMsg += "您的用户名密码不匹配，请重新输入";
                }
            } else {
                errMsg += "您的用户名不存在，请先注册";
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        // 如果出错，转发到重新登陆
        if(errMsg != null && !errMsg.equals("")){
            rd = request.getRequestDispatcher("/login.jsp");
            request.setAttribute("err", errMsg);
            rd.forward(request, response);
        }
    }
}