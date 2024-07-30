/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Controller;
import Model.Mesh;
import Model.Triangle;
import Model.Vector3D;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author HP 240 G8
 */
public class ScreenPanel extends JPanel {
    Controller controller;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        Mesh mesh = controller.getRenderData();

        for (Triangle tri : mesh.getTriangles()) {
            // Rotate
            Triangle rotatedTriangle = controller.rotate(tri);

            // Offset
            Triangle translatedTriangle = controller.offSet(rotatedTriangle);

            // Project
            Triangle projectedTriangle = controller.project(translatedTriangle);

            // Scale
            projectedTriangle = controller.scale(projectedTriangle, this.getWidth(), this.getHeight());

            drawTriangle(
                (int) projectedTriangle.getIndex(0).getX(), (int) projectedTriangle.getIndex(0).getY(),
                (int) projectedTriangle.getIndex(1).getX(), (int) projectedTriangle.getIndex(1).getY(),
                (int) projectedTriangle.getIndex(2).getX(), (int) projectedTriangle.getIndex(2).getY(),
                Color.WHITE, g
            );
        }
    }

    private void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }
}
