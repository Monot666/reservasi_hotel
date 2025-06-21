import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.border.EmptyBorder;


public class screen extends JFrame {
    private JLabel hotelImageLabel;
    private JProgressBar progressBar;
    private JLabel progressPercentage;
    private JLabel reservationIdLabel;
    private Timer loadingTimer;
    private int progress = 0;

    public screen() {
        // Setup frame
        setTitle("Hotel Reservation Processing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        // Image panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        
        try {
            // Load default image
            ImageIcon originalIcon = new ImageIcon(ImageIO.read(new File("src/images/heavenixScreen.jpg")));
            Image scaledImage = originalIcon.getImage().getScaledInstance(760, 300, Image.SCALE_SMOOTH);
            hotelImageLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            // If image not found, use placeholder
            hotelImageLabel = new JLabel("HEAVENIX HOTEL");
            hotelImageLabel.setHorizontalAlignment(JLabel.CENTER);
            hotelImageLabel.setPreferredSize(new Dimension(760, 300));
            hotelImageLabel.setBackground(new Color(240, 240, 240));
            hotelImageLabel.setOpaque(true);
        }
        
        imagePanel.add(hotelImageLabel, BorderLayout.CENTER);
        
        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Processing Your Reservation");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Tunggu sebentar untuk memuat sistem");
        subtitleLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        
        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(700, 10));
        progressBar.setBackground(new Color(224, 224, 224));
        progressBar.setForeground(new Color(255, 123, 0));
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        // Progress percentage
        progressPercentage = new JLabel("0% Complete");
        progressPercentage.setFont(new Font("Poppins", Font.PLAIN, 14));
        progressPercentage.setAlignmentX(Component.RIGHT_ALIGNMENT);
        progressPercentage.setBorder(new EmptyBorder(5, 0, 20, 0));
        
        // Reservation details
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBackground(Color.WHITE);
        
        reservationIdLabel = new JLabel("Reservation ID: Admin");
        reservationIdLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        
        detailsPanel.add(reservationIdLabel, BorderLayout.WEST);
        detailsPanel.add(progressPercentage, BorderLayout.EAST);
        
        // Add components to content panel
        contentPanel.add(titleLabel);
        contentPanel.add(subtitleLabel);
        contentPanel.add(progressBar);
        contentPanel.add(detailsPanel);
        
        // Add panels to main panel
        mainPanel.add(imagePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Start loading simulation
        startLoading();
    }
    
    private void startLoading() {
        loadingTimer = new Timer(100, e -> {
            progress += (int)(Math.random() * 5) + 1;
            if (progress >= 100) {
                progress = 100;
                loadingTimer.stop();
                showConfirmation();
            }
            progressBar.setValue(progress);
            progressPercentage.setText(progress + "% Complete");
        });
        loadingTimer.start();
    }
    
    private void showConfirmation() {
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.Y_AXIS));
        confirmationPanel.setBackground(Color.WHITE);
        
        JLabel confirmationTitle = new JLabel("Berhasil!");
        confirmationTitle.setFont(new Font("Poppins", Font.BOLD, 24));
        confirmationTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmationTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        
        JLabel confirmationSubtitle = new JLabel("Terima ksih telah menggunakan sistem kami.");
        confirmationSubtitle.setFont(new Font("Poppins", Font.PLAIN, 16));
        confirmationSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmationSubtitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        confirmationPanel.add(confirmationTitle);
        confirmationPanel.add(confirmationSubtitle);
        
        // Replace content
        ((JPanel)getContentPane().getComponent(0)).remove(1);
        ((JPanel)getContentPane().getComponent(0)).add(confirmationPanel, BorderLayout.CENTER);
        revalidate();
        repaint();


    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            screen loadingScreen = new screen();
            loadingScreen.setVisible(true);
        });
    }
}
