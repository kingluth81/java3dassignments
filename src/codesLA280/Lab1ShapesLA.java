package codesLA280;

import java.awt.Font;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Cone;
import org.jogamp.vecmath.*;

public abstract class Lab1ShapesLA {
    protected abstract Node create_Object();               // use 'Node' for both Group and Shape3D
    public abstract Node position_Object();
}
class Axis extends Lab1ShapesLA{

    @Override
    protected Node create_Object() {
        //float r = 0.6f, x, y;                              // vertices at 0.6 away from origin
        Point3f coor[] = new Point3f[4];                   // declare 5 points for star shape
        LineArray lineArr = new LineArray(8, LineArray.COLOR_3 | LineArray.COORDINATES);
        coor[0] = new Point3f(0,0,0);
        coor[1] = new Point3f(.3f,0,0);
        coor[2] = new Point3f(0,.3f,0);
        coor[3] = new Point3f(0,0,-0.3f);

        for (int i = 0; i <= 3; i++) {
            lineArr.setCoordinate(i * 2, coor[i]);         // define point pairs for each line
            lineArr.setCoordinate(i * 2 , coor[(i+1) %4]);
            lineArr.setColor(i * 2, CommonsLA.Red);        // specify color for each pair of points
            lineArr.setColor(i * 2 + 1, CommonsLA.Green);
        }
        return new Shape3D(lineArr);                        // create and return a Shape3D
    }

    @Override
    public Node position_Object() {
        // TODO Auto-generated method stub
        return create_Object();
    }

}
class CircleShape extends Lab1ShapesLA{

    @Override
    protected Node create_Object() {
        float r = 0.6f, x, y;                              // vertices at 0.6 away from origin
        Point3f coor[] = new Point3f[15];                   // declare 5 points for star shape
        LineArray lineArr = new LineArray(30, LineArray.COLOR_3 | LineArray.COORDINATES);
        for (int i = 0; i <= 14; i++) {                     // define coordinates for star shape
            x = (float) Math.cos(Math.PI / 360 * (90 + 48 * i)) * r;
            y = (float) Math.sin(Math.PI / 360 * (90 + 48 * i)) * r;
            coor[i] = new Point3f(x, y, -0.6f);            // use z-value to position star shape
        }
        for (int i = 0; i <= 14; i++) {
            lineArr.setCoordinate(i * 2, coor[i]);         // define point pairs for each line
            lineArr.setCoordinate(i * 2 + 1, coor[(i+1) %15]);
            lineArr.setColor(i * 2, CommonsLA.Red);        // specify color for each pair of points
            lineArr.setColor(i * 2 + 1, CommonsLA.Green);
        }
        return new Shape3D(lineArr);                        // create and return a Shape3D
    }

    @Override
    public Node position_Object() {
        // TODO Auto-generated method stub
        return create_Object();
    }
}

class StarShape extends Lab1ShapesLA {
    protected Node create_Object() {
        float r = 0.6f, x, y;                              // vertices at 0.6 away from origin
        Point3f coor[] = new Point3f[5];                   // declare 5 points for star shape
        LineArray lineArr = new LineArray(10, LineArray.COLOR_3 | LineArray.COORDINATES);
        for (int i = 0; i <= 4; i++) {                     // define coordinates for star shape
            x = (float) Math.cos(Math.PI / 180 * (90 + 72 * i)) * r;
            y = (float) Math.sin(Math.PI / 180 * (90 + 72 * i)) * r;
            coor[i] = new Point3f(x, y, -0.6f);            // use z-value to position star shape
        }
        for (int i = 0; i <= 4; i++) {
            lineArr.setCoordinate(i * 2, coor[i]);         // define point pairs for each line
            lineArr.setCoordinate(i * 2 + 1, coor[(i + 2) % 5]);
            lineArr.setColor(i * 2, CommonsLA.Red);        // specify color for each pair of points
            lineArr.setColor(i * 2 + 1, CommonsLA.Green);
        }
        return new Shape3D(lineArr);                        // create and return a Shape3D
    }
    public Node position_Object() {
        return create_Object();
    }
}

class ConeShape extends Lab1ShapesLA {
    private TransformGroup objTG;                          // use 'objTG' to position an object
    public ConeShape() {
        Transform3D translator = new Transform3D();        // 4x4 matrix for translation
        translator.setTranslation(new Vector3f(0f, 0f, 0.3f));
        Transform3D rotator = new Transform3D();           // 4x4 matrix for rotation
        rotator.rotX(Math.PI / -2);
        Transform3D trfm = new Transform3D();              // 4x4 matrix for composition
        trfm.mul(translator);                              // apply translation next
        trfm.mul(rotator);                                 // apply rotation first
        objTG = new TransformGroup(trfm);                  // set the combined transformation

        objTG.addChild(create_Object());
    }
    protected Node create_Object() {
        return new Cone(0.6f, 0.6f, CommonsLA.obj_Appearance(CommonsLA.Orange));
    }
    public Node position_Object() {
        return objTG;
    }
}

class StringShape extends Lab1ShapesLA {
    private TransformGroup objTG;                              // use 'objTG' to position an object
    private String str;
    public StringShape(String str_ltrs) {
        str = str_ltrs;
        Transform3D scaler = new Transform3D();
        scaler.setScale(0.2);                              // scaling 4x4 matrix
        objTG = new TransformGroup(scaler);
        objTG.addChild(create_Object());           // apply scaling to change the string's size
    }
    protected Node create_Object() {
        Font my2DFont = new Font("Arial", Font.PLAIN, 1);  // font's name, style, size
        FontExtrusion myExtrude = new FontExtrusion();
        Font3D font3D = new Font3D(my2DFont, myExtrude);

        Point3f pos = new Point3f(-str.length()/4f, 0, 3f);// position for the string
        Text3D text3D = new Text3D(font3D, str, pos);      // create a text3D object
        return new Shape3D(text3D);
    }
    public Node position_Object() {
        return objTG;
    }
}

