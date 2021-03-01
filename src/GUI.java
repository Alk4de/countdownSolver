import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {


    private MathWorker mathWorker;
    private JTextArea result;

    public GUI() {
        this.mathWorker = new MathWorker(this);


        this.setTitle("Countdown Solver");
        this.setLayout(new BorderLayout());
        initialize();

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));
        this.pack();
        this.setVisible(true);
    }


    public void showResult(String result) {
        this.result.setText(result);
    }

    /**
     * Initialize the graphical interface
     */
    private void initialize() {
        JButton button = new JButton("New calculation");
        button.addActionListener(ae -> this.askForInput());

        this.result = new JTextArea("");
        this.result.setEditable(false);
        this.add(result, BorderLayout.CENTER);
        this.add(button, BorderLayout.NORTH);

        askForInput();
    }

    /**
     * Ask user for input (maths round)
     */
    private void askForInput() {

        JPanel numbersPanel = new JPanel();
        JPanel fullPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(2, 3, 5, 5));
        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(numbersPanel, BorderLayout.CENTER);

        ArrayList<JTextField> inputs = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            inputs.add(new JTextField(5));
            numbersPanel.add(inputs.get(i));
        }

        // Special field for the target
        JTextField target = new JTextField(10);
        fullPanel.add(new JLabel("Target:"), BorderLayout.NORTH);
        fullPanel.add(target, BorderLayout.NORTH);

        // Ask the user
        int result = JOptionPane.showConfirmDialog(null, fullPanel,
                "Please Enter Target and Numbers", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (JTextField input: inputs) {
                numbers.add(Integer.parseInt(input.getText()));
            }

            this.mathWorker.setNumbers(numbers, Integer.parseInt(target.getText()));
            this.mathWorker.run();
            this.mathWorker = new MathWorker(this);
        }
    }


    /**
     * Send an error message to the user
     * @param message message to the user
     */
    public void sendErrorMessage(String message) {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
