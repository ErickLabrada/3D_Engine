/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author HP 240 G8
 */
public class Model {
    
    private Mesh mesh = new Mesh();
    private Matrix4x4 projectionMatrix=new Matrix4x4();;
    private Matrix4x4 xRotationMatrix=new Matrix4x4();;
    private Matrix4x4 yRotationMatrix=new Matrix4x4();;
    private Matrix4x4 zRotationMatrix=new Matrix4x4();;
    private float xRotation=0.0f, zRotation=0.0f, yRotation=0.0f;
    private float offset = 2.0f;

    public float getxRotation() {
        return xRotation;
    }

    public void setxRotation(float xRotation) {
        this.xRotation = xRotation;
        updateRotationMatrices();
    }

    public float getzRotation() {
        return zRotation;
    }

    public void setzRotation(float zRotation) {
        this.zRotation = zRotation;
        updateRotationMatrices();
    }

    public float getyRotation() {
        return yRotation;
    }

    public void setyRotation(float yRotation) {
        this.yRotation = yRotation;
        updateRotationMatrices();
    }


    public Model(){
        
        
        
        //Projection Matrix
            //HardCoded values
            float fNear =0.1f;
            float fFar = 1000.0f;
            float fFov = 90.0f;
            //                   Screen Height/Screen Width
            float fAspectRatio = (float) 500/ (float) 500;
            float fFovRad = 1.0f/(float)Math.tan(fFov*0.5f/180.0f*(float)Math.PI);

        projectionMatrix.matrix[0][0] = fAspectRatio * fFovRad;
        projectionMatrix.matrix[1][1] = fFovRad;
        projectionMatrix.matrix[2][2] = fFar/(fFar-fNear);
        projectionMatrix.matrix[3][2] = (-fFar*fNear)/(fFar-fNear);
        projectionMatrix.matrix[2][3] = 1f;
        projectionMatrix.matrix[3][3] = 0f;


        
        
        
    }


        
    public Vector3D multiplyMatrixVector(Vector3D input, Matrix4x4 matrix){
        
        Vector3D output = new Vector3D(0, 0, 0);

        
          output.x =input.x*matrix.matrix[0][0]+input.y*matrix.matrix[1][0]+input.z*matrix.matrix[2][0]+matrix.matrix[3][0];
          output.y =input.x*matrix.matrix[0][1]+input.y*matrix.matrix[1][1]+input.z*matrix.matrix[2][1]+matrix.matrix[3][1];
          output.z =input.x*matrix.matrix[0][2]+input.y*matrix.matrix[1][2]+input.z*matrix.matrix[2][2]+matrix.matrix[3][2];
          float w = input.x*matrix.matrix[0][3]+input.y*matrix.matrix[1][3]+input.z*matrix.matrix[2][3]+matrix.matrix[3][3];
          
          if(w!=0.0f){
             output.x/=w;
             output.y/=w;
             output.z/=w;
             
          }
          return output;
    }

    public Mesh getMesh() {
        return this.mesh;
    }

    public Matrix4x4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public Triangle offSet(Triangle tri) {

        //Sends the Z vertex of each coordinate further back
        
        tri.getIndex(0).setZ(tri.getIndex(0).getZ() + offset);
        tri.getIndex(1).setZ(tri.getIndex(1).getZ() + offset);
        tri.getIndex(2).setZ(tri.getIndex(2).getZ() + offset);
        
        return tri;

    }
    
    public Triangle project(Triangle tri) {

        Vector3D v1 = multiplyMatrixVector(tri.getIndex(0),projectionMatrix);
        Vector3D v2 = multiplyMatrixVector(tri.getIndex(1),projectionMatrix);
        Vector3D v3 = multiplyMatrixVector(tri.getIndex(2),projectionMatrix);
        
        return new Triangle(v1,v2,v3);

    }
    
