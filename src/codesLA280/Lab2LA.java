package codesLA280;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;

public class Lab2LA extends JPanel {
    private static final long serialVersionUID = 1L;
    private static JFrame frame;
    private static final int OBJ_NUM = 5;

    public static BranchGroup create_Scene() {
        BranchGroup sceneBG = new BranchGroup();           // create the scene' BranchGroup
        TransformGroup sceneTG = new TransformGroup();     // create the scene's TransformGroup

        Lab2ShapesLA[] lab2Shapes = new Lab2ShapesLA[OBJ_NUM];
        lab2Shapes[0] = new ConeShape2();
        lab2Shapes[1] = new ConeShape3();
        lab2Shapes[2] = new StringShape2("LA's Lab2");
        lab2Shapes[3] = new CircleShape2();
        lab2Shapes[4]= new Axis2();


        for (int i = 0; i < OBJ_NUM; i++)
            sceneTG.addChild(lab2Shapes[i].position_Object());

        sceneBG.addChild(CommonsLA.add_Lights(CommonsLA.White, 1));
        sceneBG.addChild(CommonsLA.rotate_Behavior(7500, sceneTG));
        sceneBG.addChild(sceneTG);                         // make 'sceneTG' continuous rotating
        return sceneBG;
    }

    /* NOTE: Keep the constructor for each of the labs and assignments */
    public Lab2LA(BranchGroup sceneBG) {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);

        SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
        CommonsLA.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));

        sceneBG.addChild(CommonsLA.key_Navigation(su));     // allow key navigation
        sceneBG.compile();		                           // optimize the BranchGroup
        su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

        setLayout(new BorderLayout());
        add("Center", canvas);
        frame.setSize(800, 800);                           // set the size of the JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frame = new JFrame("LA's Lab2");                   // NOTE: change XY to student's initials
        frame.getContentPane().add(new Lab2LA(create_Scene()));  // create an instance of the class
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
