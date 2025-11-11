import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public ModernCalculator() {
        setTitle("Modern Calculator");
        setSize(360, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(40, 40, 40));

        textField = new JTextField();
        textField.setBounds(30, 40, 290, 50);
        textField.setFont(new Font("Consolas", Font.BOLD, 24));
        textField.setBackground(new Color(30, 30, 30));
        textField.setForeground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
        add(textField);

        String[] buttonLabels = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};

        JPanel panel = new JPanel();
        panel.setBounds(30, 110, 290, 290);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(40, 40, 40));
        add(panel);

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            panel.add(button);
        }

        JButton clearBtn = createButton("C");
        clearBtn.setBackground(new Color(180, 50, 50));
        clearBtn.setBounds(30, 420, 290, 40);
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(90, 90, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 60, 60));
            }
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 != 0) result = num1 / num2;
                        else { textField.setText("Error"); return; }
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            } catch (Exception ex) { textField.setText("Error"); }
        } else {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = command.charAt(0);
                textField.setText("");
            } catch (Exception ex) { textField.setText("Error"); }
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception ignored) {}
        new ModernCalculator();
    }
}
