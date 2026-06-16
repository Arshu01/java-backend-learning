package org.tap.jdbc.batchprocessing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Statement_Batch_Processing {
	private static final String URL="jdbc:mysql://localhost:3306/tapjdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "Arshu@321";

    public static void main(String[] args) {
    	Connection con =null;
    	Statement stmt =null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL,USER,PASSWORD);

            stmt = con.createStatement();

            // INSERT
            stmt.addBatch(
                "INSERT INTO employee VALUES(45,'verra','verra@gmail.com','IT',30000)");

            // UPDATE
            stmt.addBatch(
                "UPDATE employee SET salary = salary + 500 WHERE id = 1");

            // DELETE
            stmt.addBatch(
                "DELETE FROM employee WHERE id = 50");

            int[] result = stmt.executeBatch();

            for(int i = 0; i < result.length; i++) {
                System.out.println(
                    "Batch " + (i + 1) +
                    " affected " + result[i] + " row(s)");
            }

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
			if(stmt!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
}