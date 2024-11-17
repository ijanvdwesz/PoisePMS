/*
 * package poisePMS;
 * 
 * import javax.swing.*; import java.awt.*; import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener; import java.sql.Date;
 * 
 * public class ProjectMenuGUI {
 * 
 * private JFrame frame; private JTextField txtProjectName; private JTextField
 * txtBuildingType; private JTextField txtERFNumber; private JTextField
 * txtTotalFee; private JTextField txtTotalPaid; private JTextField txtDeadline;
 * private JTextField txtArchitectID; private JTextField txtContractorID;
 * private JTextField txtCustomerID; private JTextField txtProjectID; private
 * JTextField txtCompletionDate; private JTextField txtSearchQuery;
 * 
 * private JPanel projectPanel; private JPanel buttonPanel;
 * 
 * private ProjectManager projectManager;
 * 
 * public ProjectMenuGUI() { projectManager = new ProjectManager();
 * initialize(); }
 * 
 * private void initialize() { frame = new JFrame("Project Management");
 * frame.setBounds(100, 100, 500, 400);
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setLayout(new
 * BorderLayout());
 * 
 * buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
 * frame.add(buttonPanel, BorderLayout.WEST);
 * 
 * // Add Project Button JButton btnAddProject = new JButton("Add Project");
 * btnAddProject.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { showProjectFields(); }
 * }); buttonPanel.add(btnAddProject);
 * 
 * // Update Project Button JButton btnUpdateProject = new
 * JButton("Update Project"); btnUpdateProject.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { updateProject(); } });
 * buttonPanel.add(btnUpdateProject);
 * 
 * // Delete Project Button JButton btnDeleteProject = new
 * JButton("Delete Project"); btnDeleteProject.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { deleteProject(); } });
 * buttonPanel.add(btnDeleteProject);
 * 
 * // Finalize Project Button JButton btnFinalizeProject = new
 * JButton("Finalize Project"); btnFinalizeProject.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { finalizeProject(); }
 * }); buttonPanel.add(btnFinalizeProject);
 * 
 * // Display All Projects Button JButton btnDisplayProjects = new
 * JButton("Display All Projects"); btnDisplayProjects.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * projectManager.displayAllProjects(); } });
 * buttonPanel.add(btnDisplayProjects);
 * 
 * // Find Incomplete Projects Button JButton btnFindIncompleteProjects = new
 * JButton("Find Incomplete Projects");
 * btnFindIncompleteProjects.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * projectManager.findIncompleteProjects(); } });
 * buttonPanel.add(btnFindIncompleteProjects);
 * 
 * // Find Projects Past Due Date Button JButton btnFindProjectsPastDueDate =
 * new JButton("Find Projects Past Due Date");
 * btnFindProjectsPastDueDate.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * projectManager.findProjectsPastDueDate(); } });
 * buttonPanel.add(btnFindProjectsPastDueDate);
 * 
 * // Search Project by ID or Name JPanel searchPanel = new JPanel(new
 * GridLayout(2, 1, 10, 10)); searchPanel.add(new
 * JLabel("Search Project by ID or Name:")); txtSearchQuery = new JTextField();
 * searchPanel.add(txtSearchQuery); JButton btnFindProject = new
 * JButton("Find Project"); btnFindProject.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * findProjectByIDOrName(); } }); searchPanel.add(btnFindProject);
 * buttonPanel.add(searchPanel);
 * 
 * // Panel for Project Details projectPanel = new JPanel(new GridLayout(0, 2,
 * 10, 10)); frame.add(projectPanel, BorderLayout.CENTER);
 * 
 * frame.setVisible(true); }
 * 
 * private void showProjectFields() { projectPanel.removeAll();
 * projectPanel.setLayout(new GridLayout(0, 2, 10, 10));
 * 
 * projectPanel.add(new JLabel("Project Name:")); txtProjectName = new
 * JTextField(); projectPanel.add(txtProjectName);
 * 
 * projectPanel.add(new JLabel("Building Type:")); txtBuildingType = new
 * JTextField(); projectPanel.add(txtBuildingType);
 * 
 * projectPanel.add(new JLabel("ERF Number:")); txtERFNumber = new JTextField();
 * projectPanel.add(txtERFNumber);
 * 
 * projectPanel.add(new JLabel("Total Fee:")); txtTotalFee = new JTextField();
 * projectPanel.add(txtTotalFee);
 * 
 * projectPanel.add(new JLabel("Total Paid:")); txtTotalPaid = new JTextField();
 * projectPanel.add(txtTotalPaid);
 * 
 * projectPanel.add(new JLabel("Deadline (YYYY-MM-DD):")); txtDeadline = new
 * JTextField(); projectPanel.add(txtDeadline);
 * 
 * projectPanel.add(new JLabel("Architect ID:")); txtArchitectID = new
 * JTextField(); projectPanel.add(txtArchitectID);
 * 
 * projectPanel.add(new JLabel("Contractor ID:")); txtContractorID = new
 * JTextField(); projectPanel.add(txtContractorID);
 * 
 * projectPanel.add(new JLabel("Customer ID:")); txtCustomerID = new
 * JTextField(); projectPanel.add(txtCustomerID);
 * 
 * JButton btnSubmit = new JButton("Submit"); btnSubmit.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { addProject(); } });
 * projectPanel.add(btnSubmit);
 * 
 * projectPanel.revalidate(); projectPanel.repaint(); }
 * 
 * private boolean validateInputs() { if
 * (txtProjectName.getText().trim().isEmpty() ||
 * txtBuildingType.getText().trim().isEmpty() ||
 * txtERFNumber.getText().trim().isEmpty() ||
 * txtTotalFee.getText().trim().isEmpty() ||
 * txtTotalPaid.getText().trim().isEmpty() ||
 * txtDeadline.getText().trim().isEmpty() ||
 * txtArchitectID.getText().trim().isEmpty() ||
 * txtContractorID.getText().trim().isEmpty() ||
 * txtCustomerID.getText().trim().isEmpty()) {
 * JOptionPane.showMessageDialog(frame, "Please fill in all fields.",
 * "Validation Error", JOptionPane.ERROR_MESSAGE); return false; } return true;
 * }
 * 
 * private void addProject() { if (!validateInputs()) return;
 * 
 * try { String projectName = txtProjectName.getText(); String buildingType =
 * txtBuildingType.getText(); String erfNumber = txtERFNumber.getText(); double
 * totalFee = Double.parseDouble(txtTotalFee.getText()); double totalPaid =
 * Double.parseDouble(txtTotalPaid.getText()); Date deadline =
 * Date.valueOf(txtDeadline.getText()); int architectID =
 * Integer.parseInt(txtArchitectID.getText()); int contractorID =
 * Integer.parseInt(txtContractorID.getText()); int customerID =
 * Integer.parseInt(txtCustomerID.getText());
 * 
 * projectManager.addProject(projectName, buildingType, erfNumber, totalFee,
 * totalPaid, deadline, architectID, contractorID, customerID);
 * 
 * // Clear fields after adding txtProjectName.setText("");
 * txtBuildingType.setText(""); txtERFNumber.setText("");
 * txtTotalFee.setText(""); txtTotalPaid.setText(""); txtDeadline.setText("");
 * txtArchitectID.setText(""); txtContractorID.setText("");
 * txtCustomerID.setText("");
 * 
 * } catch (Exception e) { JOptionPane.showMessageDialog(frame,
 * "Error adding project: " + e.getMessage(), "Error",
 * JOptionPane.ERROR_MESSAGE); } }
 * 
 * private void updateProject() { try { int projectID =
 * Integer.parseInt(txtProjectID.getText()); String newProjectName =
 * txtProjectName.getText(); String newBuildingType = txtBuildingType.getText();
 * String newERFNumber = txtERFNumber.getText(); double newTotalFee =
 * Double.parseDouble(txtTotalFee.getText()); double newTotalPaid =
 * Double.parseDouble(txtTotalPaid.getText()); Date newDeadline =
 * Date.valueOf(txtDeadline.getText());
 * 
 * projectManager.updateProject(projectID, newProjectName, newBuildingType,
 * newERFNumber, newTotalFee, newTotalPaid, newDeadline); } catch (Exception e)
 * { JOptionPane.showMessageDialog(frame, "Error updating project: " +
 * e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } }
 * 
 * private void deleteProject() { try { int projectID =
 * Integer.parseInt(txtProjectID.getText());
 * projectManager.deleteProject(projectID); } catch (Exception e) {
 * JOptionPane.showMessageDialog(frame, "Error deleting project: " +
 * e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } }
 * 
 * private void finalizeProject() { try { int projectID =
 * Integer.parseInt(txtProjectID.getText()); Date completionDate =
 * Date.valueOf(txtCompletionDate.getText());
 * projectManager.finalizeProject(projectID, completionDate); } catch (Exception
 * e) { JOptionPane.showMessageDialog(frame, "Error finalizing project: " +
 * e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } }
 * 
 * private void findProjectByIDOrName() { String query =
 * txtSearchQuery.getText(); projectManager.findProjectByIDOrName(query); }
 * 
 * public static void main(String[] args) { SwingUtilities.invokeLater(new
 * Runnable() {
 * 
 * @Override public void run() { new ProjectMenuGUI(); } }); } }
 */