/*
 * package poisePMS;
 * 
 * import javax.swing.*; import java.awt.*; import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * 
 * public class MainMenuGUI {
 * 
 * private ArchitectManager architectManager; private CustomerManager
 * customerManager; private ContractorManager contractorManager; private
 * ProjectManager projectManager;
 * 
 * public MainMenuGUI(ArchitectManager architectManager, CustomerManager
 * customerManager, ContractorManager contractorManager, ProjectManager
 * projectManager) { this.architectManager = architectManager;
 * this.customerManager = customerManager; this.contractorManager =
 * contractorManager; this.projectManager = projectManager;
 * 
 * createAndShowGUI(); }
 * 
 * private void createAndShowGUI() { // Create the main frame JFrame frame = new
 * JFrame("Main Menu"); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * frame.setSize(400, 300); frame.setLayout(new GridLayout(5, 1)); // 5 rows, 1
 * column
 * 
 * // Create buttons for each manager JButton architectMenuButton = new
 * JButton("Architect Menu"); JButton customerMenuButton = new
 * JButton("Customer Menu"); JButton contractorMenuButton = new
 * JButton("Contractor Menu"); JButton projectMenuButton = new
 * JButton("Project Menu"); JButton exitButton = new JButton("Exit");
 * 
 * // Add buttons to the frame frame.add(architectMenuButton);
 * frame.add(customerMenuButton); frame.add(contractorMenuButton);
 * frame.add(projectMenuButton); frame.add(exitButton);
 * 
 * // Add action listeners for each button
 * architectMenuButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { new
 * ArchitectMenuGUI(architectManager); } });
 * 
 * customerMenuButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { // Assuming you will
 * create a similar GUI for CustomerMenu new CustomerMenuGUI(customerManager); }
 * });
 * 
 * contractorMenuButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { // Assuming you will
 * create a similar GUI for ContractorMenu new
 * ContractorMenuGUI(contractorManager); } });
 * 
 * projectMenuButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { // Assuming you will
 * create a similar GUI for ProjectMenu new ProjectMenuGUI(); } });
 * 
 * exitButton.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { frame.dispose(); //
 * Close the application } });
 * 
 * // Display the frame frame.setVisible(true); }
 * 
 * public static void main(String[] args) { // Assuming all manager classes are
 * already instantiated ArchitectManager architectManager = new
 * ArchitectManager(); CustomerManager customerManager = new CustomerManager();
 * ContractorManager contractorManager = new ContractorManager(); ProjectManager
 * projectManager = new ProjectManager();
 * 
 * new MainMenuGUI(architectManager, customerManager, contractorManager,
 * projectManager); } }
 */