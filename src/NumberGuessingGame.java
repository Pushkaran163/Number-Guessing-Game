import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener{
    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 100;

    private Random random = new Random();
    private int targetNumber;
    private int attempts;

    private JTextField guessInput;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame(){
        targetNumber = random.nextInt(RANGE_END - RANGE_START + 1) + RANGE_START;
        attempts = 0;

        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Guess a Number between " + RANGE_START + " and " + RANGE_END));
        guessInput = new JTextField();
        add(guessInput);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
        add(attemptsLabel);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String guessText = guessInput.getText();
        try{
            int guess = Integer.parseInt(guessText);
            attempts++;

            if(guess < targetNumber){
                feedbackLabel.setText("Too Low!");
            }else if(guess > targetNumber){
                feedbackLabel.setText("Too High!");
            }else{
                feedbackLabel.setText("Congratulations! Youu've guessed the right number in " + attempts + "attempts");
                guessInput.setEnabled(false);
                return;
            }
            
            attemptsLabel.setText("Attempts remaining: " + (MAX_ATTEMPTS - attempts));

            if(attempts >= MAX_ATTEMPTS){
                feedbackLabel.setText("Game Over! The correct number was " + targetNumber);
                guessInput.setEnabled(false);
            }
        }catch(NumberFormatException ex){
            feedbackLabel.setText("Please enter a valid number");
        }
    }
    public static void main(String[] ars){
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}