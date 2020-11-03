import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class GetByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/productdb?serverTimezone=Europe/Moscow&useSSL=false",
                    "root",
                    "08071986")) {

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM products WHERE id = 1");

                while (result.next()) {
                    pw.println(result.getInt(1) + " "
                            + result.getString(2) + " "
                            + result.getInt(3));
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
        }
    }
}

