package poisePMS;

import java.sql.*;

/**
 * The {@code CustomerManager} class provides methods to manage customers within
 * the Poise Project Management System (PMS). It includes functionalities to
 * add, update, delete, and display customers.
 * 
 * <p>
 * This class is responsible for handling all customer-related operations and
 * interacting with the underlying database to perform CRUD operations.
 * </p>
 * 
 * @author Ijan van der Westhuizen
 * @version 1.0
 * @since 2024-08-12
 */
public class CustomerManager {

  // String containing SQl query to retrieve customers name based on their ID
  private static final String GET_CUSTOMER_NAME_BY_ID_SQL = "SELECT Surname FROM customers WHERE CustomerID = ?";

  /**
   * Retrieves the name of a customer based on their ID.
   *
   * @param customerID The ID of the customer whose Surname is to be retrieved.
   * @return The name of the customer.
   */
  // Method for Retrieving the Surname of the customer by ID
  public String getCustomerNameById(int customerID) {
    // Gets the connection to the database
    try (Connection connection = PoisePMS.getConnection();
        // Prepares the SQL query
        PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_NAME_BY_ID_SQL)) {
      // Binds the CustomerID to the first placeholder
      statement.setInt(1, customerID);

      // Executes the query and returns results into resultSet
      try (ResultSet resultSet = statement.executeQuery()) {
        // if the resultSet contains a row gets and returns the customer name
        if (resultSet.next()) {
          return resultSet.getString("Surname");
          // else it prints this and returns Unknown customer
        } else {
          System.out.println("Customer ID not found.");
          return "Unknown Customer";
        }
      }
      // if an exception occurs prints an error message and returns unknown customer
    } catch (SQLException e) {
      System.out.println("Error retrieving customer name: " + e.getMessage());
      return "Unknown Customer";
    }
  }

  /**
   * Adds a new customer to the database.
   * 
   * @param Firstname       The Firstname of the customer.
   * @param Surname         The Surname of the customer.
   * @param telephone       The telephone number of the customer.
   * @param email           The email address of the customer.
   * @param physicalAddress The physical address of the customer.
   */

  // method to add customers based on the parameters
  public void addCustomer(String Firstname, String Surname, String telephone, String email, String physicalAddress) {
    // Defines the SQL query for adding customers with "?"marks as placeholders for
    // the values
    String sql = "INSERT INTO customers (Firstname, Surname, Telephone, Email, PhysicalAddress) VALUES (?, ?, ?, ?, ?)";
    // Establishes a connection to the database and prepares the SQL statement for
    // execution
    try (Connection conn = PoisePMS.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // Sets the values for the placeholders
      pstmt.setString(1, Firstname);
      pstmt.setString(2, Surname);
      pstmt.setString(3, telephone);
      pstmt.setString(4, email);
      pstmt.setString(5, physicalAddress);
      // executes the query
      pstmt.executeUpdate();
      // catches any exceptions and prints the StackTrace
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates the details of an existing customer in the system.
   * 
   * <p>
   * This method takes the customer's ID and the new details as parameters and
   * updates the customer's information in the database.
   * </p>
   * 
   * @param id              The ID of the customer to be updated.
   * @param Firstname       The new Firstname of the customer.
   * @param Surname         The new Surname of the customer.
   * @param telephone       The new telephone number of the customer.
   * @param email           The new email address of the customer.
   * @param physicalAddress The new physical address of the customer.
   */
  // Method to Update a customer
  public void updateCustomer(int id, String Firstname, String Surname, String telephone, String email,
      String physicalAddress) {
    // Builds the dynamic SQL query
    StringBuilder sql = new StringBuilder("UPDATE customers SET ");
    boolean firstField = true;

    // Dynamically append fields based on non-null, non-empty values
    if (Firstname != null && !Firstname.trim().isEmpty()) {
      sql.append("Firstname = ?");
      firstField = false;
    }
    if (Surname != null && !Surname.trim().isEmpty()) {
      sql.append("Surname = ?");
      if (!firstField)
        sql.append(", ");
      firstField = false;
    }
    if (telephone != null && !telephone.trim().isEmpty()) {
      if (!firstField)
        sql.append(", ");
      sql.append("Telephone = ?");
      firstField = false;
    }
    if (email != null && !email.trim().isEmpty()) {
      if (!firstField)
        sql.append(", ");
      sql.append("Email = ?");
      firstField = false;
    }
    if (physicalAddress != null && !physicalAddress.trim().isEmpty()) {
      if (!firstField)
        sql.append(", ");
      sql.append("PhysicalAddress = ?");
    }

    // Only update if there's at least one field to update
    if (!firstField) {
      sql.append(" WHERE CustomerID = ?");

      // Creates a connection to the database
      try (Connection conn = PoisePMS.getConnection();
          // prepares the SQL statement
          PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
        // Keeps track of placeholder positions
        int index = 1;

        // Sets the parameters for the non-null, non-empty fields
        if (Firstname != null && !Firstname.trim().isEmpty())
          pstmt.setString(index++, Firstname);
        if (Surname != null && !Surname.trim().isEmpty())
          pstmt.setString(index++, Surname);
        if (telephone != null && !telephone.trim().isEmpty())
          pstmt.setString(index++, telephone);
        if (email != null && !email.trim().isEmpty())
          pstmt.setString(index++, email);
        if (physicalAddress != null && !physicalAddress.trim().isEmpty())
          pstmt.setString(index++, physicalAddress);

        // Sets the CustomerID in the WHERE clause
        pstmt.setInt(index, id);

        // Execute the update and returns number of rows affected
        int rowsUpdated = pstmt.executeUpdate();
        // if any
        if (rowsUpdated > 0) {
          // prints this message
          System.out.println("Customer updated successfully.");
          // else prints this one
        } else {
          System.out.println("No customer found with the given ID.");
        }
        // catches exceptions and prints error message
      } catch (SQLException e) {
        System.out.println("Error updating customer: " + e.getMessage());
      }
    } else {
      System.out.println("No fields to update.");
    }
  }

  /**
   * Deletes a customer from the system.
   * 
   * <p>
   * This method takes the customer's ID as a parameter and removes the customer
   * from the database.
   * </p>
   * 
   * @param customerId the ID of the customer to be deleted.
   */
  // Method used to Delete customers
  public void deleteCustomer(int customerId) {
    Connection conn = null;// defines a connection
    PreparedStatement pstmt = null;// defines Preparedstatement

    try {
      conn = PoisePMS.getConnection();
      int defaultCustomerId = -1; // defines Default customer ID

      // Similar to deleting a contractor it gets updated to default in project table
      // first
      // ,reason for this is to avoid errors related to foreign key constraints
      String updateProjectsSql = "UPDATE projects SET CustomerID = ? WHERE CustomerID = ?";
      pstmt = conn.prepareStatement(updateProjectsSql);
      pstmt.setInt(1, defaultCustomerId);
      pstmt.setInt(2, customerId);
      pstmt.executeUpdate();

      // Deletes the customer from the customers table
      String deleteCustomerSql = "DELETE FROM customers WHERE CustomerID = ?";
      pstmt = conn.prepareStatement(deleteCustomerSql);
      pstmt.setInt(1, customerId);
      pstmt.executeUpdate();
      // Prints message that confirms the deletion of the customer
      System.out.println("Customer has been deleted. Projects reassigned to default customer.");
      // Catches exceptions and prints the StackTrace
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Displays all customers currently in the system.
   * 
   * <p>
   * This method retrieves and displays a list of all customers stored in the
   * database.
   * </p>
   */
  // Method for displaying all customers
  public void displayAllCustomers() {
    // Defines the SQL query
    String sql = "SELECT * FROM customers";
    try (Connection conn = PoisePMS.getConnection(); // Connects to the database
        Statement stmt = conn.createStatement(); // Creates a statement
        ResultSet rs = stmt.executeQuery(sql)) {// and executes the SQL query defined above
      // Loops through the ResultSet
      while (rs.next()) {
        // And displays customer details
        System.out.println("Customer ID: " + rs.getInt("CustomerID"));
        System.out.println("Firstname: " + rs.getString("Firstname"));
        System.out.println("Surname: " + rs.getString("Surname"));
        System.out.println("Telephone: " + rs.getString("Telephone"));
        System.out.println("Email: " + rs.getString("Email"));
        System.out.println("Physical Address: " + rs.getString("PhysicalAddress"));
        System.out.println();
      }
      // catches any exceptions and prints the StackTrace
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}