// =============================================
// 🏀 BOUNCING BALL - FULL LINE BY LINE EXPLAINED
// =============================================

// 📦 IMPORT STATEMENTS - WHAT EACH ONE ACTUALLY DOES
// These are pre-built tools from Java that we borrow so we don't have to write everything from scratch:

// JFrame creates the actual OS window that appears on your desktop
import javax.swing.JFrame;

// JPanel is the blank canvas area inside the window where we can draw things
import javax.swing.JPanel;

// Timer is our game clock that ticks on an exact schedule
import javax.swing.Timer;
// SwingUtilities is helper class for running GUI code correctly
import javax.swing.SwingUtilities;

// Color gives us standard colours we can draw with
import java.awt.Color;

// Dimension stores width + height values for sizes
import java.awt.Dimension;

// Graphics is our digital paintbrush that can draw shapes on screen
import java.awt.Graphics;

// ActionEvent is the message that gets sent every time our timer ticks
import java.awt.event.ActionEvent;

// ActionListener listens for those timer ticks and runs code when they happen
import java.awt.event.ActionListener;


// 👋 MAIN PROGRAM CLASS
// We are making a class called BBall that IS a JPanel (it extends JPanel)
// This means our entire program IS a drawing canvas
public class BBall extends JPanel {

    // 🔧 CONSTANTS - THESE VALUES NEVER CHANGE WHILE RUNNING
    // 'final' means you can't accidentally change these while the program is running
    private static final int WIDTH     = 800;  // Window width in pixels
    private static final int HEIGHT    = 600;  // Window height in pixels
    private static final int BALL_SIZE = 50;   // Diameter of our ball


    // 🏀 LIVE GAME STATE - THESE VALUES CHANGE EVERY SINGLE FRAME
    private int x = 0;          // X coordinate: ball's left edge position
    private int y = 0;          // Y coordinate: ball's top edge position
    private int xSpeed = 5;     // How many pixels ball moves horizontally per tick
    private int ySpeed = 5;     // How many pixels ball moves vertically per tick


    // 🚀 CONSTRUCTOR
    // This runs EXACTLY ONCE when the program first starts
    public BBall() {

        // ⏱️ OUR GAME LOOP - THIS IS THE HEART OF EVERY GAME EVER MADE
        // First parameter: 20 = wait 20 milliseconds between ticks = 50 FPS (1000 / 20 = 50)
        // Second parameter: what code should run every single tick
        Timer timer = new Timer(20, new ActionListener() {

            // This method will automatically run 50 TIMES EVERY SECOND!
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();     // Step 1: Calculate new ball position
                repaint();      // Step 2: Redraw everything on screen
            }
        });

        // Start the timer! Game loop is now running forever.
        timer.start();
    }


    // 🏃 MOVE BALL LOGIC
    // Runs 50x/second. Updates position and checks for wall collisions.
    private void moveBall() {

        // Move the ball: add speed to current position
        x = x + xSpeed;
        y = y + ySpeed;

        // 🚧 LEFT / RIGHT WALL COLLISION CHECK
        // If ball hits left edge OR hits right edge
        if (x <= 0 || x >= WIDTH - BALL_SIZE) {
            // Flip direction: multiply speed by -1
            xSpeed = -xSpeed;
        }

        // 🚧 TOP / BOTTOM WALL COLLISION CHECK
        // If ball hits top edge OR hits bottom edge
        if (y <= 0 || y >= HEIGHT - BALL_SIZE) {
            // Flip direction: multiply speed by -1
            ySpeed = -ySpeed;
        }
    }


    // 🎨 DRAWING CODE
    // Runs 50x/second. Automatically called when we run repaint()
    @Override
    protected void paintComponent(Graphics g) {

        // First erase entire screen (draws background colour over everything)
        super.paintComponent(g);

        // Set our paintbrush color to RED
        g.setColor(Color.RED);

        // Draw the ball!
        // Parameters: x position, y position, width, height
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
    }


    // 📏 TELL WINDOW WHAT SIZE WE WANT
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }


    // 🎬 PROGRAM ENTRY POINT
    // This is where Java starts running the program
    public static void main(String[] args) {

        // Swing rule: always run GUI code on the Swing thread
        SwingUtilities.invokeLater(() -> {

            // Create our window frame
            JFrame frame = new JFrame("Bouncing Ball");

            // Make program close when you click the X button
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Add our BBall canvas into the window
            frame.add(new BBall());

            // Resize window to exactly fit our canvas
            frame.pack();

            // Open window exactly in the center of the screen
            frame.setLocationRelativeTo(null);

            // Make window visible! Now you can see everything.
            frame.setVisible(true);
        });
    }
}