import javax.swing.*;
import java.awt.*;


/**
 * An interactive program to show a koch curve with different levels of fractal patterns in the shape of a snowflake.
 *
 * @author Casey Cotton COSC326
 *
 */
public class KochSnowflakeApp {

    /**
     * Creates an interactive GUI component where you can increase and decrease the order of a koch snowflake.
     *
     * @param args no arguments.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Koch Snowflake Interactive");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(350, 300));
        frame.getContentPane().add(new SnowflakePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