    public Triangle scale(Triangle projectedTriangle,int width, int height) {


        projectedTriangle.getIndex(0).setX(projectedTriangle.getIndex(0).getX() + 1);
        projectedTriangle.getIndex(0).setY(projectedTriangle.getIndex(0).getY() + 1);
        projectedTriangle.getIndex(1).setX(projectedTriangle.getIndex(1).getX() + 1);
        projectedTriangle.getIndex(1).setY(projectedTriangle.getIndex(1).getY() + 1);
        projectedTriangle.getIndex(2).setX(projectedTriangle.getIndex(2).getX() + 1);
        projectedTriangle.getIndex(2).setY(projectedTriangle.getIndex(2).getY() + 1);

        projectedTriangle.getIndex(0).setX((float) (projectedTriangle.getIndex(0).getX() * 0.5 * width));
        projectedTriangle.getIndex(0).setY((float) (projectedTriangle.getIndex(0).getY() * 0.5 * height));
        projectedTriangle.getIndex(1).setX((float) (projectedTriangle.getIndex(1).getX() * 0.5 * width));
        projectedTriangle.getIndex(1).setY((float) (projectedTriangle.getIndex(1).getY() * 0.5 * height));
        projectedTriangle.getIndex(2).setX((float) (projectedTriangle.getIndex(2).getX() * 0.5 * width));
        projectedTriangle.getIndex(2).setY((float) (projectedTriangle.getIndex(2).getY() * 0.5 * height));

        return projectedTriangle;

    }

    public Triangle rotate(Triangle tri){
        
        Vector3D v1 = multiplyMatrixVector(tri.getIndex(0),xRotationMatrix);
        Vector3D v2 = multiplyMatrixVector(tri.getIndex(1),xRotationMatrix);
        Vector3D v3 = multiplyMatrixVector(tri.getIndex(2),xRotationMatrix);
        
        v1 = multiplyMatrixVector(v1,yRotationMatrix);
        v2 = multiplyMatrixVector(v2,yRotationMatrix);
        v3 = multiplyMatrixVector(v3,yRotationMatrix);
        
        v1 = multiplyMatrixVector(v1,zRotationMatrix);
        v2 = multiplyMatrixVector(v2,zRotationMatrix);
        v3 = multiplyMatrixVector(v3,zRotationMatrix);
        
        return new Triangle(v1,v2,v3);
    }
    
    private void updateRotationMatrices(){
        //X Rotation Matrix
        
            xRotationMatrix.matrix[0][0]=1f;
            xRotationMatrix.matrix[1][1]=(float) Math.cos(xRotation);
            xRotationMatrix.matrix[2][1]=(float) Math.sin(xRotation);
            xRotationMatrix.matrix[1][2]=(float) -Math.sin(xRotation);
            xRotationMatrix.matrix[2][2]=(float) Math.cos(xRotation);
            xRotationMatrix.matrix[3][3]=1f;
            

        //Y Rotation Matrix
            
            yRotationMatrix.matrix[0][0]=(float) Math.cos(yRotation);
            yRotationMatrix.matrix[2][0]=(float) Math.sin(yRotation);
            yRotationMatrix.matrix[1][1]=1f;
            yRotationMatrix.matrix[0][2]=(float) -Math.sin(yRotation);
            yRotationMatrix.matrix[2][2]=(float) Math.cos(yRotation);
            yRotationMatrix.matrix[3][3]=1f;
        
        //Z Rotation Matrix
        
            zRotationMatrix.matrix[0][0]=(float) Math.cos(zRotation);
            zRotationMatrix.matrix[1][0]=(float) Math.sin(zRotation);
            zRotationMatrix.matrix[0][1]=(float) -Math.sin(zRotation);
            zRotationMatrix.matrix[1][1]=(float) Math.cos(zRotation);
            zRotationMatrix.matrix[2][2]=1f;
            zRotationMatrix.matrix[3][3]=1f;
    }
    
    
    public void createCube(){
ArrayList<Triangle> triangles = new ArrayList<>(
                Arrays.asList(
                        //cube
                        //Front Face
                        new Triangle(new Vector3D(0, 0, 0), new Vector3D(0, 1, 0), new Vector3D(1, 1, 0)),
                        new Triangle(new Vector3D(0, 0, 0), new Vector3D(1, 1, 0), new Vector3D(1, 0, 0)),
                        //Right Face
                        new Triangle(new Vector3D(1, 0, 0), new Vector3D(1, 1, 0), new Vector3D(1, 1, 1)),
                        new Triangle(new Vector3D(1, 0, 0), new Vector3D(1, 1, 1), new Vector3D(1, 0, 1)),
                        //Left Face
                        new Triangle(new Vector3D(0, 0, 1), new Vector3D(0, 1, 1), new Vector3D(0, 1, 0)),
                        new Triangle(new Vector3D(0, 0, 1), new Vector3D(0, 1, 0), new Vector3D(0, 0, 0)),
                        //Behind Face
                        new Triangle(new Vector3D(1, 0, 1), new Vector3D(1, 1, 1), new Vector3D(0, 1, 1)),
                        new Triangle(new Vector3D(1, 0, 1), new Vector3D(0, 1, 1), new Vector3D(0, 0, 1)),
                        //Up Face
                        new Triangle(new Vector3D(0, 1, 0), new Vector3D(0, 1, 1), new Vector3D(1, 1, 1)),
                        new Triangle(new Vector3D(0, 1, 0), new Vector3D(1, 1, 1), new Vector3D(1, 1, 0)),
                        //Down Face
                        new Triangle(new Vector3D(0, 0, 1), new Vector3D(0, 0, 0), new Vector3D(1, 0, 0)),
                        new Triangle(new Vector3D(0, 0, 0), new Vector3D(1, 0, 0), new Vector3D(1, 0, 1))
                )
        );
        mesh.tris.addAll(triangles);
        updateRotationMatrices();    
    }

