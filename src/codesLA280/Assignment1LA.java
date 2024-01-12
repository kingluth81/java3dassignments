package codesLA280;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.loaders.IncorrectFormatException;
import org.jogamp.java3d.loaders.ParsingErrorException;
import org.jogamp.java3d.loaders.Scene;
import org.jogamp.java3d.loaders.objectfile.ObjectFile;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;



public class Assignment1LA extends JPanel {
    private static final long serialVersionUID = 1L;
    private static JFrame frame;
    private static final int OBJ_NUM = 5;


    public static BranchGroup create_Scene() {
        BranchGroup sceneBG = new BranchGroup();
        TransformGroup sceneTG = new TransformGroup();




        sceneBG.addChild(CommonsLA.add_Lights(CommonsLA.White, 1));
        sceneBG.addChild(CommonsLA.rotate_Behavior(7500, sceneTG));
        sceneBG.addChild(sceneTG);                         // make 'sceneTG' continuous rotating
        return sceneBG;
    }
    private BranchGroup loadshape() {
        int flags = ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY;
        ObjectFile f = new ObjectFile(flags, (float) (60 * Math.PI / 180.0));
        Scene s = null;
        System system = null;
        try {
            s = f.load("images/obj/Ring1.obj");
        } catch (FileNotFoundException e) {

        } catch (ParsingErrorException e) {
            system.err.println(e);
            system.exit(1);
        } catch (IncorrectFormatException e) {
            system.err.println(e);
            system.exit(1);
        }

        return s.getSceneGroup();


    }
    class Axis {


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

        
        public Node position_Object() {
            // TODO Auto-generated method stub
            return create_Object();
        }

    }


    public Assignment1LA(BranchGroup sceneBG) {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);

        SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
        CommonsLA.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));

        sceneBG.addChild(CommonsLA.key_Navigation(su));     // allow key navigation
        sceneBG.compile();		                           // optimize the BranchGroup
        su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

        frame.setLayout(new BorderLayout());
        frame.add("Center", canvas);
        frame.setSize(800, 800);                           // set the size of the JFrame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frame = new JFrame("LA Assignment 1");
        frame.getContentPane().add(new Assignment1LA(create_Scene()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
