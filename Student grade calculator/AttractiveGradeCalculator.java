import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttractiveGradeCalculator extends JFrame {

    private JTextField txtMathematics, txtScience, txtEnglish, txtTotal, txtResult;
    private JButton btnCalculate, btnReset;

    public AttractiveGradeCalculator() {
        setTitle("Attractive Student Grade Calculator");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToFrame();
        attachListeners();
    }

    private void initComponents() {
        Font font = new Font("Arial", Font.PLAIN, 14);

        txtMathematics = new JTextField(10);
        txtScience = new JTextField(10);
        txtEnglish = new JTextField(10);
        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);
        txtResult = new JTextField(10);
        txtResult.setEditable(false);

        btnCalculate = new JButton("Calculate");
        btnCalculate.setFont(font);
        btnCalculate.setBackground(new Color(46, 204, 113)); // Emerald Green
        btnCalculate.setForeground(Color.WHITE);

        btnReset = new JButton("Reset");
        btnReset.setFont(font);
        btnReset.setBackground(new Color(231, 76, 60)); // Alizarin Red
        btnReset.setForeground(Color.WHITE);
    }

    private void addComponentsToFrame() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(createLabel("Mathematics:"));
        inputPanel.add(txtMathematics);

        inputPanel.add(createLabel("Science:"));
        inputPanel.add(txtScience);

        inputPanel.add(createLabel("English:"));
        inputPanel.add(txtEnglish);

        inputPanel.add(createLabel("Total Marks:"));
        inputPanel.add(txtTotal);

        inputPanel.add(createLabel("Average:"));
        inputPanel.add(txtResult);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnReset);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void attachListeners() {
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    private void calculateAverage() {
        try {
            double Mathematics = Double.parseDouble(txtMathematics.getText());
            double science = Double.parseDouble(txtScience.getText());
            double english = Double.parseDouble(txtEnglish.getText());

            double total = Mathematics + science + english;
            double average = total / 3;

            txtTotal.setText(String.format("%.2f", total));
            txtResult.setText(String.format("%.2f", average));

            // Assign a grade based on the average (you can customize this part)
            if (average >= 90) {
                JOptionPane.showMessageDialog(this, "Grade: A");
            } else if (average >= 80) {
                JOptionPane.showMessageDialog(this, "Grade: B");
            } else if (average >= 70) {
                JOptionPane.showMessageDialog(this, "Grade: C");
            } else if (average >= 60) {
                JOptionPane.showMessageDialog(this, "Grade: D");
            } else {
                JOptionPane.showMessageDialog(this, "Grade: F");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    private void resetFields() {
        txtMathematics.setText("");
        txtScience.setText("");
        txtEnglish.setText("");
        txtTotal.setText("");
        txtResult.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AttractiveGradeCalculator().setVisible(true);
            }
        });
    }
}

