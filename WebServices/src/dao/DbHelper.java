package dao;//DriverManager ve Connection Pool
// DriverManager her gonderilen requesti DB gonderir
//ConnectionPool biz ozumuz deiyirik saniyede nece pool ac


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Locale;

public class DbHelper {
    public static Connection getConnection() throws Exception{
        Locale.setDefault(Locale.ENGLISH);
        Context context = new InitialContext(); //xml fayldan oxumag ucun .
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/test");
        Connection c = dataSource.getConnection();
        return c;
    }

}
