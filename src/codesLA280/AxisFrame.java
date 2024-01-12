package codesLA280;

import org.jogamp.java3d.LineArray;
import org.jogamp.java3d.Node;
import org.jogamp.java3d.Shape3D;
import org.jogamp.vecmath.Point3f;

public class AxisFrame extends Assignment3ShapesLA {
    protected Node create_Object() {
        Point3f coor[] = new Point3f[6];                 // declare 6 points for axis frame shape
        LineArray lineArr = new LineArray(6, LineArray.COLOR_3 | LineArray.COORDINATES);
        coor[0] = new Point3f(0f, 0f, 0f);
        coor[1] = new Point3f(1f, 0f, 0f);
        coor[2] = new Point3f(0f, 0f, 0f);
        coor[3] = new Point3f(0f, 1f, 0f);
        coor[4] = new Point3f(0f, 0f, 0f);
        coor[5] = new Point3f(0f, 0f, 1f);
        //Setting  coordinates for a axis frame lines, a pair for each line
        lineArr.setCoordinate(0, coor[0]);
        lineArr.setCoordinate(1, coor[1]);
        lineArr.setCoordinate(2, coor[2]);
        lineArr.setCoordinate(3, coor[3]);
        lineArr.setCoordinate(4, coor[4]);
        lineArr.setCoordinate(5, coor[5]);
        lineArr.setColor(0, CommonsLA.Red);        // specify color for each pair of points
        lineArr.setColor(1, CommonsLA.Red);
        lineArr.setColor(2, CommonsLA.Green);        // specify color for each pair of points
        lineArr.setColor(3, CommonsLA.Green);
        lineArr.setColor(4, CommonsLA.Blue);        // specify color for each pair of points
        lineArr.setColor(5, CommonsLA.Blue);

        return new Shape3D(lineArr);
    }

    public Node position_Object() {
        return create_Object();
    }
}
