/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Matrix4x4;
import Model.Mesh;
import Model.Model;
import Model.Triangle;
import Model.Vector3D;
import View.ScreenPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author HP 240 G8
 */
public class Controller implements KeyListener {
    Model model;
    ScreenPanel view;

    public Controller(Model model, ScreenPanel view) {
        this.model = model;
        this.view = view;
        this.view.addKeyListener(this);
        this.view.setFocusable(true);
    }

    public Mesh getRenderData() {
        return model.getMesh();
    }

    public Vector3D multiplyMatrixVector(Vector3D tri) {
        return model.multiplyMatrixVector(tri, model.getProjectionMatrix());
    }

    public Triangle offSet(Triangle triangle) {
        return model.offSet(triangle);
    }

    public Triangle project(Triangle rotatedTriangle) {
        return model.project(rotatedTriangle);
    }

    public Triangle rotate(Triangle projectedTriangle) {
        return model.rotate(projectedTriangle);
    }

    public Triangle scale(Triangle projectedTriangle, int width, int height) {
        return model.scale(projectedTriangle, width, height);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            model.setyRotation(model.getyRotation() - 0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            model.setyRotation(model.getyRotation() + 0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            model.setxRotation(model.getxRotation() - 0.1f);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            model.setxRotation(model.getxRotation() + 0.1f);
        }
        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}