    public void createPyramid() {
ArrayList<Triangle> triangles = new ArrayList<>(
                Arrays.asList(
                                //pyramid
                                    //Left
                                    new Triangle(new Vector3D(0, 0, 1), new Vector3D(0.5f, 1, 0.5f), new Vector3D(0.5f,0,0)),
                                    //Right
                                    new Triangle(new Vector3D(0.5f,0,0), new Vector3D(0.5f, 1, 0.5f), new Vector3D(1,0,1)),
                                    //Back
                                    new Triangle(new Vector3D(1,0,1), new Vector3D(0.5f, 1, 0.5f), new Vector3D(0,0,1)),
                                    //Down
                                    new Triangle(new Vector3D(0,0,1), new Vector3D(0.5f, 0, 0), new Vector3D(1,0,1))

                )
        );
        mesh.tris.addAll(triangles);
        updateRotationMatrices();  
    }

    public void createRubiksCube() {

        this.offset = -10;

        for (float i = -.5f; i < 1; i++) {
            for (float j = -.5f; j < 1; j++) {
                for (float k = -.5f; k < 1; k++) {

                    ArrayList<Triangle> triangles = new ArrayList<>(
                            Arrays.asList(
                                    //Front Face
                                    new Triangle(new Vector3D(-1 + i, 1 + j, -1 + k), new Vector3D(-1 + i, -1 + j, -1 + k), new Vector3D(1 + i, -1 + j, -1 + k)),
                                    new Triangle(new Vector3D(-1 + i, 1 + j, -1 + k), new Vector3D(1 + i, -1 + j, -1 + k), new Vector3D(1 + i, 1 + j, -1 + k)),
                                    //Right Face
                                    new Triangle(new Vector3D(1 + i, 1 + j, -1 + k), new Vector3D(1 + i, -1 + j, -1 + k), new Vector3D(1 + i, -1 + j, 1 + k)),
                                    new Triangle(new Vector3D(1 + i, 1 + j, -1 + k), new Vector3D(1 + i, -1 + j, -1 + k), new Vector3D(1 + i, 1 + j, 1 + k)),
                                    //Left Face
                                    new Triangle(new Vector3D(-1 + i, 1 + j, 1 + k), new Vector3D(-1 + i, -1 + j, 1 + k), new Vector3D(-1 + i, -1 + j, -1 + k)),
                                    new Triangle(new Vector3D(-1 + i, 1 + j, 1 + k), new Vector3D(-1 + i, -1 + j, -1 + k), new Vector3D(-1 + i, 1 + j, -1 + k)),
                                    //Behind Face
                                    new Triangle(new Vector3D(1 + i, 1 + j, 1 + k), new Vector3D(1 + i, -1 + j, 1 + k), new Vector3D(-1 + i, -1 + j, 1 + k)),
                                    new Triangle(new Vector3D(1 + i, 1 + j, 1 + k), new Vector3D(-1 + i, -1 + j, 1 + k), new Vector3D(-1 + i, 1 + j, 1 + k)),
                                    //Up Face
                                    new Triangle(new Vector3D(-1 + i, -1 + j, -1 + k), new Vector3D(-1 + i, -1 + j, 1 + k), new Vector3D(1 + i, -1 + j, 1 + k)),
                                    new Triangle(new Vector3D(-1 + i, -1 + j, -1 + k), new Vector3D(1 + i, -1 + j, 1 + k), new Vector3D(1 + i, -1 + j, -1 + k)),
                                    //Down Face
                                    new Triangle(new Vector3D(-1 + i, 1 + j, 1 + k), new Vector3D(-1 + i, 1 + j, -1 + k), new Vector3D(1 + i, 1 + j, -1 + k)),
                                    new Triangle(new Vector3D(-1 + i, 1 + j, 1 + k), new Vector3D(1 + i, 1 + j, -1 + k), new Vector3D(1 + i, 1 + j, 1 + k))
                            )
                    );
                    mesh.tris.addAll(triangles);
                }
            }
        }
        ArrayList<Triangle> triangles = new ArrayList<>(
                Arrays.asList(
                        //Front Face
                        new Triangle(new Vector3D(-1, 1, -1), new Vector3D(-1, -1, -1), new Vector3D(1, -1, -1)),
                        new Triangle(new Vector3D(-1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, 1, -1)),
                        //Right Face
                        new Triangle(new Vector3D(1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, -1, 1)),
                        new Triangle(new Vector3D(1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, 1, 1)),
                        //Left Face
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, -1, 1), new Vector3D(-1, -1, -1)),
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, -1, -1), new Vector3D(-1, 1, -1)),
                        //Behind Face
                        new Triangle(new Vector3D(1, 1, 1), new Vector3D(1, -1, 1), new Vector3D(-1, -1, 1)),
                        new Triangle(new Vector3D(1, 1, 1), new Vector3D(-1, -1, 1), new Vector3D(-1, 1, 1)),
                        //Up Face
                        new Triangle(new Vector3D(-1, -1, -1), new Vector3D(-1, -1, 1), new Vector3D(1, -1, 1)),
                        new Triangle(new Vector3D(-1, -1, -1), new Vector3D(1, -1, 1), new Vector3D(1, -1, -1)),
                        //Down Face
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, 1, -1), new Vector3D(1, 1, -1)),
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(1, 1, -1), new Vector3D(1, 1, 1))
                )
        );
        mesh.tris.addAll(triangles);
        updateRotationMatrices(); 
    }
    
    public void createCenteredCube() {
        
        this.offset = -10;

        ArrayList<Triangle> triangles = new ArrayList<>(
                Arrays.asList(
                        //cube 
                        //Front Face
                        new Triangle(new Vector3D(-1, 1, -1), new Vector3D(-1, -1, -1), new Vector3D(1, -1, -1)),
                        new Triangle(new Vector3D(-1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, 1, -1)),
                        //Right Face
                        new Triangle(new Vector3D(1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, -1, 1)),
                        new Triangle(new Vector3D(1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(1, 1, 1)),
                        //Left Face
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, -1, 1), new Vector3D(-1, -1, -1)),
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, -1, -1), new Vector3D(-1, 1, -1)),
                        //Behind Face
                        new Triangle(new Vector3D(1, 1, 1), new Vector3D(1, -1, 1), new Vector3D(-1, -1, 1)),
                        new Triangle(new Vector3D(1, 1, 1), new Vector3D(-1, -1, 1), new Vector3D(-1, 1, 1)),
                        //Up Face
                        new Triangle(new Vector3D(-1, -1, -1), new Vector3D(-1, -1, 1), new Vector3D(1, -1, 1)),
                        new Triangle(new Vector3D(-1, -1, -1), new Vector3D(1, -1, 1), new Vector3D(1, -1, -1)),
                        //Down Face
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(-1, 1, -1), new Vector3D(1, 1, -1)),
                        new Triangle(new Vector3D(-1, 1, 1), new Vector3D(1, 1, -1), new Vector3D(1, 1, 1))
                )
        );
        mesh.tris.addAll(triangles);
        updateRotationMatrices(); 
    }
}