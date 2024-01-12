package CodesLA2800;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridLayoutDemo1 extends JPanel implements ActionListener {
    public boolean toggle = true;
    public JPanel container;
    public GridLayout grid1 = new GridLayout(2, 3, 5, 5);
    public GridLayout grid2 = new GridLayout(3, 2);
    private JButton[] buttons;
    private String[] names = new String[]{"one", "two", "three", "four", "five", "six"};

    public GridLayoutDemo1() {
        this.setLayout(this.grid1);
        this.buttons = new JButton[this.names.length];

        for(int var1 = 0; var1 < this.names.length; ++var1) {
            this.buttons[var1] = new JButton(this.names[var1]);
            this.buttons[var1].addActionListener(this);
            this.add(this.buttons[var1]);
        }

        this.setSize(300, 150);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent var1) {
        if (this.toggle) {
            this.setLayout(this.grid2);
        } else {
            this.setLayout(this.grid1);
        }

        this.toggle = !this.toggle;
        this.validate();
    }

    public static void main(String[] var0) {
        JFrame var1 = new JFrame("Grid Layout Exercise");
        var1.setDefaultCloseOperation(3);
        GridLayoutDemo1 var2 = new GridLayoutDemo1();
        var1.getContentPane().add(var2);
        var1.setSize(300, 150);
        var1.setVisible(true);
    }
}
