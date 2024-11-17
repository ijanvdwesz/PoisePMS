/*
 * package poisePMS;
 * 
 * import javax.swing.*; import java.awt.*; import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * 
 * public class CustomerMenuGUI {
 * 
 * private CustomerManager customerManager;
 * 
 * public CustomerMenuGUI(CustomerManager customerManager) {
 * this.customerManager = customerManager; createAndShowGUI(); }
 * 
 * private void createAndShowGUI() { // Create the frame JFrame frame = new
 * JFrame("Customer Menu");
 * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); frame.setSize(400,
 * 300); frame.setLayout(new GridLayout(6, 1)); // 6 rows, 1 column
 * 
 * // Create buttons for each action JButton addButton = new
 * JButton("Add Customer"); JButton updateButton = new
 * JButton("Update Customer"); JButton deleteButton = new
 * JButton("Delete Customer"); JButton displayButton = new
 * JButton("Display All Customers"); JButton backButton = new
 * JButton("Back to Main Menu");
 * 
 * // Add buttons to the frame frame.add(addButton); frame.add(updateButton);
 * frame.add(deleteButton); frame.add(displayButton); frame.add(backButton);
 * 
 * // Add action listeners for each button addButton.addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showAddCustomerDialog(); } });
 * 
 * updateButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showUpdateCustomerDialog(); } });
 * 
 * deleteButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * showDeleteCustomerDialog(); } });
 * 
 * displayButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) {
 * customerManager.displayAllCustomers(); } });
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
 * private void showAddCustomerDialog() { JPanel panel = new JPanel(new
 * GridLayout(4, 2)); JTextField nameField = new JTextField(); JTextField
 * telephoneField = new JTextField(); JTextField emailField = new JTextField();
 * JTextField addressField = new JTextField();
 * 
 * panel.add(new JLabel("Name:")); panel.add(nameField); panel.add(new
 * JLabel("Telephone:")); panel.add(telephoneField); panel.add(new
 * JLabel("Email:")); panel.add(emailField); panel.add(new JLabel("Address:"));
 * panel.add(addressField);
 * 
 * int result = JOptionPane.showConfirmDialog(null, panel, "Add Customer",
 * JOptionPane.OK_CANCEL_OPTION);
 * 
 * if (result == JOptionPane.OK_OPTION) { String name = nameField.getText();
 * String telephone = telephoneField.getText(); String email =
 * emailField.getText(); String address = addressField.getText();
 * 
 * customerManager.addCustomer(name, telephone, email, address);
 * JOptionPane.showMessageDialog(null, "Customer added successfully!"); } }
 * 
 * private void showUpdateCustomerDialog() { JPanel panel = new JPanel(new
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
 * int result = JOptionPane.showConfirmDialog(null, panel, "Update Customer",
 * JOptionPane.OK_CANCEL_OPTION);
 * 
 * if (result == JOptionPane.OK_OPTION) { int id =
 * Integer.parseInt(idField.getText()); String name = nameField.getText();
 * String telephone = telephoneField.getText(); String email =
 * emailField.getText(); String address = addressField.getText();
 * 
 * customerManager.updateCustomer(id, name, telephone, email, address);
 * JOptionPane.showMessageDialog(null, "Customer updated successfully!"); } }
 * 
 * private void showDeleteCustomerDialog() { String idStr =
 * JOptionPane.showInputDialog(null, "Enter Customer ID to Delete:"); if (idStr
 * != null && !idStr.trim().isEmpty()) { int id = Integer.parseInt(idStr);
 * customerManager.deleteCustomer(id); JOptionPane.showMessageDialog(null,
 * "Customer deleted successfully!"); } }
 * 
 * public static void main(String[] args) { // Create an instance of
 * CustomerManager for testing CustomerManager customerManager = new
 * CustomerManager(); new CustomerMenuGUI(customerManager); } }
 */