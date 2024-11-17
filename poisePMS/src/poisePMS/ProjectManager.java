package poisePMS;

import java.sql.*;

/**
 * Manages operations related to projects within the system.
 */
public class ProjectManager {

  // Defines SQL queries to be used for interacting with the projects table
  private static final String ADD_PROJECT_SQL = "INSERT INTO projects (ProjectName, BuildingType, ERFNumber, TotalFee, TotalPaid, Deadline, ArchitectID, ContractorID, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String DELETE_PROJECT_SQL = "DELETE FROM projects WHERE ProjectID = ?";
  private static final String FINALIZE_PROJECT_SQL = "UPDATE projects SET CompletionDate = ? WHERE ProjectID = ?";
  private static final String DISPLAY_ALL_PROJECTS_SQL = "SELECT * FROM projects";
  private static final String FIND_INCOMPLETE_PROJECTS_SQL = "SELECT * FROM projects WHERE CompletionDate IS NULL";
  private static final String FIND_PROJECTS_PAST_DUE_DATE_SQL = "SELECT * FROM projects WHERE Deadline < NOW() AND CompletionDate IS NULL";
  private static final String FIND_PROJECT_BY_ID_OR_NAME_SQL = "SELECT * FROM projects WHERE ProjectID = ? OR ProjectName LIKE ?";

  /**
   * Adds a new project to the database.
   *
   * @param projectName  The name of the project.
   * @param buildingType The type of building for the project.
   * @param erfNumber    The ERF number of the project.
   * @param totalFee     The total fee for the project.
   * @param totalPaid    The amount paid so far for the project.
   * @param deadline     The deadline date for the project.
   * @param architectID  The ID of the architect assigned to the project.
   * @param contractorID The ID of the contractor assigned to the project.
   * @param customerID   The ID of the customer for the project.
   */
  // Method used to add projects
  public void addProject(String projectName, String buildingType, String erfNumber, double totalFee, double totalPaid,
      java.sql.Date deadline, int architectID, int contractorID, int customerID) {
    // Try-with-resources to ensure that resources are automatically closed
    try (Connection connection = PoisePMS.getConnection(); // Establishes a connection to the database
        PreparedStatement statement = connection.prepareStatement(ADD_PROJECT_SQL)) {// prepares statement for adding a
                                                                                     // project
      // Sets the values for the SQL query parameters
      statement.setString(1, projectName);
      statement.setString(2, buildingType);
      statement.setString(3, erfNumber);
      statement.setDouble(4, totalFee);
      statement.setDouble(5, totalPaid);
      statement.setDate(6, deadline);
      statement.setInt(7, architectID);
      statement.setInt(8, contractorID);
      statement.setInt(9, customerID);
      // Executes the SQL query and check if the project was added successfully
      int rowsInserted = statement.executeUpdate();
      if (rowsInserted > 0) {// if atleast one row is affected
        System.out.println("Project added successfully.");// then print this
      }
    } catch (SQLException e) {// catches SQL exceptions and prints this message
      System.out.println("Error adding project:Please make sure the ID's provided exist "
          + "by checking the relevant tables and then try again");
    }
  }

