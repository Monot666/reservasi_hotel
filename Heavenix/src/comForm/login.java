import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameError;
    private JLabel passwordError;

    public login() {
        setTitle("Login");
        setSize(1175, 674);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(40, 40, 80)); // Bisa pakai gambar juga
        add(backgroundPanel, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel(null); // Gunakan layout null untuk kontrol penuh posisi
        loginPanel.setPreferredSize(new Dimension(420, 270));
        loginPanel.setBackground(new Color(255, 255, 255, 40));
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        backgroundPanel.add(loginPanel);

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setBounds(160, 10, 120, 30);
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginTitle.setForeground(Color.WHITE);
        loginPanel.add(loginTitle);

        // Username error
        usernameError = new JLabel();
        usernameError.setBounds(110, 50, 200, 15);
        usernameError.setFont(new Font("Arial", Font.PLAIN, 10));
        usernameError.setForeground(Color.RED);
        loginPanel.add(usernameError);

        // Username field
        usernameField = new JTextField();
        usernameField.setBounds(110, 65, 200, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(new LineBorder(Color.GRAY, 1));
        loginPanel.add(usernameField);

        // Password error
        passwordError = new JLabel();
        passwordError.setBounds(110, 105, 200, 15);
        passwordError.setFont(new Font("Arial", Font.PLAIN, 10));
        passwordError.setForeground(Color.RED);
        loginPanel.add(passwordError);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 120, 200, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(new LineBorder(Color.GRAY, 1));
        loginPanel.add(passwordField);

        // Tombol Login
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(160, 180, 100, 30);
        loginBtn.setBackground(new Color(0, 204, 0));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginPanel.add(loginBtn);

        loginBtn.addActionListener(e -> checkLogin());

        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean userCorrect = username.equals("admin");
        boolean passCorrect = password.equals("12345");

        // Reset border dan error
        usernameField.setBorder(new LineBorder(Color.GRAY, 1));
        passwordField.setBorder(new LineBorder(Color.GRAY, 1));
        usernameError.setText("");
        passwordError.setText("");

        if (userCorrect && passCorrect) {
            JOptionPane.showMessageDialog(this, "Login berhasil!");
        } else if (!userCorrect && passCorrect) {
            usernameError.setText("please enter the correct Username");
            usernameField.setBorder(new LineBorder(Color.RED, 2));
        } else if (userCorrect && !passCorrect) {
            passwordError.setText("please enter the correct Password");
            passwordField.setBorder(new LineBorder(Color.RED, 2));
        } else {
            usernameError.setText("Username dan Password anda salah");
            passwordError.setText("Username dan Password anda salah");
            usernameField.setBorder(new LineBorder(Color.RED, 2));
            passwordField.setBorder(new LineBorder(Color.RED, 2));
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
