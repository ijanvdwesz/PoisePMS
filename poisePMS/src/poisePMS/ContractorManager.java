package poisePMS;

import java.sql.*;

/**
 * The {@code ContractorManager} class provides methods to manage contractors
 * within the Poise Project Management System (PMS). It includes functionalities
 * to add, update, delete, and display contractors.
 * 
 * <p>
 * This class is responsible for handling all contractor-related operations and
 * interacting with the underlying database to perform CRUD operations.
 * </p>
 * 
 * @author Ijan van der Westhuizen
 * @version 1.0
 * @since 2024-08-12
 */
public class ContractorManager {

	/**
	 * Adds a new contractor to the system.
	 * 
	 * <p>
	 * This method takes the Firstname, Surname, telephone, email, and physical
	 * address of the contractor as parameters and adds the contractor to the
	 * database.
	 * </p>
	 * 
	 * @param Firstname       The Firstname of the contractor.
	 * @param Surname         The Surname of the contractor.
	 * @param telephone       The telephone number of the contractor.
	 * @param email           The email address of the contractor.
	 * @param physicalAddress The physical address of the contractor.
	 */
	// method for adding contractors
	public void addContractor(String Firstname, String Surname, String telephone, String email, String physicalAddress) {
		// Stores the SQL query for adding contractors in a variable with placeholders
		// "?" for the Values
		String sql = "INSERT INTO contractors (Firstname, Surname, Telephone, Email, PhysicalAddress) VALUES (?, ?, ?, ?, ?)";
		// try with resources: Establishes connection to database and prepares the SQL
		try (Connection conn = PoisePMS.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Sets the values for the "?" SQL query parameters
			pstmt.setString(1, Firstname);
			pstmt.setString(2, Surname);
			pstmt.setString(3, telephone);
			pstmt.setString(4, email);
			pstmt.setString(5, physicalAddress);
			// Executes the SQL query to insert the contractor into the database
			pstmt.executeUpdate();
			// handles exceptions that might occur
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the details of an existing contractor in the system.
	 * 
	 * <p>
	 * This method takes the contractor's ID and the new details as parameters and
	 * updates the contractor's information in the database.
	 * </p>
	 * 
	 * @param id              The ID of the contractor to be updated.
	 * @param Firstname       The new Firstname of the contractor.
	 * @param Surname         The new Surname of the contractor.
	 * @param telephone       The new telephone number of the contractor.
	 * @param email           The new email address of the contractor.
	 * @param physicalAddress The new physical address of the contractor.
	 */
	// Method to Update contractors
	public void updateContractor(int id, String Firstname, String Surname, String telephone, String email,
			String physicalAddress) {
		// Builds the dynamic SQL query
		StringBuilder sql = new StringBuilder("UPDATE contractors SET ");
		boolean firstField = true;

		// Dynamically adds fields if they are not empty ,all if statements are similar
		// but for different values
		if (Firstname != null && !Firstname.trim().isEmpty()) {
			if (!firstField)
				sql.append(", ");
			sql.append("Firstname = ?");
			firstField = false;
		}
		if (Surname != null && !Surname.trim().isEmpty()) {
			if (!firstField)
				sql.append(", ");
			sql.append("Surname = ?");
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

		// Only updates if there's at least one field to update
		if (!firstField) {
			sql.append(" WHERE ContractorID = ?");

			// Opens database connection and sets up SQL query
			try (Connection conn = PoisePMS.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
				// Keeps track of position of placeholders
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

				// Sets the ContractorID in the WHERE clause
				pstmt.setInt(index, id);

				// Executes the update and returns number of rows affected
				int rowsUpdated = pstmt.executeUpdate();
				// if its more than 0 prints this message
				if (rowsUpdated > 0) {
					System.out.println("Contractor updated successfully.");
					// Otherwise prints this one
				} else {
					System.out.println("No contractor found with the given ID.");
				}
				// Catches exceptions and prints the error message
			} catch (SQLException e) {
				System.out.println("Error updating contractor: " + e.getMessage());
			}
			// prints this if no fields to update where found
		} else {
			System.out.println("No fields to update.");
		}
	}

	/**
	 * Deletes a contractor from the system.
	 * 
	 * <p>
	 * This method takes the contractor's ID as a parameter and removes the
	 * contractor from the database.
	 * </p>
	 * 
	 * @param id The ID of the contractor to be deleted.
	 */
	// Method to delete contractors
	public void deleteContractor(int id) {
		// declares variables for database connections and prepared statement
		Connection conn = null;
		PreparedStatement pstmt = null;
		// Opens a database connection
		try {
			conn = PoisePMS.getConnection();
			int defaultContractorId = -1; // Default contractor ID used to replaced the deleted contractor

			/*
			 * Updates related records in the projects table to use the default contractor
			 * ID
			 */
			String updateProjectsSql = "UPDATE projects SET ContractorID = ? WHERE ContractorID = ?";
			pstmt = conn.prepareStatement(updateProjectsSql);
			pstmt.setInt(1, defaultContractorId);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			// Deletes the contractor from the contractors table
			String deleteContractorSql = "DELETE FROM contractors WHERE ContractorID = ?";
			pstmt = conn.prepareStatement(deleteContractorSql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			// Prints the message to inform user that contractor was deleted successfully
			System.out.println("Contractor been deleted. Projects reassigned to default contractor.");
			// if any exception occurs ,prints the Stacktrace
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays all contractors currently in the system.
	 * 
	 * <p>
	 * This method retrieves and displays a list of all contractors stored in the
	 * database.
	 * </p>
	 */
	// Method to display all contractors
	public void displayAllContractors() {
		// Defines the SQL query for displaying all contractors
		String sql = "SELECT * FROM contractors";
		// Opens a connection to the database
		try (Connection conn = PoisePMS.getConnection();
				// Creates a Statement object to execute the query
				Statement stmt = conn.createStatement();
				// Stores the results using ResultSet
				ResultSet rs = stmt.executeQuery(sql)) {
			// Loops through the Resultset
			while (rs.next()) {
				// to display the values one by one
				System.out.println("Contractor ID: " + rs.getInt("ContractorID"));
				System.out.println("Firstname: " + rs.getString("Firstname"));
				System.out.println("Surname: " + rs.getString("Surname"));
				System.out.println("Telephone: " + rs.getString("Telephone"));
				System.out.println("Email: " + rs.getString("Email"));
				System.out.println("Physical Address: " + rs.getString("PhysicalAddress"));
				System.out.println();
			}
			// Catches exceptions that might occur and prints the StackTrace
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}