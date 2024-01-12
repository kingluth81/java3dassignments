package CodesLA2800;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class DrawingPanel extends JPanel {

    class Point{
        int x1, y1, x2, y2;
        Point(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private int x1,y1,x2,y2;
    ArrayList<Point> points = new ArrayList<Point>();
    private boolean initPoint = false;
    String actionSelected = "add" ;                                         // could be add, move or delete
    int lineINDEX;                                                          // helper variables
    boolean drawingPermission = true, bufferLine = false;
    // constructor
    public DrawingPanel(){

        addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(e.getButton() == MouseEvent.BUTTON1) {

                            super.mouseClicked(e);
                            ////////////////////////////////////////////////////////////////////////////////////////////////
                            // deleting an edge when clicked
                            if (Objects.equals(actionSelected, "delete")) {

                                int x = e.getX();     // initial points
                                int y = e.getY();

                                lineINDEX = isOnLine(x, y);
                                if (lineINDEX != -1) {
                                    removeLine(lineINDEX);
                                }
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {


                        if(e.getButton() == MouseEvent.BUTTON1) {

                            initPoint = false;

                            if (Objects.equals(actionSelected, "add")) {
                                addLine(x1, y1, x2, y2);
                            }
                        }
                    }

                }
        );

        addMouseMotionListener(
                new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {

                        // only if it's a left click
                        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {

                            if (!initPoint) {

                                x1 = x2 = e.getX();     // getting initial points
                                y1 = y2 = e.getY();

                                // add, delete or move based on the user's choice

                                if (Objects.equals(actionSelected, "move")) {
                                    lineINDEX = isOnLine(x1, y1);
                                } else if (Objects.equals(actionSelected, "delete")) {
                                    lineINDEX = isOnLine(x1, y1);
                                }

                                initPoint = true;
                            }

                            // not initial points
                            if (Objects.equals(actionSelected, "add")) {
                                x2 = e.getX();
                                y2 = e.getY();
                                bufferLine = true;
                                repaint();
                            } else if (Objects.equals(actionSelected, "move")) {

                                //the point lies on a line
                                if (lineINDEX != -1) {
                                    Point draggedLine = points.get(lineINDEX);
                                    int oldX = x2, oldY = y2;
                                    x2 = e.getX();
                                    y2 = e.getY();
                                    int xVector = x2 - oldX, yVector = y2 - oldY;

                                    draggedLine.x1 = draggedLine.x1 + xVector;
                                    draggedLine.x2 = draggedLine.x2 + xVector;
                                    draggedLine.y1 = draggedLine.y1 + yVector;
                                    draggedLine.y2 = draggedLine.y2 + yVector;
                                    points.set(lineINDEX, draggedLine);
                                    repaint();
                                }
                            }
                        }

                    }
                }

        );
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        for (Point newLine: points) {
            Line2D line = new Line2D.Double(newLine.x1,newLine.y1,newLine.x2,newLine.y2);
            g2d.draw(line);

            AffineTransform tx = new AffineTransform();
            Polygon arrowHead = new Polygon();
            arrowHead.addPoint( 0,5);
            arrowHead.addPoint( -5, -5);
            arrowHead.addPoint( 5,-5);

            tx.setToIdentity();
            double angle = Math.atan2(newLine.y2-newLine.y1, newLine.x2-newLine.x1);
            tx.translate(newLine.x2, newLine.y2);
            tx.rotate((angle-Math.PI/2d));

            Graphics2D g2d2 = (Graphics2D) g2d.create();
            g2d2.setTransform(tx);
            g2d2.fill(arrowHead);
            g2d2.dispose();
        }
        if (Objects.equals(actionSelected, "add") && bufferLine) {
            Line2D line = new Line2D.Double(x1, y1, x2, y2);
            g2d.draw(line);
            AffineTransform tx = new AffineTransform();
            Polygon arrowHead = new Polygon();
            arrowHead.addPoint( 0,5);
            arrowHead.addPoint( -5, -5);
            arrowHead.addPoint( 5,-5);

            tx.setToIdentity();
            double angle = Math.atan2(y2-y1, x2-x1);
            tx.translate(x2, y2);
            tx.rotate((angle-Math.PI/2d));

            Graphics2D g2d2 = (Graphics2D) g2d.create();
            g2d2.setTransform(tx);
            g2d2.fill(arrowHead);
            g2d2.dispose();
        }

    }

    // return index number of the line if the point lies on the line
    public int isOnLine(int x, int y){
        double error_threshold = 0.3;
        int lineIndex = 0;

        for (Point line: points){

            double linelen = pow((line.x1 - line.x2),2) + pow((line.y1 - line.y2),2);
            double line1len = pow((line.x1 - x),2) + pow((line.y1 - y),2);
            double line2len = pow((line.x2 - x),2) + pow((line.y2 - y),2);
            linelen = sqrt(linelen);
            line1len = sqrt(line1len);
            line2len = sqrt(line2len);

            if (line1len + line2len - error_threshold <= linelen && linelen <= line1len + line2len + error_threshold){
                System.out.println("DEBUG -> clicked on Line");
                return lineIndex;
            }
            lineIndex++;
        }
        return -1;
    }

    // to remove a line using line index in points
    public void removeLine(int lineINX){
        points.remove(lineINX);
        bufferLine = false;
        repaint();
    }

    // to add a line to points
    public void addLine(int x1, int y1, int x2, int y2){
        Point newCoordinate = new Point(x1, y1, x2, y2);
        points.add(newCoordinate);

    }
}