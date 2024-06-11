import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Window implements ActionListener {
    private JFrame mainFrame;
    private JTextField displayPanelText;
    private JPanel bodyPanel;
    private JButton[] buttons = new JButton[24];
    static final Color NAVY_BLUE = new Color(0, 45, 98);
    static final Color GRAY = new Color(240, 240, 240);
    static final Color LIGHT_GRAY = new Color(245, 245, 245);


    public Window() {
        setupMainFrame();
        setupDisplayPanel();
        setupButtons();

        mainFrame.add(displayPanelText, BorderLayout.NORTH);
        mainFrame.add(bodyPanel, BorderLayout.CENTER);
    }

    private void setupButtons() {
        bodyPanel = new JPanel(new GridLayout(6,4,1,1));
        bodyPanel.setBackground(LIGHT_GRAY);

        for (int i = 0; i < buttons.length; i++) {
            JButton jButton = new JButton();
            buttons[i] = jButton;
            buttons[i].setPreferredSize(new Dimension(90,60));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(GRAY);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            buttons[i].addActionListener(this);
            bodyPanel.add(buttons[i]);
        }

        buttons[0].setText("CLR");
        buttons[1].setText("(");
        buttons[2].setText(")");
        buttons[3].setText("DEL");
        buttons[4].setText("!");
        buttons[5].setText("π");
        buttons[6].setText("%");
        buttons[7].setText("÷");
        buttons[8].setText("7");
        buttons[8].setBackground(Color.white);
        buttons[9].setText("8");
        buttons[9].setBackground(Color.white);
        buttons[10].setText("9");
        buttons[10].setBackground(Color.white);
        buttons[11].setText("×");
        buttons[12].setText("4");
        buttons[12].setBackground(Color.white);
        buttons[13].setText("5");
        buttons[13].setBackground(Color.white);
        buttons[14].setText("6");
        buttons[14].setBackground(Color.white);
        buttons[15].setText("-");
        buttons[16].setText("1");
        buttons[16].setBackground(Color.white);
        buttons[17].setText("2");
        buttons[17].setBackground(Color.white);
        buttons[18].setText("3");
        buttons[18].setBackground(Color.white);
        buttons[19].setText("+");
        buttons[20].setText("mod");
        buttons[21].setText("0");
        buttons[21].setBackground(Color.white);
        buttons[22].setText(".");
        buttons[22].setFont(new Font("Arial", Font.PLAIN, 30));
        buttons[23].setText("=");
        buttons[23].setBackground(NAVY_BLUE);
        buttons[23].setForeground(Color.white);
    }

    private void setupDisplayPanel() {
        displayPanelText = new JTextField("");
        displayPanelText.setPreferredSize(new Dimension(0,100));
        displayPanelText.setFont(new Font("Arial", Font.BOLD, 48));
        displayPanelText.setBackground(LIGHT_GRAY);
        displayPanelText.setBorder(null);
    }

    private void setupMainFrame() {
        mainFrame = new JFrame("Calculator");
        mainFrame.setIconImage(new ImageIcon("logo2.png").getImage());
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(LIGHT_GRAY);
        mainFrame.setSize(375,470);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().equals("CLR")) {
            displayPanelText.setText("");
        }
        else if (button.getText().equals("DEL")) {
            String text = displayPanelText.getText();
            if (text.isEmpty()) return;
            displayPanelText.setText(text.substring(0, text.length()-1));
        }
        else if (button.getText().equals("=")) {
            double result = 0;
            try {
                result = new Calculation().evaluate(displayPanelText.getText());
                displayPanelText.setText(new DecimalFormat("#.######").format(result));
            } catch (Exception ex) {
                displayPanelText.setText(ex.getMessage());
            }
        }
        else {
            displayPanelText.setText(displayPanelText.getText() + button.getText());
        }
    }

    public void show() {
        mainFrame.setVisible(true);
    }
}
