package codesLA280;

/*import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;


import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Box;
import org.jogamp.java3d.utils.geometry.Cylinder;
import org.jogamp.java3d.utils.geometry.Primitive;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;
import org.jogamp.java3d.Link;

public class Assignment3LA extends JPanel {

    private static JFrame frame;
    private static final int num =1;
    private static final int OBJ_NUM =2 ;

    public static BranchGroup create_Scene() {
        BranchGroup sceneBG = new BranchGroup();   // create the scene' BranchGroup
        TransformGroup sceneTG = new TransformGroup();   // create the scene's TransformGroup
        TransformGroup baseTG = new TransformGroup();

        // create new 4 TransformGroup
        TransformGroup base1TG = new TransformGroup();
        TransformGroup base2TG = new TransformGroup();
        TransformGroup base3TG = new TransformGroup();
        TransformGroup base4TG = new TransformGroup();

        // create the 4 external objectS
        RingObjectsLA ring13D = new Ring1();
        base1TG = ring13D.position_Object1();

        RingObjectsLA ring23D = new Ring2();
        base2TG = ring23D.position_Object2();

        RingObjectsLA ring33D = new Ring3();
        base3TG = ring33D.position_Object3();

        RingObjectsLA ring43D = new Ring4();
        base4TG = ring43D.position_Object4();

        Assignment3ShapesLA[] Assignment3Shapes = new Assignment3ShapesLA[OBJ_NUM];
        Assignment3Shapes[0] = new StringShape6("LA's Assignment3");
        sceneTG.addChild(Assignment3Shapes[0].position_Object());//Adding shape to sceneTG


        //Object for axis frame to attach to the sceneBG
        Assignment3ShapesLA[] Assignment3AxisFrame = new Assignment3ShapesLA[num];
        Assignment3AxisFrame[0] = new AxisFrame();

        //attaching to sceneTG
        for (int i = 0; i < num; i++)
            sceneBG.addChild(Assignment3AxisFrame[i].position_Object());

        sceneBG.addChild(CommonsLA.add_Lights(CommonsLA.White, 1));
        sceneBG.addChild(CommonsLA.rotate_Behavior(7500, sceneTG));
        sceneBG.addChild(sceneTG);    // make 'sceneTG' continuous rotating
        sceneTG.addChild(baseTG); // make 'baseTG' continuous rotating
        sceneTG.addChild(base1TG);
        sceneTG.addChild(base2TG);
        sceneTG.addChild(base3TG);
        sceneTG.addChild(base4TG);

        baseTG.addChild(PrimitiveBox());
        createContent(baseTG);

        return sceneBG;
    }


    private static void createContent(TransformGroup baseTG) {
        Vector3f[] post = {new Vector3f(0f,-0f,-0.4f), new Vector3f(0.99f,0f,-0.4f), new Vector3f(0.99f,0f,-0.65f), new Vector3f(0f,0f,-0.65f)};
        SharedGroup shared3D = new SharedGroup(); //Creating Shared Group
        shared3D.addChild(PrimitiveCyl());	 //Adding shape to shared group
        shared3D.compile();

        Transform3D scaler = new Transform3D();
        scaler.setScale(1d); //setting scale
        baseTG.setTransform(scaler);
        Link[] links =new Link[4];  //Adding four links for 4 shapes
        for(int i= 0;i<4;i++) {
            links[i] = new Link(shared3D);
            baseTG.addChild(linked3D(post[i],links[i]));
        }
    }

    private static TransformGroup linked3D(Vector3f pos, Link link) {
        Transform3D position = new Transform3D();
        position.setTranslation(pos); //setting translation
        TransformGroup posTG = new TransformGroup(position);
        posTG.addChild(link);
        return posTG;

    }
    private static TransformGroup PrimitiveBox() {
        Vector3d[] position = {new Vector3d(0d,-1.1d,-0.022d), new Vector3d(0.029d,-1.1d,0d)}; // a position vector
        TransformGroup objBG = new TransformGroup();
        Appearance app = new Appearance();

        Primitive[] priShape =new Primitive[2]; // two primitive shapes
        priShape[0] = new Box(0.5f,0.1f,1.0f,CommonsLA.obj_Appearance(CommonsLA.Magenta)); // adding size and color for box 1
        priShape[1] = new Box(0.995f,0.1f,0.5f,CommonsLA.obj_Appearance(CommonsLA.Magenta)); // adding size and color for box 2

        Transform3D trans3d = new Transform3D();
        TransformGroup trans = null;
        for(int i=0; i<2;i++){
            trans3d.setScale(new Vector3d(1d, 1d, 0.25d)); //setting scale
            trans3d.setTranslation(position[i]); //setting translation
            trans = new TransformGroup(trans3d);

            objBG.addChild(trans);
            trans.addChild(priShape[i]);
        }
        return objBG;
    }

    private static TransformGroup PrimitiveCyl() {
        Vector3f[] position = {new Vector3f(-0.46f,-1.1f,0.5f)};
        TransformGroup objBG = new TransformGroup();
        Appearance app = new Appearance();

        Primitive[] priShape =new Primitive[1]; // one primitive object
        priShape[0] = new Cylinder(0.5f,0.2f, CommonsLA.obj_Appearance(CommonsLA.Magenta)); // adding size and color for cyl

        Transform3D trans3d = new Transform3D();
        TransformGroup trans = null;
        for(int i=0; i<1;i++){
            trans3d.setScale(new Vector3d(1, 1, 0.25f));//scaling as required
            trans3d.setTranslation(position[i]); //setting translation
            trans = new TransformGroup(trans3d);
            objBG.addChild(trans);
            trans.addChild(priShape[i]);
        }
        return objBG;
    }
    public Assignment3LA(BranchGroup sceneBG) {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);

        SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
        CommonsLA.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));

        BranchGroup group = new BranchGroup();
        Background background = new Background();
        //TextureLoader textureLoader = new TextureLoader("background.jpg", null);
        //background.setImage(textureLoader.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        background.setApplicationBounds(new BoundingSphere(new Point3d(), 1.0));
        group.addChild(background);
        su.addBranchGraph(group);

        sceneBG.addChild(CommonsLA.key_Navigation(su));     // allow key navigation
        sceneBG.compile();		                           // optimize the BranchGroup
        su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

        setLayout(new BorderLayout());
        add("Center", canvas);
        frame.setSize(800, 800);                           // set the size of the JFrame
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        frame = new JFrame("LA's Assignment3");
        frame.getContentPane().add(new Assignment3LA(create_Scene()));  // create an instance of the class
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}*/