package poisePMS;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code PoisePMS} class serves as the main entry point for managing the
 * Poise Project Management System (PMS). It provides methods to handle various
 * operations related to architects, customers, contractors, and projects. The
 * class connects to a MySQL database to perform CRUD operations.
 * 
 * <p>
 * It includes sub-menus for managing different entities such as architects,
 * customers, contractors, and projects. Each sub-menu allows the user to add,
 * update, delete, and display information related to the respective entity.
 * </p>
 * 
 * <p>
 * The database connection details are stored as final strings for security and
 * ease of management.
 * </p>
 * 
 * @version 1.0
 * @since 2024-08-12
 */
public class PoisePMS {
  // Stores information for database connection
  private static final String URL = "jdbc:mysql://localhost:3306/poisepms";
  private static final String USER = "root";
  private static final String PASSWORD = "Jenice@18";

  // For formatting dates in the "yyyy-MM-dd" format
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // date format

  // Instantiates manager instances for handling operations related to the
  // specific entity
  private ArchitectManager architectManager = new ArchitectManager();
  private CustomerManager customerManager = new CustomerManager();
  private ContractorManager contractorManager = new ContractorManager();
  private ProjectManager projectManager = new ProjectManager();

  /**
   * Establishes a connection to the MySQL database using the specified URL, user,
   * and password.
   * 
   * @return A {@code Connection} object representing the database connection.
   * @throws SQLException If a database access error occurs or the URL is
   *                      incorrect.
   */
  // Estabishes and returns a connection to the database,throws SQL exception if
  // error occurs when connecting
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  /**
   * Handles the Architect Menu operations including adding, updating, deleting,
   * and displaying architects. Provides options to navigate the menu and prompts
   * the user for necessary information.
   *
   * @see ArchitectManager
   * @param scanner A {@code Scanner} object used for reading user input.
   */
  // Method to handle the Architect Menu
  private void handleArchitects(Scanner scanner) {
    // Prints the Menu in a while loop
    while (true) {
      System.out.println(); // Adds a blank line
      System.out.println("Architect Menu");
      System.out.println("1. Add Architect");
      System.out.println("2. Update Architect");
      System.out.println("3. Delete Architect");
      System.out.println("4. Display All Architects");
      System.out.println("5. Go Back");
      System.out.print("Choose an option: ");
      // declares the variable to be used by switch statement
      int choice;
      /*
       * using try/catch for errors prevention this block reads the users input if
       * invalid prompts again
       */
      try {
        choice = Integer.parseInt(scanner.nextLine());// expects a number(INteger)
      } catch (NumberFormatException e) {// catches exception if non numeric value is entered
        System.out.println("Invalid input. Please enter a number.");// then prints this
        continue;
      }
      // the case will determine what will be printed based on the users entry
      switch (choice) {
      case 1:// if user enters 1
        System.out.println(); // Adds a blank line
        // Prompts for architect details
        System.out.print("Enter architect Firstname: ");
        String architectFirstname = scanner.nextLine();
        System.out.print("Enter architect Surname: ");
        String architectSurname = scanner.nextLine();
        System.out.print("Enter architect telephone: ");
        String architectTelephone = scanner.nextLine();
        System.out.print("Enter architect email: ");
        String architectEmail = scanner.nextLine();
        System.out.print("Enter architect physical address: ");
        String architectAddress = scanner.nextLine();
        // Call the addArchitect method and check if the insertion was successful
        boolean success = architectManager.addArchitect(architectFirstname, architectSurname, architectTelephone,
            architectEmail, architectAddress);

        // Provides a confirmation message based on outcome
        if (success) {
          System.out.println("Architect successfully added.");
        } else {
          System.out.println("Error adding architect. Please try again.");
        }
        break;
      case 2:
        // if user selects 2
        System.out.println(); // Adds a blank line
        System.out.print("Enter architect ID: ");// Prompts for the ID
        int architectID;
        try {
          // Parses the value
          architectID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          // Catches exceptions related to non-numeric input
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }

        // Prompts for the rest of the values, allowing user to skip by pressing Enter
        System.out.print("Enter new architect Firstname (Press Enter to skip): ");
        String newArchitectFirstname = scanner.nextLine();
        if (newArchitectFirstname.trim().isEmpty()) {
          newArchitectFirstname = null; // Skip updating this field
        }

        System.out.print("Enter new architect Surname (Press Enter to skip): ");
        String newArchitectSurname = scanner.nextLine();
        if (newArchitectSurname.trim().isEmpty()) {
          newArchitectSurname = null; // Skip updating this field
        }

        System.out.print("Enter new architect telephone (Press Enter to skip): ");
        String newArchitectTelephone = scanner.nextLine();
        if (newArchitectTelephone.trim().isEmpty()) {
          newArchitectTelephone = null; // Skip updating this field
        }

        System.out.print("Enter new architect email (Press Enter to skip): ");
        String newArchitectEmail = scanner.nextLine();
        if (newArchitectEmail.trim().isEmpty()) {
          newArchitectEmail = null; // Skip updating this field
        }

        System.out.print("Enter new architect physical address (Press Enter to skip): ");
        String newArchitectAddress = scanner.nextLine();
        if (newArchitectAddress.trim().isEmpty()) {
          newArchitectAddress = null; // Skip updating this field
        }

        // Calls the updateArchitect method with dynamic updates
        architectManager.updateArchitect(architectID, newArchitectFirstname, newArchitectSurname, newArchitectTelephone,
            newArchitectEmail, newArchitectAddress);

        break;
      case 3:// if user selects 3
        System.out.println(); // Adds a blank line
        System.out.print("Enter architect ID: ");// Prompts for the ID
        int deleteArchitectID;
        try {
          deleteArchitectID = Integer.parseInt(scanner.nextLine());
          // Catches exceptions related to non numeric input and prints error message
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }
        // Calls the method to delete the architect which id was entered
        architectManager.deleteArchitect(deleteArchitectID);
        break;
      case 4:// if the user selects 4
        // Calls the displayAllArchitects method
        architectManager.displayAllArchitects();
        break;
      case 5:// if user selects 5 return to previous menu
        return;
      default:
        // if 1-5 was not selected print this message
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Handles the Customer Menu operations including adding, updating, deleting,
   * and displaying customers. Provides options to navigate the menu and prompts
   * the user for necessary information to perform the chosen operation.
   * 
   * <p>
   * The menu options are:
   * <ul>
   * <li>1. Add Customer - Prompts for customer details and adds a new
   * customer.</li>
   * <li>2. Update Customer - Prompts for customer ID and new details to update an
   * existing customer.</li>
   * <li>3. Delete Customer - Prompts for customer ID and deletes the specified
   * customer.</li>
   * <li>4. Display All Customers - Displays all customers in the system.</li>
   * <li>5. Go Back - Returns to the previous menu.</li>
   * </ul>
   * </p>
   * 
   * <p>
   * User input is validated to ensure that numeric inputs are correctly parsed.
   * If an invalid input is detected, an error message is displayed, and the user
   * is prompted to try again.
   * </p>
   * 
   * @see Customer Menu
   * @param scanner A {@code Scanner} object used for reading user input from the
   *                console.
   */
  // Method to Handle the Customer Menu
  private void handleCustomers(Scanner scanner) {
    while (true) {
      // Prints the menu
      System.out.println(); // Adds a blank line
      System.out.println("Customer Menu");
      System.out.println("1. Add Customer");
      System.out.println("2. Update Customer");
      System.out.println("3. Delete Customer");
      System.out.println("4. Display All Customers");
      System.out.println("5. Go Back");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {// catches exceptions for non numeric input
        System.out.println("Invalid input. Please enter a number.");// if so prints tis message
        continue;
      }

      switch (choice) {
      case 1:// if user selects 1
        System.out.println(); // Adds a blank line
        // Prompt for customer details
        System.out.print("Enter customer Firstname: ");
        String customerFirstname = scanner.nextLine();
        System.out.print("Enter customer Surname: ");
        String customerSurname = scanner.nextLine();
        System.out.print("Enter customer telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer physical address: ");
        String address = scanner.nextLine();
        // calls the method to add a customer
        customerManager.addCustomer(customerFirstname, customerSurname, telephone, email, address);
        break;
      case 2:// if user selects option 2
        System.out.print("Enter customer ID: ");// Prompts for customer ID
        int customerID;
        try {
          customerID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {// Catches exceptions for non numeric input
          // then prints out this error message
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }
        System.out.println(); // Adds a blank line
        // Prompts for the rest of the customer details
        System.out.print("Enter new customer Firstname: ");
        String newCustomerFirstname = scanner.nextLine();
        System.out.print("Enter new customer Surname: ");
        String newCustomerSurname = scanner.nextLine();
        System.out.print("Enter new customer telephone: ");
        String newTelephone = scanner.nextLine();
        System.out.print("Enter new customer email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new customer physical address: ");
        String newAddress = scanner.nextLine();
        // Calls the updateCustomer method
        customerManager.updateCustomer(customerID, newCustomerFirstname, newCustomerSurname, newTelephone, newEmail,
            newAddress);
        break;
      case 3:// if user selects option 3
        System.out.print("Enter customer ID: ");// Prompts for the Customer ID
        int deleteCustomerID;
        try {
          deleteCustomerID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {// Catch exceptions for non numeric input
          // if so prints this error message
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }
        // calls the deleteCustomer method
        customerManager.deleteCustomer(deleteCustomerID);
        break;
      case 4:// if option 4 is chosen
        // Calls the displayAllCustomers method
        customerManager.displayAllCustomers();
        break;
      case 5:// goes back to the previous menu for option 5
        return;
      default:
        // none of the above results in this message being printed
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Handles the Contractor menu operations such as adding, updating, deleting,
   * and displaying contractors.
   * 
   * @see ConractorManager
   * @param scanner A Scanner object used for reading user input.
   */
  // Method for handling contractors menu
  private void handleContractors(Scanner scanner) {
    while (true) {
      // Displays the contractor menu options
      System.out.println(); // Adds a blank line
      System.out.println("Contractor Menu");
      System.out.println("1. Add Contractor");
      System.out.println("2. Update Contractor");
      System.out.println("3. Delete Contractor");
      System.out.println("4. Display All Contractors");
      System.out.println("5. Go Back");
      System.out.print("Choose an option: ");

      int choice;

      // Handles invalid input for the menu choice
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
      case 1:// if option 1 is chosen
        // Prompts for contractor details
        System.out.println(); // Adds a blank line
        System.out.print("Enter contractor Firstname: ");
        String contractorFirstname = scanner.nextLine();
        System.out.print("Enter contractor Surname: ");
        String contractorSurname = scanner.nextLine();
        System.out.print("Enter contractor telephone: ");
        String contractorTelephone = scanner.nextLine();
        System.out.print("Enter contractor email: ");
        String contractorEmail = scanner.nextLine();
        System.out.print("Enter contractor physical address: ");
        String contractorAddress = scanner.nextLine();

        // Calls the addContractor method
        contractorManager.addContractor(contractorFirstname, contractorSurname, contractorTelephone, contractorEmail,
            contractorAddress);
        break;

      case 2:// if 2 is chosen
        System.out.print("Enter contractor ID: ");// prompts for the ID
        int contractorID;
        try {
          // Handles invalid (non numeric ) input
          contractorID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }
        // Prompts for the rest of the details
        System.out.println(); // Adds a blank line
        System.out.print("Enter new contractor Firstname: ");
        String newContractorFirstname = scanner.nextLine();
        System.out.print("Enter new contractor Surname: ");
        String newContractorSurname = scanner.nextLine();
        System.out.print("Enter new contractor telephone: ");
        String newContractorTelephone = scanner.nextLine();
        System.out.print("Enter new contractor email: ");
        String newContractorEmail = scanner.nextLine();
        System.out.print("Enter new contractor physical address: ");
        String newContractorAddress = scanner.nextLine();

        // Calls the updateContractor method
        contractorManager.updateContractor(contractorID, newContractorFirstname, newContractorSurname,
            newContractorTelephone, newContractorEmail, newContractorAddress);
        break;

      case 3:// if option 3 is chosen
        System.out.print("Enter contractor ID: ");// prompts for the Contractors ID
        int deleteContractorID;
        try {
          // Handles invalid(non Numeric) input
          deleteContractorID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number.");
          continue;
        }

        // Calls the deleteContractor Method
        contractorManager.deleteContractor(deleteContractorID);
        break;

      case 4:// if option 4 is chosen
        // call the displayAllContractors method
        contractorManager.displayAllContractors();
        break;

      case 5:
        // Exits the contractor menu and returns to the previous menu
        return;
      // if none of the above (1-5 ) are selected
      default:
        // Print this message
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * Handles the Project Menu operations including adding, updating, deleting,
   * finalizing, and displaying projects. Provides options to navigate the menu
   * and prompts the user for necessary information to perform the chosen
   * operation.
   * 
   * <p>
   * The menu options are:
   * <ul>
   * <li>1. Add Project - Prompts for project details and adds a new project. If
   * the project name is not provided, it is auto-generated based on the customer
   * ID and building type.</li>
   * <li>2. Update Project - Prompts for project ID and allows selective updating
   * of project details such as name, building type, ERF number, total fee, total
   * paid, and deadline.</li>
   * <li>3. Delete Project - Prompts for project ID and deletes the specified
   * project.</li>
   * <li>4. Finalize Project - Prompts for project ID and completion date, and
   * finalizes the project.</li>
   * <li>5. Display All Projects - Displays all projects in the system.</li>
   * <li>6. Find Incomplete Projects - Displays projects that are incomplete.</li>
   * <li>7. Find Projects Past Due Date - Displays projects that are past their
   * due date.</li>
   * <li>8. Find Project by ID or Name - Prompts for a project ID or name and
   * displays matching projects.</li>
   * <li>9. Go Back - Returns to the previous menu.</li>
   * </ul>
   * </p>
   * 
   * <p>
   * User input is validated to handle various scenarios:
   * <ul>
   * <li>For numeric inputs, {@code InputMismatchException} is caught and an error
   * message is displayed.</li>
   * <li>For date inputs, {@code ParseException} is caught and an error message is
   * displayed if the date format is invalid.</li>
   * </ul>
   * </p>
   * 
   * @see ProjectManager
   * @param scanner A {@code Scanner} object used for reading user input from the
   *                console.
   */
  // Method for handling projects menu
  private void handleProjects(Scanner scanner) {
    while (true) {
      // Prints the menu
      System.out.println(); // Adds a blank line
      System.out.println("Project Menu");
      System.out.println("1. Add Project");
      System.out.println("2. Update Project");
      System.out.println("3. Delete Project");
      System.out.println("4. Finalize Project");
      System.out.println("5. Display All Projects");
      System.out.println("6. Find Incomplete Projects");
      System.out.println("7. Find Projects Past Due Date");
      System.out.println("8. Find Project by ID or Name");
      System.out.println("9. Go Back");
      System.out.print("Choose an option: ");

      int choice;
      try {
        choice = scanner.nextInt();// reads the users choice
        scanner.nextLine(); // Clears buffer
        // Handles invalid input
      } catch (InputMismatchException e) {
        // if so prints this message
        System.out.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // Clears the invalid input
        continue;
      }

      switch (choice) {
      case 1: // if option 1 is selected
        try {
          // Prompts for Customer ID
          System.out.print("Enter customer ID: ");
          int customerID = scanner.nextInt(); // Reads user input
          scanner.nextLine(); // Clears buffer

          // Prompts for building type
          System.out.print("Enter building type: ");
          String buildingType = scanner.nextLine(); // Reads user input

          // Asks if the project name should be auto-generated or provided
          System.out.println(); // Adds a blank line
          System.out.print("Enter project name (or press Enter to auto-generate): ");
          String projectName = scanner.nextLine(); // Reads user input
          if (projectName.trim().isEmpty()) { // If the input is empty
            // Use the customerName and buildingType to generate the project name
            String customerName = customerManager.getCustomerNameById(customerID);
            projectName = customerName + " - " + buildingType;
          }

          // Continue prompting for other details
          System.out.print("Enter ERF number: ");
          String erfNumber = scanner.nextLine(); // Continue reading user input
          System.out.print("Enter total fee: ");
          double totalFee = scanner.nextDouble();
          System.out.print("Enter total paid: ");
          double totalPaid = scanner.nextDouble();
          scanner.nextLine(); // Clears buffer

          // Prompt for the deadline
          System.out.print("Enter deadline (yyyy-MM-dd) or press Enter to skip: ");
          String deadlineStr = scanner.nextLine();

          java.sql.Date deadlineSqlDate = null; // Default to null if skipped
          if (!deadlineStr.trim().isEmpty()) { // Only attempt to parse if a value is entered
            try {
              java.util.Date deadlineDate = dateFormat.parse(deadlineStr); // Parse date
              deadlineSqlDate = new java.sql.Date(deadlineDate.getTime()); // Convert to SQL date
            } catch (ParseException e) {
              // Print this message if an invalid date is entered
              System.out.println("Invalid date format. Please enter the deadline again.");
              // Ask for the date again
              System.out.print("Enter deadline (yyyy-MM-dd) or press Enter to skip: ");
              deadlineStr = scanner.nextLine();
              if (!deadlineStr.trim().isEmpty()) {
                try {
                  java.util.Date deadlineDate = dateFormat.parse(deadlineStr); // Parse date
                  deadlineSqlDate = new java.sql.Date(deadlineDate.getTime()); // Convert to SQL date
                } catch (ParseException ex) {
                  System.out.println("Invalid date format again. Skipping deadline.");
                }
              }
            }
          }

          // Prompts for architect and contractor IDs
          System.out.print("Enter architect ID: ");
          int architectID = scanner.nextInt();
          System.out.print("Enter contractor ID: ");
          int contractorID = scanner.nextInt();
          scanner.nextLine(); // Clears buffer

          // Calls the addProject method
          projectManager.addProject(projectName, buildingType, erfNumber, totalFee, totalPaid, deadlineSqlDate,
              architectID, contractorID, customerID);

        } catch (InputMismatchException e) {
          // Message printed when exception occurs
          System.out.println("Invalid input. Please enter numeric values.");
          scanner.nextLine(); // Clears the invalid input
        }
        break;

      // Update existing project
      case 2:// IF no 2 is entered
        try {
          System.out.print("Enter project ID: ");// prompts for Project Id
          int projectID = scanner.nextInt();// Reads user input
          scanner.nextLine(); // Clear buffer

          // Prompt for project details
          System.out.print("Enter new project name (or press Enter to leave unchanged): ");// Prompts for new project
                                                                                           // name
          String newProjectName = scanner.nextLine();// Reads user Input
          if (newProjectName.trim().isEmpty()) {
            newProjectName = null; // Ensures no empty input is treated as null see
                                   // projectmanager.updateProjectSelective method
          }

          System.out.print("Enter new building type (or press Enter to leave unchanged): ");
          String newBuildingType = scanner.nextLine();
          if (newBuildingType.trim().isEmpty()) {
            newBuildingType = null;
          }

          System.out.print("Enter new ERF number (or press Enter to leave unchanged): ");
          String newERFNumber = scanner.nextLine();
          if (newERFNumber.trim().isEmpty()) {
            newERFNumber = null;
          }

          System.out.print("Enter new total fee (or press Enter to leave unchanged): ");
          String totalFeeInput = scanner.nextLine().trim();
          Double newTotalFee = null;
          if (!totalFeeInput.isEmpty()) {
            try {
              newTotalFee = Double.parseDouble(totalFeeInput);
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Total fee must be a number.");
            }
          }

          System.out.print("Enter new total paid (or press Enter to leave unchanged): ");
          String totalPaidInput = scanner.nextLine().trim();
          Double newTotalPaid = null;
          if (!totalPaidInput.isEmpty()) {
            try {
              newTotalPaid = Double.parseDouble(totalPaidInput);
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Total paid must be a number.");
            }
          }

          System.out.print("Enter new deadline (yyyy-MM-dd) (or press Enter to leave unchanged): ");
          String newDeadlineStr = scanner.nextLine().trim();
          java.sql.Date newDeadline = null;
          if (!newDeadlineStr.isEmpty()) {
            try {
              java.util.Date newDeadlineDate = dateFormat.parse(newDeadlineStr);
              newDeadline = new java.sql.Date(newDeadlineDate.getTime());
            } catch (ParseException e) {
              System.out.println("Invalid date format.");
            }
          }

          System.out.print("Enter new contractor ID (or press Enter to leave unchanged): ");
          String contractorIDInput = scanner.nextLine().trim();
          Integer newContractorID = null;
          if (!contractorIDInput.isEmpty()) {
            try {
              newContractorID = Integer.parseInt(contractorIDInput);
            } catch (NumberFormatException e) {
              System.out.println("Invalid contractor ID. Skipping contractor update.");
            }
          }

          System.out.print("Enter new architect ID (or press Enter to leave unchanged): ");
          String architectIDInput = scanner.nextLine().trim();
          Integer newArchitectID = null;
          if (!architectIDInput.isEmpty()) {
            try {
              newArchitectID = Integer.parseInt(architectIDInput);
            } catch (NumberFormatException e) {
              System.out.println("Invalid architect ID. Skipping architect update.");
            }
          }

          System.out.print("Enter new customer ID (or press Enter to leave unchanged): ");
          String customerIDInput = scanner.nextLine().trim();
          Integer newCustomerID = null;
          if (!customerIDInput.isEmpty()) {
            try {
              newCustomerID = Integer.parseInt(customerIDInput);
            } catch (NumberFormatException e) {
              System.out.println("Invalid customer ID. Skipping customer update.");
            }
          }

          // Update project selectively
          projectManager.updateProjectSelective(projectID, newProjectName, newBuildingType, newERFNumber, newTotalFee,
              newTotalPaid, newDeadline, newArchitectID, newContractorID, newCustomerID);

        } catch (InputMismatchException e) {
          System.out.println("Invalid input. Please enter numeric values.");
          scanner.nextLine(); // Clear the invalid input
        }
        break;
      case 3:// if option 3 is chosen
        try {// Prompt for ID of project to be deleted
          System.out.print("Enter project ID: ");
          int deleteProjectID = scanner.nextInt();// reads user input
          // Calls the deleteProject method
          projectManager.deleteProject(deleteProjectID);
        } catch (InputMismatchException e) {// throws exception for invalid input
          // Prints this message if exception occurs
          System.out.println("Invalid input. Please enter a numeric project ID.");
          scanner.nextLine(); // Clears the invalid input
        }
        break;
      case 4:// if option 4 is chosen
        try {// Prompts for the project ID
          System.out.print("Enter project ID to finalize: ");
          int finalizeProjectID = scanner.nextInt();// reads user input
          scanner.nextLine(); // Clears buffer
          // Prompts for completion date
          System.out.print("Enter completion date (yyyy-MM-dd): ");
          String completionDateStr = scanner.nextLine();// reads user input
          try {
            // Parses the date
            java.util.Date completionDate = dateFormat.parse(completionDateStr);
            // Calls the finalize project method
            projectManager.finalizeProject(finalizeProjectID, new java.sql.Date(completionDate.getTime()));
            // Catches exception thrown when parsing
          } catch (ParseException e) {
            // Then prints this message
            System.out.println("Invalid date format.");
          }
          // catches exception for invalid input
        } catch (InputMismatchException e) {
          // then prints this message
          System.out.println("Invalid input. Please enter numeric values.");
          scanner.nextLine(); // Clears the invalid input
        }
        break;
      case 5:// if option 5 is chosen
        projectManager.displayAllProjects();// calls the displayAllProjects method
        break;
      case 6:// if option 6 is chosen
        projectManager.findIncompleteProjects();// calls the findIncompleteProjects method
        break;
      case 7:// if option 7 is chosen
        projectManager.findProjectsPastDueDate();// calls the findProjectsPastDueDate method
        break;
      case 8:// if option 8 is chosen
        // Prompt for project ID to be found
        System.out.print("Enter project ID or name: ");
        // Reads the user input
        String searchQuery = scanner.nextLine();
        // Calls the findProjectByIDOrName method
        projectManager.findProjectByIDOrName(searchQuery);
        break;
      case 9:// if option 9 is chosen
        return; // Exits the project menu
      default:// if anything other than 0-9 is chosen
        // Prints this message
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  /**
   * The entry point of the Poise Project Management System (PMS) application.
   * This method displays the main menu and handles user input to navigate through
   * different management options such as managing architects, customers,
   * contractors, and projects. It also provides the option to exit the
   * application.
   *
   * <p>
   * The method initializes a {@link Scanner} object to read user input and a
   * {@link PoisePMS} instance to manage the application operations. The main menu
   * is displayed in a loop, allowing the user to select an option. Based on the
   * user's choice, it calls the appropriate method from the {@link PoisePMS}
   * instance or exits the application.
   * </p>
   *
   * <p>
   * The menu options include:
   * <ul>
   * <li>1. Manage Architects</li>
   * <li>2. Manage Customers</li>
   * <li>3. Manage Contractors</li>
   * <li>4. Manage Projects</li>
   * <li>5. Exit</li>
   * </ul>
   *
   * <p>
   * If an invalid input is entered, the user is prompted to enter a valid number.
   * The scanner is closed and the program exits when option 5 is selected.
   * </p>
   *
   * @param args Command-line arguments (not used in this method).
   */
//Method to handle the Main menu 
  public static void main(String[] args) {
    // Creates Scanner object used to read user input
    Scanner scanner = new Scanner(System.in);
    // Creates a new instance of this class used to manage entities
    PoisePMS poisePMS = new PoisePMS();

    while (true) {
      // Prints the main menu
      System.out.println(); // Adds a blank line
      System.out.println("Main Menu");
      System.out.println("1. Manage Architects");
      System.out.println("2. Manage Customers");
      System.out.println("3. Manage Contractors");
      System.out.println("4. Manage Projects");
      System.out.println("5. Exit");
      System.out.print("Choose an option: ");
      // used to store users choice
      int choice = -1;
      try {// parses users input
        choice = Integer.parseInt(scanner.nextLine());
        // for handling non numeric input
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        continue;
      }

      switch (choice) {
      case 1:// if option 1 is chosen
        // calls the handleArchitects method with scanner object as an argument
        poisePMS.handleArchitects(scanner);
        break;
      case 2:// if option 2 is chosen
        // calls the handleCustomers method with Scanner object as an argument
        poisePMS.handleCustomers(scanner);
        break;
      case 3:// if option 3 is chosen
        // Calls the handleContractors method with scanner object as an argument
        poisePMS.handleContractors(scanner);
        break;
      case 4:// if option 4 is chosen
        // Calls the handleProjects method with scanner object as an argument
        poisePMS.handleProjects(scanner);
        break;
      case 5:// if option 5 is chosen
        scanner.close();// closes the scanner
        System.exit(0);// exits the program
        break;
      default:// if none of the above is chosen print this message
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}