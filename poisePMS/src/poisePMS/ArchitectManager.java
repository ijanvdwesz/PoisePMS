package poisePMS;

import java.sql.*;

/**
 * The {@code ArchitectManager} class provides methods to manage architects
 * within the Poise Project Management System (PMS). It includes functionalities
 * to add, update, delete, and display architects.
 * 
 * <p>
 * This class is responsible for handling all architect-related operations and
 * interacting with the underlying database to perform CRUD operations.
 * </p>
 * 
 * @author Ijan van der Westhuizen
 * @version 1.0
 * @since 2024-08-12
 */
public class ArchitectManager {

  /**
   * Adds a new architect to the system.
   * 
   * <p>
   * This method takes the details of the architect as parameters and adds the
   * architect to the database.
   * </p>
   * 
   * @param Firstname       The Firstname of the architect to be added.
   * @param Surname         The Surname of the architect to be added.
   * @param telephone       The telephone number of the architect.
   * @param email           The email address of the architect.
   * @param physicalAddress The physical address of the architect.
   * @return true if the architect was added successfully, false otherwise.
   */
  public boolean addArchitect(String Firstname, String Surname, String telephone, String email,
      String physicalAddress) {
    // Stores the SQL query in a String the "?"marks are placeholders for the values
    // to be provided by PreparedStatement
    String sql = "INSERT INTO architects (Firstname, Surname, Telephone, Email, PhysicalAddress) VALUES (?, ?, ?, ?, ?)";
    /*
     * Try with resources block that automatically closes database connection
     * PrepaparedStatement.PoisePMS.getConnection() gets a connection to the
     * database conn.prepareStatment(sql) prepares the SQL for execution
     */
    try (Connection conn = PoisePMS.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // Sets the values for the SQL query "?" placeholders
      pstmt.setString(1, Firstname);
      pstmt.setString(2, Surname);
      pstmt.setString(3, telephone);
      pstmt.setString(4, email);
      pstmt.setString(5, physicalAddress);
      // Executes the Update
      int rowsAffected = pstmt.executeUpdate();
      return rowsAffected > 0; // Returns true if the update was successful
      // Catches any Exceptions that might occur and prints the error stack
    } catch (SQLException e) {
      System.out.println("Error updating architect: " + e.getMessage());
      return false; // Return false if the update failed
    }
  }

