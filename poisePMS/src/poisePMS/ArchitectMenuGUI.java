/*
 * package poisePMS;
 * 
 * import javax.swing.*; import java.awt.*; import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * 
 * public class ArchitectMenuGUI {
 * 
 * private ArchitectManager architectManager;
 * 
 * public ArchitectMenuGUI(ArchitectManager architectManager) {
 * this.architectManager = architectManager; createAndShowGUI(); }
 * 
 * private void createAndShowGUI() { // Create the frame JFrame frame = new
 * JFrame("Architect Menu");
 * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); frame.setSize(400,
 * 300); frame.setLayout(new GridLayout(6, 1)); // 6 rows, 1 column
 * 
 * // Create buttons for each action JButton addButton = new
 * JButton("Add Architect"); JButton updateButton = new
 * JButton("Update Architect"); JButton deleteButton = new
 * JButton("Delete Architect"); JButton displayButton = new
 * JButton("Display All Architects"); JButton backButton = new
 * JButton("Back to Main Menu");
 * 
 * // Add buttons to the frame frame.add(addButton); frame.add(updateButton);
 * frame.add(deleteButton); frame.add(displayButton); frame.add(backButton);
 * 
 * // Add action listeners for each button addButton.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showAddArchitectDialog(); } });
 * 
 * updateButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showUpdateArchitectDialog(); } });
 * 
 * deleteButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showDeleteArchitectDialog(); } });
 * 
 * displayButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * architectManager.displayAllArchitects(); } });
 * 
 * backButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { frame.dispose(); //
 * Close the current window new MainMenuGUI(new ArchitectManager(), // Replace
 * with actual manager instances new CustomerManager(), new ContractorManager(),
 * new ProjectManager()); } });
 * 
 * // Display the frame frame.setVisible(true); }
 * 
 * private void showAddArchitectDialog() { JPanel panel = new JPanel(new
 * GridLayout(4, 2)); JTextField nameField = new JTextField(); JTextField
 * telephoneField = new JTextField(); JTextField emailField = new JTextField();
 * JTextField addressField = new JTextField();
 * 
 * panel.add(new JLabel("Name:")); panel.add(nameField); panel.add(new
 * JLabel("Telephone:")); panel.add(telephoneField); panel.add(new
 * JLabel("Email:")); panel.add(emailField); panel.add(new JLabel("Address:"));
 * panel.add(addressField);
 * 
 * int result = JOptionPane.showConfirmDialog(null, panel, "Add Architect",
 * JOptionPane.OK_CANCEL_OPTION);
 * 
 * if (result == JOptionPane.OK_OPTION) { String name = nameField.getText();
 * String telephone = telephoneField.getText(); String email =
 * emailField.getText(); String address = addressField.getText();
 * 
 * architectManager.addArchitect(name, telephone, email, address);
 * JOptionPane.showMessageDialog(null, "Architect added successfully!"); } }
 * 
 * private void showUpdateArchitectDialog() { JPanel panel = new JPanel(new
 * GridLayout(5, 2)); JTextField idField = new JTextField(); JTextField
 * nameField = new JTextField(); JTextField telephoneField = new JTextField();
 * JTextField emailField = new JTextField(); JTextField addressField = new
 * JTextField();
 * 
 * panel.add(new JLabel("ID:")); panel.add(idField); panel.add(new
 * JLabel("Name:")); panel.add(nameField); panel.add(new JLabel("Telephone:"));
 * panel.add(telephoneField); panel.add(new JLabel("Email:"));
 * panel.add(emailField); panel.add(new JLabel("Address:"));
 * panel.add(addressField);
 * 
 * int result = JOptionPane.showConfirmDialog(null, panel, "Update Architect",
 * JOptionPane.OK_CANCEL_OPTION);
 * 
 * if (result == JOptionPane.OK_OPTION) { int id =
 * Integer.parseInt(idField.getText()); String name = nameField.getText();
 * String telephone = telephoneField.getText(); String email =
 * emailField.getText(); String address = addressField.getText();
 * 
 * architectManager.updateArchitect(id, name, telephone, email, address);
 * JOptionPane.showMessageDialog(null, "Architect updated successfully!"); } }
 * 
 * private void showDeleteArchitectDialog() { String idStr =
 * JOptionPane.showInputDialog(null, "Enter Architect ID to Delete:"); if (idStr
 * != null && !idStr.trim().isEmpty()) { int id = Integer.parseInt(idStr);
 * architectManager.deleteArchitect(id); JOptionPane.showMessageDialog(null,
 * "Architect deleted successfully!"); } }
 * 
 * public static void main(String[] args) { // Create an instance of
 * ArchitectManager for testing ArchitectManager architectManager = new
 * ArchitectManager(); new ArchitectMenuGUI(architectManager); } }
 */