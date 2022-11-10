package com.whycoding.rubikscube;
/* THINGS TO DO
figure out the cube constructor and where to initialize the variables for hte faces
put the instructions into a README.md
need to track the turns that are made. should i do that in the Cube calss or Main? how to do it so it resets everytime I run.
for cornersolver.... if hotseat is ever cornerA, cornerR, or cornerE, we will ahve to figure out how to grab unsolved corners (could have it solve random corners? or go through all of them? not hte most efficient, but would work?
make a default case for cornersolver
cornersolver is working. it might be the parity!
 */



public class Main {
    public static void main(String[] args) {
        Cube cube = new Cube();

        cube.printCube();
        cube.randomize();
        System.out.println("*************");
        cube.printCube();
        cube.solveCube();
        System.out.println("*************");
        cube.printCube();



        
//below is the last curly brace of main
    }
//below is the curly brace of the class
}
