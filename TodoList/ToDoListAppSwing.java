import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
    String title;
    String description;
    String dueDate;
    boolean isComplete;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = false;
    }
}

public class ToDoListAppSwing extends JFrame {
    private ArrayList<Task> taskList;
    private DefaultListModel<String> taskListModel;

    private JList<String> taskJList;
    private JButton addButton;
    private JButton markCompleteButton;
    private JButton removeButton;

    public ToDoListAppSwing() {
        // Use Nimbus Look and Feel for a modern appearance
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        taskList = new ArrayList<>();
        taskListModel = new DefaultListModel<>();

        taskJList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskJList);

        addButton = new JButton("Add Task");
        markCompleteButton = new JButton("Mark as Complete");
        removeButton = new JButton("Remove Task");

        // Set custom colors
        addButton.setBackground(new Color(119, 158, 203)); // Light Blue
        markCompleteButton.setBackground(new Color(245, 176, 65)); // Yellow
        removeButton.setBackground(new Color(233, 87, 63)); // Red

        addButton.setForeground(Color.BLACK);
        markCompleteButton.setForeground(Color.BLACK);
        removeButton.setForeground(Color.BLACK);

        addButton.addActionListener(e -> addTask());
        markCompleteButton.addActionListener(e -> markTaskAsComplete());
        removeButton.addActionListener(e -> removeTask());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(markCompleteButton);
        buttonPanel.add(removeButton);

        // Set custom background color for the list
        taskJList.setBackground(new Color(231, 231, 231)); // Light Gray

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set custom background color for the JFrame
        getContentPane().setBackground(new Color(158, 219, 179)); // Mint Green

        setTitle("To-Do List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        updateTaskList();
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog(this, "Enter task title:");
        String description = JOptionPane.showInputDialog(this, "Enter task description:");
        String dueDate = JOptionPane.showInputDialog(this, "Enter due date:");

        Task newTask = new Task(title, description, dueDate);
        taskList.add(newTask);

        updateTaskList();
    }

    private void markTaskAsComplete() {
        int selectedIndex = taskJList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskList.get(selectedIndex).isComplete = true;
            updateTaskList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as complete.");
        }
    }

    private void removeTask() {
        int selectedIndex = taskJList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskList.remove(selectedIndex);
            updateTaskList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to remove.");
        }
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (Task task : taskList) {
            taskListModel.addElement(task.title + " - " + (task.isComplete ? "Complete" : "Incomplete"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListAppSwing().setVisible(true));
    }
}
