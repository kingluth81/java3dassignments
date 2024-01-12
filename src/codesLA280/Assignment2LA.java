package codesLA280;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Box;
import org.jogamp.java3d.utils.geometry.Primitive;
import org.jogamp.java3d.utils.universe.*;
import org.jogamp.vecmath.*;

public class Assignment2LA extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JFrame frame;

    /* a function to create the base with a box attached with a string label */
    public static TransformGroup create_Base() {
        Transform3D translator = new Transform3D();        // set 'baseTG' with translation in Y-axis
        translator.setTranslation(new Vector3d(0d, -1.1d, 0d));
        TransformGroup baseTG = new TransformGroup(translator);

        Appearance app = CommonsLA.obj_Appearance(CommonsLA.Orange);
        Box box = new Box(1.0f, 0.1f, 0.25f, Primitive.GENERATE_NORMALS, app);
        baseTG.addChild(box);                              // attach a box as base to 'baseTG'

        String str = "LA's A2";
        baseTG.addChild(string_Label(str, 0.15d,           // attach the string label to 'baseTG'
                new Point3f(-str.length() / 4.0f, -0.25f, 1.6f), CommonsLA.Magenta));

        return baseTG;                                     // return 'baseTG' with labeled base
    }

    /* a function to create a scaled string label with or without rotation */
    private static TransformGroup string_Label(String s, double c, Point3f p, Color3f clr) {
        Font my2DFont = new Font("Arial", Font.PLAIN, 1);  // set font's name, style, and size
        FontExtrusion myExtrude = new FontExtrusion();
        Font3D font3D = new Font3D(my2DFont, myExtrude);

        Text3D text3D = new Text3D(font3D, s, p);          // prepare string for the label
        text3D.setAlignment(1);
        Appearance app = CommonsLA.obj_Appearance(clr);

        Transform3D tmp = new Transform3D();
        tmp.setScale(c);                                   // set 'tmp' to a scaling 4x4 matrix
        TransformGroup stringTG = new TransformGroup(tmp); // set 'stringTG' with the scaling factor

        stringTG.addChild(new Shape3D(text3D, app));       // apply scaling to the string label

        return stringTG;                                   // return 'stringTG' with the scaled string
    }

    /* a function to build the content branch */
    private static BranchGroup create_Scene() {
        BranchGroup sceneBG = new BranchGroup();
        TransformGroup sceneTG = new TransformGroup();

        //RingObjectsLA ring3D = new SimpleRing();           // create the external object
        //sceneTG = ring3D.position_Object();                // set 'sceneTG' to the object's TG
        sceneTG.addChild(create_Base());                   // attached the base and then the label
        sceneBG.addChild(CommonsLA.rotate_Behavior(5000, sceneTG));
        // make 'sceneTG' continuously rotating
        sceneBG.addChild(sceneTG);
        return sceneBG;
    }

    /* NOTE: Keep the constructor for each of the labs and assignments */
    public Assignment2LA(BranchGroup sceneBG) {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);

        SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
        CommonsLA.define_Viewer(su, new Point3d(1.0d, 1.0d, 4.0d));

        sceneBG.addChild(CommonsLA.add_Lights(CommonsLA.White, 1));
        sceneBG.addChild(CommonsLA.key_Navigation(su));    // allow key navigation
        sceneBG.compile();		                           // optimize the BranchGroup
        su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

        setLayout(new BorderLayout());
        add("Center", canvas);
        frame.setSize(800, 800);                           // set the size of the JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frame = new JFrame("LA's A2");          // NOTE: change XY to student's initials
        frame.getContentPane().add(new Assignment2LA(create_Scene()));  // create an instance of the class
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