  /**
   * Updates the details of an existing architect in the system.
   * 
   * <p>
   * This method takes the architect's ID and the new details as parameters and
   * updates the architect's information in the database.
   * </p>
   * 
   * @param architectID        The ID of the architect to be updated.
   * @param newFirstname       The new Firstname of the architect.
   * @param newSurname         The new Surname of the architect.
   * @param newTelephone       The new telephone number of the architect.
   * @param newEmail           The new email address of the architect.
   * @param newPhysicalAddress The new physical address of the architect.
   */
  // Method to update architect details based on non-null values
  public void updateArchitect(int architectID, String newFirstname, String newSurname, String newTelephone,
      String newEmail, String newPhysicalAddress) {
    // dynamically builds the SQL query
    StringBuilder query = new StringBuilder("UPDATE architects SET ");
    boolean firstField = true;

    // Checks if newName is not empty if so,appends Name=? and sets first field to
    // false
    if (newFirstname != null && !newFirstname.trim().isEmpty()) {
      query.append("Firstname = ?");
      firstField = false;
    }
    // Checks if newName is not empty if so,appends Name=? and sets first field to
    // false
    if (newSurname != null && !newSurname.trim().isEmpty()) {
      if (!firstField)
        query.append(", ");
      query.append("Surname= ?");
      firstField = false;
    }
    // Does the same as above if, but for newTelephone
    if (newTelephone != null && !newTelephone.trim().isEmpty()) {
      if (!firstField)
        query.append(", ");
      query.append("Telephone = ?");
      firstField = false;
    }
    // Similar logic but for updating emails
    if (newEmail != null && !newEmail.trim().isEmpty()) {
      if (!firstField)
        query.append(", ");
      query.append("Email = ?");
      firstField = false;
    }
    // Also similar but for updating addresses
    if (newPhysicalAddress != null && !newPhysicalAddress.trim().isEmpty()) {
      if (!firstField)
        query.append(", ");
      query.append("PhysicalAddress = ?");
    }

    // Adds the WHERE clause to target the correct architect
    query.append(" WHERE ArchitectID = ?");

    // Checks if we actually have fields to update
    if (firstField) {
      // if not prints this message
      System.out.println("No fields provided to update.");
      return;
    }

    // Opens a connection to the database and dynamically builds the SQL query
    try (Connection connection = PoisePMS.getConnection();
        PreparedStatement statement = connection.prepareStatement(query.toString())) {
      // Keeps track of placeholder positions
      int index = 1;

      // Sets the parameters based on non-null and non-empty values
      if (newFirstname != null && !newFirstname.trim().isEmpty())
        statement.setString(index++, newFirstname);
      if (newSurname != null && !newSurname.trim().isEmpty())
        statement.setString(index++, newSurname);
      if (newTelephone != null && !newTelephone.trim().isEmpty())
        statement.setString(index++, newTelephone);
      if (newEmail != null && !newEmail.trim().isEmpty())
        statement.setString(index++, newEmail);
      if (newPhysicalAddress != null && !newPhysicalAddress.trim().isEmpty())
        statement.setString(index++, newPhysicalAddress);

      // Sets the ArchitectID in the WHERE clause
      statement.setInt(index, architectID);

      // Executes the update and gives feedback to the user
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Architect updated successfully.");
      } else {
        System.out.println("No architect found with the given ID.");
      }
    } catch (SQLException e) {
      System.out.println("Error updating architect: " + e.getMessage());
    }
  }

  /**
   * Deletes an architect from the system.
   * 
   * <p>
   * This method takes the architect's ID as a parameter and removes the architect
   * from the database.
   * </p>
   * 
   * @param architectsID The ID of the architect to be deleted.
   */
  // Method for deleting an architect
  public void deleteArchitect(int architectsID) {
    // Declares variables for the database connection and prepared statement
    Connection conn = null;
    PreparedStatement pstmt = null;
    // Tries to get a connection to database
    try {
      conn = PoisePMS.getConnection();

      /*
       * Checks if the architect exists by using Resultset to get the count of records
       * that match the given ID
       */
      String checkQuery = "SELECT COUNT(*) FROM architects WHERE ArchitectID = ?";
      pstmt = conn.prepareStatement(checkQuery);
      pstmt.setInt(1, architectsID);
      ResultSet rs = pstmt.executeQuery();
      rs.next();
      int count = rs.getInt(1);
      // Informs the user if no matching records are found
      if (count == 0) {
        System.out.println("Architect with ID " + architectsID + " does not exist.");
        return;
      }

      // Deletes the architect with corresponding ID
      String deleteQuery = "DELETE FROM architects WHERE ArchitectID = ?";
      pstmt = conn.prepareStatement(deleteQuery);
      pstmt.setInt(1, architectsID);
      pstmt.executeUpdate();

      // Updates projects to set the default architect where architect is null
      String updateProjectsQuery = "UPDATE projects SET ArchitectID = -1 WHERE ArchitectID IS NULL";
      pstmt = conn.prepareStatement(updateProjectsQuery);
      pstmt.executeUpdate();
      // prints a message to inform user the architect was successfully deleted
      System.out.println(
          "Architect with ID " + architectsID + " has been deleted. Projects reassigned to default architect.");
      // Ensures resources are closed and handles exceptions if any occur
    } catch (SQLException e) {
      e.printStackTrace();
      // ensures code is executed regardless of catching an exception
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Displays all architects currently in the system.
   * 
   * <p>
   * This method retrieves and displays a list of all architects stored in the
   * database.
   * </p>
   */
  // Method for displaying all architects
  public void displayAllArchitects() {
    // Stores the SQL query in variable for displaying architects
    String sql = "SELECT * FROM architects";
    try (Connection conn = PoisePMS.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        // Displays architects details
        System.out.println("Architect ID: " + rs.getInt("ArchitectID"));
        System.out.println("Firstname: " + rs.getString("Firstname"));
        System.out.println("Surname: " + rs.getString("Surname"));
        System.out.println("Telephone: " + rs.getString("Telephone"));
        System.out.println("Email: " + rs.getString("Email"));
        System.out.println("Physical Address: " + rs.getString("PhysicalAddress"));
        System.out.println();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}