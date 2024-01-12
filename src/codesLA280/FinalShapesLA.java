package codesLA280;

import java.io.FileNotFoundException;

import com.sun.java.accessibility.util.Translator;
import org.jogamp.java3d.*;
import org.jogamp.java3d.loaders.IncorrectFormatException;
import org.jogamp.java3d.loaders.ParsingErrorException;
import org.jogamp.java3d.loaders.Scene;
import org.jogamp.java3d.loaders.objectfile.ObjectFile;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Vector3d;
import org.jogamp.vecmath.Vector3f;
import org.jogamp.vecmath.*;

/* a super class for the definition of external objects */
public abstract class FinalShapesLA {
    private BranchGroup objBG;                             // load external object to 'objBG'
    private TransformGroup objTG;                          // use 'objTG' to resize an object
    protected Vector3d scale;                              // use it to change the size of objects
    protected Vector3f post;                              // use 'post' to specify location
    protected Shape3D obj_shape;


    /* a function to attach the current object to 'objTG' and return 'objTG' */
    public TransformGroup position_Object() {
        objTG.addChild(objBG);                             // attach external object to 'objTG'	
        return objTG;                                      // return 'objTG' to allow key-based object movements
    }

    /* a function to set 'objTG' and load external object to 'objBG' with appearance setting */
    protected void load_Object(String obj_name, Color3f obj_clr) {
        Transform3D scaler = new Transform3D();
        scaler.setScale(scale);
        objTG = new TransformGroup(scaler);

        objBG = load_Shape(obj_name);                      // load external object to 'objBG'
        Shape3D obj_shape = (Shape3D) objBG.getChild(0);   // convert to Shape3D to set object's appearance
        obj_shape.setAppearance(CommonsLA.obj_Appearance(obj_clr));
    }

    /* a function to load and return object shape from the file named 'obj_name' */
    private BranchGroup load_Shape(String obj_name) {
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE, (float) (60 * Math.PI / 180.0));
        Scene s = null;
        try {                                              // load object's definition file to 's'
            s = f.load("images/" + obj_name + ".obj");
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        } catch (ParsingErrorException e) {
            System.err.println(e);
            System.exit(1);
        } catch (IncorrectFormatException e) {
            System.err.println(e);
            System.exit(1);
        }
        return s.getSceneGroup();
    }

    protected void transform_Object(String obj_name) {
        Transform3D scaler = new Transform3D();
        scaler.setScale(scale);                            // set scale for the 4x4 matrix
        scaler.setTranslation(post);                       // set translations for the 4x4 matrix
        objTG = new TransformGroup(scaler);                // set the translation BG with the 4x4 matrix
        obj_shape = (Shape3D) objBG.getChild(0);           // get and cast the object to 'obj_shape'
        obj_shape.setName(obj_name);                       // use the name to identify the object
    }

    protected void transform_Object() {
        Transform3D scaler = new Transform3D();
        scaler.setScale(scale);                            // set scale for the 4x4 matrix
        scaler.setTranslation(post);                       // set translations for the 4x4 matrix
        objTG = new TransformGroup(scaler);                // set the translation BG with the 4x4 matrix
        objBG = new BranchGroup();                      // use the name to identify the object
    }
}


/* a derived class for the cup's base object */
class CupBase extends FinalShapesLA {
    public CupBase() {
        Appearance app= new Appearance();
        Transform3D translator = new Transform3D();
        scale = new Vector3d(0.8, 0.8, 0.8);    // set scaling for transformation
        post =new Vector3f(0.0f,0.0f,0.0f);     // use to move object for positioning
        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent
        load_Object("cup_base", new Color3f(0.35f, 0.35f, 0.35f)); // light grey
    }
}

/* a derived class for the innermost segment of cup wells */
class CupWall0 extends FinalShapesLA {
    public CupWall0() {
        Appearance app= new Appearance();
        scale = new Vector3d(0.42, 0.42, 0.42);            // set scaling for transformation
        post =new Vector3f(0.0f,0.0f,0.0f);         // use to move object for positioning
        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent
        //PositionInterpolator(alpha)           //tried to implement positioninter for the movement of the cups
        load_Object("cup_wall0", new Color3f(0.35f, 0.0f, 0.35f)); // light magenta
    }
}

/* a derived class for the 1st segment of cup wells */
class CupWall1 extends FinalShapesLA {
    public CupWall1() {
        Appearance app= new Appearance();
        scale = new Vector3d(0.4662, 0.4662, 0.4662);      // set scaling for transformation
        post =new Vector3f(0.0f,1f,0.0f);         // use to move object for positioning
        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent
        load_Object("cup_wall1", new Color3f(0.35f, 0.15f, 0.0f)); // light orange
    }
}

/* a derived class for the 2nd segment of cup wells */
class CupWall2 extends FinalShapesLA {
    public CupWall2() {
        Appearance app= new Appearance();
        scale = new Vector3d(0.5175, 0.5175, 0.5175);      // set scaling for transformation
        post =new Vector3f(0.0f,0.0f,0.0f);

        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent
        load_Object("cup_wall2", new Color3f(0.35f, 0.35f, 0.0f)); // light yellow
    }
}

/* a derived class for the 3rd segment of cup wells */
class CupWall3 extends FinalShapesLA {
    public CupWall3() {
        Appearance app= new Appearance();
        scale = new Vector3d(0.5744, 0.5744, 0.5744);      // set scaling for transformation
        post =new Vector3f(0.0f,0.0f,0.0f);

        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent
        load_Object("cup_wall3", new Color3f(0.0f, 0.35f, 0.0f)); // light green
    }
}

/* a derived class for the 4th segment of cup wells */
class CupWall4 extends FinalShapesLA {
    public CupWall4() {

        Appearance app= new Appearance();
        scale = new Vector3d(0.6376, 0.6376, 0.6376);      // set scaling for transformation
        post =new Vector3f(0.0f,0.0f,0.0f);

        TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.7f);
        app.setTransparencyAttributes(ta);                  //Set appearance to Transparent

        load_Object("cup_wall4", new Color3f(0.0f, 0.0f, 0.35f)); // light blue
        return;
    }
}
