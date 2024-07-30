Vector3D
```
class Vector3D{
    
    float x;
    float y;
    float z;
    
}
```

The Class Vector3D is used as an object to store the positional data (x,y,z coordinates) of a single point


Triangle 
```
class Triangle{

    Vector3D[] vector = new Vector3D[3];

}
```

The class Triangle is used as a way to connect 3 points in order to get the base shape of which all of the rendered shapes will be made of.
The reason for this base shape to be a triangle instead of any other 2d shape is because triangles are the 2d shape that requires the least amount of points to be formed which allows to make more complex shapes easily


```handdrawn-ink
{
	"versionAtEmbed": "0.2.4",
	"filepath": "Ink/Drawing/2024.7.27 - 21.45pm.drawing"
}
```
Mesh 

```
class Mesh{

    Vector <Triangle> tris;
    
}
```

The Mesh class is used to make 


```handdrawn-ink
{
	"versionAtEmbed": "0.2.4",
	"filepath": "Rendering/Rendering imgs/2024.7.27 - 21.50pm.drawing"
}
```
