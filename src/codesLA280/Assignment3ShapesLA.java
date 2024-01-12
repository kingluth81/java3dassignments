package codesLA280;

import java.awt.Font;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Cylinder;
import org.jogamp.vecmath.*;

public abstract class Assignment3ShapesLA{
    protected abstract Node create_Object();	           // use 'Node' for both Group and Shape3D
    public abstract Node position_Object();
}


class RingBase extends Assignment3ShapesLA {
    private TransformGroup objTG;                          // use 'objTG' to position an object
    public RingBase() {
        Transform3D translator = new Transform3D();        // 4x4 matrix for translation
        translator.setTranslation(new Vector3f(0f, 0f, 0.3f));
        Transform3D rotator = new Transform3D();           // 4x4 matrix for rotation
        rotator.rotX(Math.PI);
        Transform3D trfm = new Transform3D();
        trfm.mul(rotator); // 4x4 matrix for composition
        trfm.mul(translator);                              // apply translation next
        // apply rotation first
        objTG = new TransformGroup(trfm);                  // set the combined transformation

        objTG.addChild(create_Object());
    }
    protected Node create_Object() {
        return new 	Cylinder(0.5f, 0.2f, 3, 30, 30, CommonsLA.obj_Appearance(CommonsLA.White)); //Returning desired cylinder and setting color
    }
    public Node position_Object() {
        return objTG;
    }
}