  /**
   * Updates the project with the given ID based on the provided non-null values.
   *
   * @param projectID       The ID of the project to update.
   * @param newProjectName  The new name for the project.
   * @param newBuildingType The new building type for the project.
   * @param newERFNumber    The new ERF number for the project.
   * @param newTotalFee     The new total fee for the project.
   * @param newTotalPaid    The new amount paid for the project.
   * @param newDeadline     The new deadline for the project.
   */
  public void updateProjectSelective(int projectID, String newProjectName, String newBuildingType, String newERFNumber,
      Double newTotalFee, Double newTotalPaid, java.sql.Date newDeadline, Integer newArchitectID,
      Integer newContractorID, Integer newCustomerID) {

    // Validate foreign keys
    try (Connection connection = PoisePMS.getConnection()) {
      boolean valid = true;

      if (newArchitectID != null && !existsInTable(connection, "architects", "ArchitectID", newArchitectID)) {
        System.out.println("Invalid ArchitectID: " + newArchitectID + ". Please provide a valid ID.");
        valid = false;
      }
      if (newContractorID != null && !existsInTable(connection, "contractors", "ContractorID", newContractorID)) {
        System.out.println("Invalid ContractorID: " + newContractorID + ". Please provide a valid ID.");
        valid = false;
      }
      if (newCustomerID != null && !existsInTable(connection, "customers", "CustomerID", newCustomerID)) {
        System.out.println("Invalid CustomerID: " + newCustomerID + ". Please provide a valid ID.");
        valid = false;
      }

      if (!valid) {
        return; // Abort the update if any ID is invalid
      }
    } catch (SQLException e) {
      System.out.println("Error validating foreign keys: " + e.getMessage());
      return;
    }

    // Start building the SQL query
    StringBuilder query = new StringBuilder("UPDATE projects SET ");
    boolean hasFieldsToUpdate = false;

    // Dynamically build the query based on non-null values
    if (newProjectName != null) {
      query.append("ProjectName = ?");
      hasFieldsToUpdate = true;
    }
    if (newBuildingType != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("BuildingType = ?");
      hasFieldsToUpdate = true;
    }
    if (newERFNumber != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("ERFNumber = ?");
      hasFieldsToUpdate = true;
    }
    if (newTotalFee != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("TotalFee = ?");
      hasFieldsToUpdate = true;
    }
    if (newTotalPaid != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("TotalPaid = ?");
      hasFieldsToUpdate = true;
    }
    if (newDeadline != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("Deadline = ?");
      hasFieldsToUpdate = true;
    }
    if (newArchitectID != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("ArchitectID = ?");
      hasFieldsToUpdate = true;
    }
    if (newContractorID != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("ContractorID = ?");
      hasFieldsToUpdate = true;
    }
    if (newCustomerID != null) {
      if (hasFieldsToUpdate)
        query.append(", ");
      query.append("CustomerID = ?");
      hasFieldsToUpdate = true;
    }

    if (!hasFieldsToUpdate) {
      // If no fields are provided, return early
      System.out.println("No fields provided to update.");
      return;
    }

    // Append the WHERE clause
    query.append(" WHERE ProjectID = ?");

    // Execute the query
    try (Connection connection = PoisePMS.getConnection();
        PreparedStatement statement = connection.prepareStatement(query.toString())) {

      // Set the parameters for the non-null values
      int index = 1;
      if (newProjectName != null)
        statement.setString(index++, newProjectName);
      if (newBuildingType != null)
        statement.setString(index++, newBuildingType);
      if (newERFNumber != null)
        statement.setString(index++, newERFNumber);
      if (newTotalFee != null)
        statement.setDouble(index++, newTotalFee);
      if (newTotalPaid != null)
        statement.setDouble(index++, newTotalPaid);
      if (newDeadline != null)
        statement.setDate(index++, newDeadline);
      if (newArchitectID != null)
        statement.setInt(index++, newArchitectID);
      if (newContractorID != null)
        statement.setInt(index++, newContractorID);
      if (newCustomerID != null)
        statement.setInt(index++, newCustomerID);

      // Set the ProjectID in the WHERE clause
      statement.setInt(index, projectID);

      // Execute the update
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Project updated successfully.");
      } else {
        System.out.println("No project found with the given ID.");
      }

    } catch (SQLException e) {
      System.out.println("Error updating project: " + e.getMessage());
    }
  }

  // Helper method to check existence in a table
  private boolean existsInTable(Connection connection, String tableName, String columnName, Integer value)
      throws SQLException {
    String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, value);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt(1) > 0;
      }
    }
    return false;
  }

  /**
   * Deletes the project with the specified ID from the database.
   *
   * @param projectID The ID of the project to be deleted.
   */
  // Method for deleting a project
  public void deleteProject(int projectID) {
    try (Connection connection = PoisePMS.getConnection(); // to establish a connection
        PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_SQL)) {// prepares the SQL statement
      // Sets the ProjectID parameter in the prepared statement
      statement.setInt(1, projectID);
      // Executes the SQL
      int rowsDeleted = statement.executeUpdate();
      if (rowsDeleted > 0) {// if any rows are affected
        // print this message
        System.out.println("Project deleted successfully.");
      }
      // Catches exceptions and prints this message if any occurs
    } catch (SQLException e) {
      System.out.println("Error deleting project: " + e.getMessage());
    }
  }

  /**
   * Finalizes the project by setting the completion date.
   *
   * @param projectID      The ID of the project to finalize.
   * @param completionDate The date when the project was completed.
   */
  // Method for Finalizing a project by setting the completion date
  public void finalizeProject(int projectID, java.sql.Date completionDate) {
    try (Connection connection = PoisePMS.getConnection(); // connects to the database
        // Prepares SQL statement
        PreparedStatement statement = connection.prepareStatement(FINALIZE_PROJECT_SQL)) {
      // Sets the parameters in p=the prepared statement
      statement.setDate(1, completionDate);
      statement.setInt(2, projectID);
      // Executes the statement
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {// if any rows affected
        // Prints this message
        System.out.println("Project finalized successfully.");
      }
      // Catches any exceptions
    } catch (SQLException e) {
      // Prints the error message and StackTRace if any exceptions occurs
      System.out.println("Error finalizing project: " + e.getMessage());
    }
  }

  /**
   * Retrieves and displays all projects from the database.
   */
  // Method to Display all projects
  public void displayAllProjects() {
    try (Connection connection = PoisePMS.getConnection(); // gets the connection to the database
        // Prepares the SQL Statement
        PreparedStatement statement = connection.prepareStatement(DISPLAY_ALL_PROJECTS_SQL);
        // Used to store values returned
        ResultSet resultSet = statement.executeQuery()) {
      // loops Through the ResultSet
      while (resultSet.next()) {
        // gets the values of the projects
        int id = resultSet.getInt("ProjectID");
        String name = resultSet.getString("ProjectName");
        String buildingType = resultSet.getString("BuildingType");
        String erfNumber = resultSet.getString("ERFNumber");
        double totalFee = resultSet.getDouble("TotalFee");
        double totalPaid = resultSet.getDouble("TotalPaid");
        Date deadline = resultSet.getDate("Deadline");
        Date completionDate = resultSet.getDate("CompletionDate");
        int architectID = resultSet.getInt("ArchitectID");
        int contractorID = resultSet.getInt("ContractorID");
        int customerID = resultSet.getInt("CustomerID");
        // prints the values in a formatted way
        System.out.printf(
            "ID: %d, Name: %s, Building Type: %s, ERF Number: %s, Total Fee: %.2f, Total Paid: %.2f, Deadline: %s, Completion Date: %s, ArchitectID: %d, ContractorID: %d, CustomerID: %d%n",
            id, name, buildingType, erfNumber, totalFee, totalPaid, deadline, completionDate, architectID, contractorID,
            customerID);
      }
      // Catches any exceptions
    } catch (SQLException e) {
      // and Prints a error message when exceptions occur
      System.out.println("Error displaying projects: " + e.getMessage());
    }
  }

  /**
   * Finds and displays all projects that are incomplete (i.e., without a
   * completion date).
   */
  // Method used to find incomplete projects
  public void findIncompleteProjects() {
    try (Connection connection = PoisePMS.getConnection(); // sets up a database connection
        // Prepares the SQL statement
        PreparedStatement statement = connection.prepareStatement(FIND_INCOMPLETE_PROJECTS_SQL);
        ResultSet resultSet = statement.executeQuery()) {
      // loop through the results and retrieves the data for each project
      while (resultSet.next()) {
        int id = resultSet.getInt("ProjectID");
        String name = resultSet.getString("ProjectName");
        String buildingType = resultSet.getString("BuildingType");
        String erfNumber = resultSet.getString("ERFNumber");
        double totalFee = resultSet.getDouble("TotalFee");
        double totalPaid = resultSet.getDouble("TotalPaid");
        Date deadline = resultSet.getDate("Deadline");
        int architectID = resultSet.getInt("ArchitectID");
        int contractorID = resultSet.getInt("ContractorID");
        int customerID = resultSet.getInt("CustomerID");
//then displays it in a formatted manner 
        System.out.printf(
            "ID: %d, Name: %s, Building Type: %s, ERF Number: %s, Total Fee: %.2f, Total Paid: %.2f, Deadline: %s, ArchitectID: %d, ContractorID: %d, CustomerID: %d%n",
            id, name, buildingType, erfNumber, totalFee, totalPaid, deadline, architectID, contractorID, customerID);
      }
    } catch (SQLException e) {
      System.out.println("Error finding incomplete projects: " + e.getMessage());
    }
  }

  /**
   * Finds and displays projects that are past their due date and are still
   * incomplete.
   */
  /*
   * Method to find projects past their due date by using the SQL query that is
   * designed to check the current date and comparing it to the due date
   */
  public void findProjectsPastDueDate() {
    try (Connection connection = PoisePMS.getConnection(); // connects to the database
        // Prepares the SQL statement
        PreparedStatement statement = connection.prepareStatement(FIND_PROJECTS_PAST_DUE_DATE_SQL);
        ResultSet resultSet = statement.executeQuery()) {// executes the query and stores results in resultSet
      // loops through the ResultSet
      while (resultSet.next()) {
        // Retrieves the values
        int id = resultSet.getInt("ProjectID");
        String name = resultSet.getString("ProjectName");
        String buildingType = resultSet.getString("BuildingType");
        String erfNumber = resultSet.getString("ERFNumber");
        double totalFee = resultSet.getDouble("TotalFee");
        double totalPaid = resultSet.getDouble("TotalPaid");
        Date deadline = resultSet.getDate("Deadline");
        int architectID = resultSet.getInt("ArchitectID");
        int contractorID = resultSet.getInt("ContractorID");
        int customerID = resultSet.getInt("CustomerID");
        // Prints them in a formatted manner
        System.out.printf(
            "ID: %d, Name: %s, Building Type: %s, ERF Number: %s, Total Fee: %.2f, Total Paid: %.2f, Deadline: %s, ArchitectID: %d, ContractorID: %d, CustomerID: %d%n",
            id, name, buildingType, erfNumber, totalFee, totalPaid, deadline, architectID, contractorID, customerID);
      }
      // Catches any exceptions
    } catch (SQLException e) {
      // if any prints out this message
      System.out.println("Error finding projects past due date: " + e.getMessage());
    }
  }

  /**
   * Finds and displays projects based on the given ID or name. If the search
   * query is numeric, it is treated as a project ID. Otherwise, it is treated as
   * part of the project name.
   *
   * @param searchQuery The project ID or name to search for.
   */
  // Method used to find project by ID or name
  public void findProjectByIDOrName(String searchQuery) {
    try (Connection connection = PoisePMS.getConnection(); // connect to the database
        // Prepares the SQL statement
        PreparedStatement statement = connection.prepareStatement(FIND_PROJECT_BY_ID_OR_NAME_SQL)) {

      // Sets parameters for the query
      try {
        int projectID = Integer.parseInt(searchQuery);
        statement.setInt(1, projectID);
        // Catches exception if non numeric values are entered
      } catch (NumberFormatException e) {
        statement.setNull(1, java.sql.Types.INTEGER);
      }
      // Allows the query to search based on the project name
      statement.setString(2, "%" + searchQuery + "%");
      // Executes the query and stores results in resultSet
      ResultSet resultSet = statement.executeQuery();
      // Loops through the resultSet
      while (resultSet.next()) {
        // and retrieves the values
        int id = resultSet.getInt("ProjectID");
        String name = resultSet.getString("ProjectName");
        String buildingType = resultSet.getString("BuildingType");
        String erfNumber = resultSet.getString("ERFNumber");
        double totalFee = resultSet.getDouble("TotalFee");
        double totalPaid = resultSet.getDouble("TotalPaid");
        Date deadline = resultSet.getDate("Deadline");
        Date completionDate = resultSet.getDate("CompletionDate");
        int architectID = resultSet.getInt("ArchitectID");
        int contractorID = resultSet.getInt("ContractorID");
        int customerID = resultSet.getInt("CustomerID");
        // Prints it in a formatted manner
        System.out.printf(
            "ID: %d, Name: %s, Building Type: %s, ERF Number: %s, Total Fee: %.2f, Total Paid: %.2f, Deadline: %s, Completion Date: %s, ArchitectID: %d, ContractorID: %d, CustomerID: %d%n",
            id, name, buildingType, erfNumber, totalFee, totalPaid, deadline, completionDate, architectID, contractorID,
            customerID);
      }
      // Catches any exceptions
    } catch (SQLException e) {
      // if any prints out this error message
      System.out.println("Error finding project by ID or name: " + e.getMessage());
    }
  }
}