import java.awt.*;
import java.lang.Math;

public class Triangle {
    /** Used to store the width of the drawing panel in pixels. */
    private int x;
    /** Used to store the height of the drawing panel in pixels. */
    private int y;
    /** Counter to store the current order of the koch curve. */
    private int order;

    /**
     * This method is used to initially draw each side of the triangle. It will then call upon the recursive function
     * "smallTriangle" to add the fractal patterns.
     *
     * @param g a graphics component.
     */
    public void display(Graphics g) {

        // the first value in the array is always the x coordinate.
        int[] a = {(int) (x * 0.33), (int) (y * 0.66)}; //left most vertex.
        int[] c = { (int) (x * 0.66), (int) (y * 0.66)}; // right most vertex.
        int[] b = { x/2, (y - (y-a[1]) -(int) (Math.sqrt(3)/2 * (c[0]-a[0])))}; //top most vertex.

        smallTriangle(g, order, a[0], a[1], b[0], b[1]);
        smallTriangle(g, order, b[0], b[1], c[0], c[1]);
        smallTriangle(g, order, c[0], c[1], a[0], a[1]);

    }
    /** Setter for the x variable */
    public void setX(int x) {
        this.x = x;
    }
    /** Setter for y variable */
    public void setY(int y) {
        this.y = y;
    }
    /** Setter for the order variable */
    public void setOrder(int count) {
        order = count;
    }

    /**
     * This recursive method takes a line and adds an equilateral triangle with the two base vertices of the triangle
     * being positioned at the first third of the line and the last third of the line with the top vertex pointing
     * outwards.
     * @param g the graphics component.
     * @param order the number of recursive calls to be done.
     * @param x1 the x coordinate at the start of the line.
     * @param y1 the y coordinate at the start of the line.
     * @param x2 the x coordinate at the end of the line.
     * @param y2 the y coordinate at the end of the line.
     */
    private void smallTriangle(Graphics g, int order, int x1, int y1, int x2, int y2) {

        // draw a simple line representing one side of the triangle.
        if (order <= 1) {
            g.drawLine(x1,y1,x2,y2);
        } else {
            order--;
            // distance formula just in case I need it later.
            //int distance = (int) Math.sqrt(((x2 - x1) ^ 2) + ((y2 - y1) ^ 2));
            int distanceX = (x2 - x1) / 3; // distance to first third of the line relative to x.
            int distanceY = (y2 - y1) / 3; // distance to first third of the line relative to y.

            int[] oneThirdsPoint = {x1 + distanceX, y1 + distanceY}; // coordinate for the first third of the line.
            int[] twoThirdsPoint = {x2 - distanceX, y2 - distanceY}; // coordinate for the second third of the line.

            // coordinate for the protruding point of the equilateral triangle using special trigonometry formulas.
            int[] protrudingPoint = {(int) (oneThirdsPoint[0] + (distanceX / 2) + distanceY * (Math.sqrt(3) / 2)),
                    (int) (oneThirdsPoint[1] + (distanceY / 2) - distanceX * (Math.sqrt(3) / 2))};


            g.drawLine(oneThirdsPoint[0], oneThirdsPoint[1], protrudingPoint[0], protrudingPoint[1]);
            g.drawLine(protrudingPoint[0], protrudingPoint[1], twoThirdsPoint[0], twoThirdsPoint[1]);

            // Graphics2D object used for more functionality. It is used to erase the bottom line of the triangle.
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g.setColor(Color.LIGHT_GRAY);
            g2.drawLine(oneThirdsPoint[0], oneThirdsPoint[1], twoThirdsPoint[0], twoThirdsPoint[1]);
            g.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));

            // recursively calls the smallTriangle function to draw a koch curve.
            smallTriangle(g, order, x1, y1, oneThirdsPoint[0], oneThirdsPoint[1]);
            smallTriangle(g, order, oneThirdsPoint[0], oneThirdsPoint[1], protrudingPoint[0], protrudingPoint[1]);
            smallTriangle(g, order, protrudingPoint[0], protrudingPoint[1], twoThirdsPoint[0], twoThirdsPoint[1]);
            smallTriangle(g, order, twoThirdsPoint[0], twoThirdsPoint[1], x2, y2);
        }
    }
}
