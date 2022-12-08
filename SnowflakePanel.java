import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnowflakePanel extends JPanel {

    /** Counter to store the current order of the koch curve. */
    private int count;
    /** A JPanel which holds buttons to control the GUI. */
    private final JPanel controlPanel;
    /** A JPanel with the sole purpose of displaying the order number. */
    private final JPanel displayPanel;
    /** The JPanel used to draw the snowflake. */
    private final DrawingPanel drawPanel;
    /** Buttons used for increasing or decreasing the level of fractilisation on the snowflake. */
    private final JButton[] buttons = {new JButton("+1"), new JButton("-1") };
    /** Label for the increase/decrease buttons. */
    private final JLabel lblIncreaseDecrease;
    /** label for the order number. */
    private JLabel lblOrderNumber;
    /** Used to store the width of the drawing panel in pixels. */
    private int x;
    /** Used to store the height of the drawing panel in pixels. */
    private int y;
    /**
     * This object will be used to draw the three lines on the drawing panel to represent a triangle and thence,
     * the level of fractilisation.
     */
    private Triangle triangle;

    /**
     * Creates a Panel to instantiate all the components used to construct the GUI application.
     */
    public SnowflakePanel() {
        count = 1;
        ButtonListener listener = new ButtonListener();
        displayPanel = new JPanel();
        controlPanel = new JPanel();
        drawPanel = new DrawingPanel();
        lblIncreaseDecrease = new JLabel();
        lblIncreaseDecrease.setText("Increase/Decrease Order:");
        lblOrderNumber = new JLabel();
        lblOrderNumber = new JLabel();
        lblOrderNumber.setText("Order Number: " + count);
        displayPanel.add(lblOrderNumber);
        controlPanel.add(lblIncreaseDecrease);

        for (JButton item : buttons) {
            item.addActionListener(listener);
            controlPanel.add(item);
        }

        displayPanel.setMaximumSize(new Dimension(400,40));
        controlPanel.setMaximumSize(new Dimension(400,40));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(displayPanel);
        add(drawPanel);
        add(controlPanel);

    }

    /**
     * The panel used to display the snowflake.
     */
    private class DrawingPanel extends JPanel {

        /** Sets the colour and the initial size of the window. */
        public DrawingPanel() {
            setPreferredSize(new Dimension(400, 400));
            setBackground(Color.lightGray);
        }

        /**
         * The koch snowflake is constructed here with the width and height of the drawing panel being given to
         * triangle. This is so the snowflake can be relative to the window size, and it will be repainted every time
         * the window resizes. The triangle will also be given the degree of which to recursively fractalise each side
         * of the triangle.
         *
         * @param g a graphics component.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            triangle = new Triangle();
                    x = drawPanel.getWidth();
                    y = drawPanel.getHeight();
                    triangle.setX(x);
                    triangle.setY(y);
                    triangle.setOrder(count);
                    triangle.display(g);
                    //System.out.println("width is: " + x);
                    //System.out.println("Height is: " + y);
            //System.out.println("The current order is: " + count);
            lblOrderNumber.setText("Order Number: " + count);
                }
            }

    /**
     * An event listener used to check whether the "+1" or "-1" button has been pressed which it will then change the
     * count number accordingly.
      */
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();

            if (button.getText().equals("+1") && count <8) {
                //System.out.println("+1 pressed");
                count++;
            }
            if (button.getText().equals("-1") && count > 1) {
                //System.out.println("-1 pressed");
                count--;
            }
            repaint();
        }
    }
}

