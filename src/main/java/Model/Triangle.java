/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP 240 G8
 */
public class Triangle {
       Vector3D[] vector = new Vector3D[3];

    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3){
        this.vector[0]=v1;
        this.vector[1]=v2;
        this.vector[2]=v3;
    }

    public Vector3D[] getVector() {
        return vector;
    }

    public Vector3D getIndex(int i) {
        if (i >= 0 && i < vector.length) {
            return vector[i];
        } else {
            throw new IndexOutOfBoundsException("Invalid vertex index");
        }
    }

}
