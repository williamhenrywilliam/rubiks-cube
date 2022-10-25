/* THINGS TO DO
figure out the cube constructor and where to initialize the variables for hte faces
put the instructions into a README.md
need to track the turns that are made. should i do that in the Cube calss or Main? how to do it so it resets everytime I run.
 */

public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube();

        cube.printCube();


        cube.printMove("Rotate Right Face");
        cube.turnR();
        cube.printCube();
        cube.printMove("Rotate Right Face twice");
        cube.turnR2();
        cube.printCube();
        cube.printMove("Rotate Right Inverse");
        cube.turnRInverse();
        cube.printCube();
        cube.printMove("Rotate Left Face");
        cube.turnL();
        cube.printCube();
        cube.printMove("Rotate Left Face twice");
        cube.turnL2();
        cube.printCube();
        cube.printMove("Rotate Left Inverse");
        cube.turnLInverse();
        cube.printCube();
        cube.printMove("Rotate Upper Face");
        cube.turnU();
        cube.printCube();
        cube.printMove("Rotate Upper Face twice");
        cube.turnU2();
        cube.printCube();
        cube.printMove("Rotate Upper Inverse ");
        cube.turnUInverse();
        cube.printCube();
        cube.printMove("Rotate Down Face");
        cube.turnD();
        cube.printCube();
        cube.printMove("Rotate Down Face twice");
        cube.turnD2();
        cube.printCube();
        cube.printMove("Rotate Down Inverse ");
        cube.turnDInverse();
        cube.printCube();

//below is the last curly brace of main
    }
//below is the curly brace of the class
}
