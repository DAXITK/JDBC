import java.sql.*;

public class studentDB{
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onedb", "root", "3688");

            System.out.println("------------Creating table------------");
            stmt = conn.createStatement();

            // Create data table
            String createTableSql = "CREATE TABLE student " +
                    "(RollNo INTEGER not NULL, " +
                    " Name VARCHAR(255), " +
                    " Address VARCHAR(255), " +
                    " PRIMARY KEY ( RollNo ))";

            stmt.executeUpdate(createTableSql);


            // Insert records into Student table
            System.out.println("------------Insert Data into table------------");
            String insertSql = "INSERT INTO Student (RollNo, Name, Address) VALUES " +
                    "(7085, 'Dakshit', 'Rajkot'), " +
                    "(7062, 'Jay', 'Rajkot')";

            stmt.executeUpdate(insertSql);

            // Display the content of records
            System.out.println("------------Display the table------------");
            String selectSql = "SELECT * FROM Student";
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                int rollNo = resultSet.getInt("RollNo");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");

                System.out.println("RollNo: " + rollNo);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                System.out.println("------------");
            }
                System.out.println("------------Inserting this value into the table------------");
            // Insert two more records into Student table
                String insertSql2 = "INSERT INTO Student (RollNo, Name, Address) VALUES " +
                    "(7084, 'Vishnu', 'Rajkot'), " +
                    "(7077, 'Shubham', 'Rajkot')";
                stmt.executeUpdate(insertSql2);


            // Update a record in Student table
                String updateSql = "UPDATE Student SET Address = 'Morbi' WHERE RollNo = 7085";
                stmt.executeUpdate(updateSql);

                System.out.println("------------Deleting this data from the table------------");
            // Delete a record from Student table
                String deleteSql = "DELETE FROM Student WHERE RollNo = 7062" +
                        "DELETE FROM Student WHERE RollNo = 7077";
                stmt.executeUpdate(deleteSql);

            // Display the content of records again
            ResultSet resultSet2 = stmt.executeQuery(selectSql);

            while (resultSet2.next()) {
                int rollNo = resultSet2.getInt("RollNo");
                String name = resultSet2.getString("Name");
                String address = resultSet2.getString("Address");

                System.out.println("RollNo: " + rollNo);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                System.out.println("------------");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
