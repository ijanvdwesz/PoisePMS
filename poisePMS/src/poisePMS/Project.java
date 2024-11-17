package poisePMS;

import java.sql.Date;

/**
 * The {@code Project} class represents a project entity in the Poise Project
 * Management System. It includes fields for storing project information and
 * provides getters and setters for accessing and modifying this information.
 * 
 * @author Ijan van der Westhuizen
 * @version 1.0
 * @since 2024-08-12
 */
//Declares a class that holds project data
public class Project {
  // Declares the variables for project class
  /** The unique identifier for the project. */
  private int projectID;

  /** The name of the project. */
  private String projectName;

  /** The type of building associated with the project. */
  private String buildingType;

  /** The ERF number of the project. */
  private String erfNumber;

  /** The total fee charged for the project. */
  private double totalFee;

  /** The amount paid towards the project. */
  private double totalPaid;

  /** The deadline for the project's completion. */
  private Date deadline;

  /** The ID of the architect assigned to the project. */
  private int architectID;

  /** The ID of the contractor assigned to the project. */
  private int contractorID;

  /** The ID of the customer for whom the project is being developed. */
  private int customerID;

  /** The date the project was completed. */
  private Date completionDate;

  /** A flag indicating whether the project is finalized. */
  private boolean finalized;

  /**
   * Default constructor for creating an empty Project object.
   */
  Project() {
  }

  /**
   * Constructor to initialize a Project object with specified values.
   * 
   * @param projectID      The unique ID of the project.
   * @param projectName    The name of the project.
   * @param buildingType   The type of building involved in the project.
   * @param erfNumber      The ERF number associated with the project.
   * @param totalFee       The total fee for the project.
   * @param totalPaid      The amount paid towards the project.
   * @param deadline       The project's deadline.
   * @param architectID    The ID of the assigned architect.
   * @param contractorID   The ID of the assigned contractor.
   * @param customerID     The ID of the customer.
   * @param completionDate The date when the project was completed.
   * @param finalized      The flag indicating if the project is finalized.
   */
  public Project(int projectID, String projectName, String buildingType, String erfNumber, double totalFee,
      double totalPaid, Date deadline, int architectID, int contractorID, int customerID, Date completionDate,
      boolean finalized) {
    this.projectID = projectID;
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.erfNumber = erfNumber;
    this.totalFee = totalFee;
    this.totalPaid = totalPaid;
    this.deadline = deadline;
    this.architectID = architectID;
    this.contractorID = contractorID;
    this.customerID = customerID;
    this.completionDate = completionDate;
    this.finalized = finalized;
  }

  /**
   * Gets the project ID.
   * 
   * @return The unique project ID.
   */
  public int getProjectID() {
    return projectID;
  }

  /**
   * Sets the project ID.
   * 
   * @param projectID The new project ID.
   */
  public void setProjectID(int projectID) {
    this.projectID = projectID;
  }

  /**
   * Gets the project name.
   * 
   * @return The name of the project.
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Sets the project name.
   * 
   * @param projectName The new project name.
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * Gets the building type.
   * 
   * @return The type of building in the project.
   */
  public String getBuildingType() {
    return buildingType;
  }

  /**
   * Sets the building type.
   * 
   * @param buildingType The new building type.
   */
  public void setBuildingType(String buildingType) {
    this.buildingType = buildingType;
  }

  /**
   * Gets the ERF number.
   * 
   * @return The ERF number of the project.
   */
  public String getERFNumber() {
    return erfNumber;
  }

  /**
   * Sets the ERF number.
   * 
   * @param erfNumber The new ERF number.
   */
  public void setERFNumber(String erfNumber) {
    this.erfNumber = erfNumber;
  }

  /**
   * Gets the total fee for the project.
   * 
   * @return The total project fee.
   */
  public double getTotalFee() {
    return totalFee;
  }

  /**
   * Sets the total fee for the project.
   * 
   * @param totalFee The new total fee.
   */
  public void setTotalFee(double totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * Gets the total amount paid.
   * 
   * @return The amount paid towards the project.
   */
  public double getTotalPaid() {
    return totalPaid;
  }

  /**
   * Sets the total amount paid.
   * 
   * @param totalPaid The new total amount paid.
   */
  public void setTotalPaid(double totalPaid) {
    this.totalPaid = totalPaid;
  }

  /**
   * Gets the project deadline.
   * 
   * @return The deadline date for the project.
   */
  public Date getDeadline() {
    return deadline;
  }

  /**
   * Sets the project deadline.
   * 
   * @param deadline The new deadline date.
   */
  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  /**
   * Gets the architect ID.
   * 
   * @return The ID of the architect assigned to the project.
   */
  public int getArchitectID() {
    return architectID;
  }

  /**
   * Sets the architect ID.
   * 
   * @param architectID The new architect ID.
   */
  public void setArchitectID(int architectID) {
    this.architectID = architectID;
  }

  /**
   * Gets the contractor ID.
   * 
   * @return The ID of the contractor assigned to the project.
   */
  public int getContractorID() {
    return contractorID;
  }

  /**
   * Sets the contractor ID.
   * 
   * @param contractorID The new contractor ID.
   */
  public void setContractorID(int contractorID) {
    this.contractorID = contractorID;
  }

  /**
   * Gets the customer ID.
   * 
   * @return The ID of the customer associated with the project.
   */
  public int getCustomerID() {
    return customerID;
  }

  /**
   * Sets the customer ID.
   * 
   * @param customerID The new customer ID.
   */
  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  /**
   * Gets the project's completion date.
   * 
   * @return The date the project was completed.
   */
  public Date getCompletionDate() {
    return completionDate;
  }

  /**
   * Sets the project's completion date.
   * 
   * @param completionDate The new completion date.
   */
  public void setCompletionDate(Date completionDate) {
    this.completionDate = completionDate;
  }

  /**
   * Checks if the project is finalized.
   * 
   * @return {@code true} if the project is finalized; {@code false} otherwise.
   */
  public boolean isFinalized() {
    return finalized;
  }

  /**
   * Sets the project as finalized.
   * 
   * @param finalized {@code true} if the project is finalized; {@code false}
   *                  otherwise.
   */
  public void setFinalized(boolean finalized) {
    this.finalized = finalized;
  }
}