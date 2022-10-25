/* THINGS TO DO
figure out the cube constructor and where to initialize the variables for hte faces

 */

public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube();

        cube.printCube();
        cube.printMove("Rotate Right Face");
        cube.turnR();
        cube.printCube();
        cube.printMove("Rotate Right Inverse Face twice");
        cube.turnRInverse();
        cube.turnRInverse();
        cube.printCube();
        cube.printMove("Rotate Left Face");
        cube.turnL();
        cube.printCube();
        cube.printMove("Rotate Left Inverse twice");
        cube.turnLInverse();
        cube.turnLInverse();
        cube.printCube();

//below is the last curly brace of main
    }
//below is the curly brace of the class
}
