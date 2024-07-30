/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Vector;

/**
 *
 * @author HP 240 G8
 */
public class Mesh {
     Vector <Triangle> tris;
    
     public Mesh() {
        tris = new Vector<>();
    }
     
     public Vector<Triangle> getTriangles(){
         return tris;
     }
}
