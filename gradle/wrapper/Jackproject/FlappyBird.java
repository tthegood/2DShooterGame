import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlappyBird extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private int PIPE_WIDTH = 50;
    private int MIN_GAP_WIDTH = 100;

    private JPanel gamePanel;
    private Timer timer;
    private int birdY;
    private int velocity;
    private boolean isGameOver;
    private int score;

    private JButton startButton;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    private int pipeX;
    private int pipe1Height;
    private int pipe2Height;
    private int gapY;

    public FlappyBird() {
        setTitle("Flappy Bird");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        startButton = new JButton("Start");
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(5);
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(10);
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDifficulty(15);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void startGame() {
        startButton.setEnabled(false);
        easyButton.setEnabled(false);
        mediumButton.setEnabled(false);
        hardButton.setEnabled(false);

        birdY = HEIGHT / 2;
        velocity = 0;
        isGameOver = false;
        score = 0;

        pipeX = WIDTH;
        pipe1Height = generateRandomPipeHeight();
        pipe2Height = generateRandomPipeHeight();
        gapY = generateRandomGapY();

        gamePanel.requestFocus();
        gamePanel.addKeyListener(new BirdKeyListener());

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                gamePanel.repaint();
            }
        });

        timer.start();
    }

    private void setDifficulty(int pipeGap) {
        MIN_GAP_WIDTH = pipeGap;
    }

    private int generateRandomPipeHeight() {
        return (int) (Math.random() * (HEIGHT / 2));
    }

    private int generateRandomGapY() {
        return (int) (Math.random() * (HEIGHT - MIN_GAP_WIDTH));
    }

    private void updateGame() {
        if (!isGameOver) {
            birdY += velocity;
            velocity += 1;

            if (birdY >= HEIGHT) {
                gameOver();
            }

            pipeX -= 5;

            if (pipeX + PIPE_WIDTH < 0) {
                pipeX = WIDTH;
                pipe1Height = generateRandomPipeHeight();
                pipe2Height = generateRandomPipeHeight();
                gapY = generateRandomGapY();
                score++;
            }

            int birdRight = WIDTH / 2;
            int birdLeft = birdRight - 20;
            int birdTop = birdY - 10;
            int birdBottom = birdY + 10;

            int pipeLeft = pipeX;
            int pipeRight = pipeX + PIPE_WIDTH;
            int pipe1Top = 0;
            int pipe1Bottom = pipe1Height;
            int pipe2Top = gapY + MIN_GAP_WIDTH;
            int pipe2Bottom = HEIGHT;

            if ((birdRight > pipeLeft && birdLeft < pipeRight) &&
                    ((birdTop < pipe1Bottom) || (birdBottom > pipe2Top))) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        isGameOver = true;
        timer.stop();

        int result = JOptionPane.showConfirmDialog(this, "Game Over! Score: " + score +
                "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        gamePanel.removeKeyListener(gamePanel.getKeyListeners()[0]);
        gamePanel.requestFocusInWindow();

        startButton.setEnabled(true);
        easyButton.setEnabled(true);
        mediumButton.setEnabled(true);
        hardButton.setEnabled(true);
    }

    private void drawGame(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.ORANGE);
        g.fillRect(WIDTH / 2 - 20, birdY - 10, 40, 20);

        g.setColor(Color.GREEN);
        g.fillRect(pipeX, 0, PIPE_WIDTH, pipe1Height);
        g.fillRect(pipeX, gapY + MIN_GAP_WIDTH, PIPE_WIDTH, pipe2Height);
    }

    private class BirdKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                velocity = -12;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlappyBird();
            }
        });
    }
}
