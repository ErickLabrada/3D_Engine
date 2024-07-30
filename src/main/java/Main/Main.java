package Main;

import Controller.Controller;
import Model.Model;
import View.ScreenPanel;
import javax.swing.JFrame;

/**
 *
 * @author HP 240 G8
 */
public class Main {


    public static void main(String[] args) {

        JFrame frame = new JFrame("3D Renderer");
        ScreenPanel screenPanel = new ScreenPanel();
        Model model = new Model();
        Controller controller = new Controller(model, screenPanel);
        screenPanel.addController(controller);

        frame.add(screenPanel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
}
