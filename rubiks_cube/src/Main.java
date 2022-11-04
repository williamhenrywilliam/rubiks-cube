/* THINGS TO DO
figure out the cube constructor and where to initialize the variables for hte faces
put the instructions into a README.md
need to track the turns that are made. should i do that in the Cube calss or Main? how to do it so it resets everytime I run.
 */

public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube();

        cube.printCube();
        Pieces[] test = cube.getPiecesFrontFace();
        System.out.println(test[0].color);
        System.out.println(test[0].letter);



//        cube.printMove("Corner Swap Alg");
//        cube.edgeAlgI();
//        System.out.println(cube.getTurnList());
//        cube.printCube();

//        cube.printMove("Rotate Right Face");
//        cube.turnR();
//        cube.printCube();
//        cube.printMove("Rotate Right Face twice");
//        cube.turnR2();
//        cube.printCube();
//        cube.printMove("Rotate Right Inverse");
//        cube.turnRInverse();
//        cube.printCube();
//        cube.printMove("Rotate Left Face");
//        cube.turnL();
//        cube.printCube();
//        cube.printMove("Rotate Left Face twice");
//        cube.turnL2();
//        cube.printCube();
//        cube.printMove("Rotate Left Inverse");
//        cube.turnLInverse();
//        cube.printCube();
//        cube.printMove("Rotate Upper Face");
//        cube.turnU();
//        cube.printCube();
//        cube.printMove("Rotate Upper Face twice");
//        cube.turnU2();
//        cube.printCube();
//        cube.printMove("Rotate Upper Inverse ");
//        cube.turnUInverse();
//        cube.printCube();
//        cube.printMove("Rotate Down Face");
//        cube.turnD();
//        cube.printCube();
//        cube.printMove("Rotate Down Face twice");
//        cube.turnD2();
//        cube.printCube();
//        cube.printMove("Rotate Down Inverse ");
//        cube.turnDInverse();
//        cube.printCube();
//        cube.printMove("Rotate Front Face");
//        cube.turnF();
//        cube.printCube();
//        cube.printMove("Rotate Front Face twice");
//        cube.turnF2();
//        cube.printCube();
//        cube.printMove("Rotate Front Inverse ");
//        cube.turnFInverse();
//        cube.printCube();
//        cube.printMove("Rotate Back Face");
//        cube.turnB();
//        cube.printCube();
//        cube.printMove("Rotate Back Face twice");
//        cube.turnB2();
//        cube.printCube();
//        cube.printMove("Rotate Back Inverse ");
//        cube.turnBInverse();
//        cube.printCube();
//        cube.printMove("Rotate Middle Face");
//        cube.turnM();
//        cube.printCube();
//        cube.printMove("Rotate Middle Face twice");
//        cube.turnM2();
//        cube.printCube();
//        cube.printMove("Rotate Middle Inverse");
//        cube.turnMInverse();
//        cube.printCube();
//        System.out.println(cube.getTurnList());

//below is the last curly brace of main
    }
//below is the curly brace of the class
}
