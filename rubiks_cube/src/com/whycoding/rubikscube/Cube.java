package com.whycoding.rubikscube;

import java.lang.Math;

public class Cube {
    //variables
    /*Each face has 9 sides. the arrays have a length of 9. the orientation for the face is as if that face is currently facing you
        [0] is Top Left
        [1] is Top Middle
        [2] is Top Right
        [3] is Middle Left
        [4] is Middle Middle (aka the center piece)
        [5] is Middle Right
        [6] is Bottom Left
        [7] is Bottom Middle
        [8] is Bottom Right

     number the corners
     */

    //these arrays represent the actual faces of the cube while the program is running
    private Pieces[] frontFace = {Pieces.ICORNER, Pieces.IEDGE, Pieces.JCORNER, Pieces.LEDGE, Pieces.REDCENTER, Pieces.JEDGE, Pieces.LCORNER, Pieces.KEDGE, Pieces.KCORNER};
    private Pieces[] backFace = {Pieces.QCORNER, Pieces.QEDGE, Pieces.RCORNER, Pieces.TEDGE, Pieces.ORANGECENTER, Pieces.REDGE, Pieces.TCORNER, Pieces.SEDGE, Pieces.SCORNER};
    private Pieces[] leftFace = {Pieces.ECORNER, Pieces.EEDGE, Pieces.FCORNER, Pieces.HEDGE, Pieces.WHITECENTER, Pieces.FEDGE, Pieces.HCORNER, Pieces.GEDGE, Pieces.GCORNER};
    private Pieces[] rightFace = {Pieces.MCORNER, Pieces.MEDGE, Pieces.NCORNER, Pieces.PEDGE, Pieces.YELLOWCENTER, Pieces.NEDGE, Pieces.PCORNER, Pieces.OEDGE, Pieces.OCORNER};
    private Pieces[] upperFace = {Pieces.ACORNER, Pieces.AEDGE, Pieces.BCORNER, Pieces.DEDGE, Pieces.BLUECENTER, Pieces.BEDGE, Pieces.DCORNER, Pieces.CEDGE, Pieces.CCORNER};
    private Pieces[] downFace = {Pieces.UCORNER, Pieces.UEDGE, Pieces.VCORNER, Pieces.XEDGE, Pieces.GREENCENTER, Pieces.VEDGE, Pieces.XCORNER, Pieces.WEDGE, Pieces.WCORNER};
    //these arrays are temp arrays, that hold the state of each face during the turn methods
    private Pieces[] tempFrontFace = new Pieces[9];
    private Pieces[] tempBackFace = new Pieces[9];
    private Pieces[] tempRightFace = new Pieces[9];
    private Pieces[] tempLeftFace = new Pieces[9];
    private Pieces[] tempUpperFace = new Pieces[9];
    private Pieces[] tempDownFace = new Pieces[9];
    //these arrays are used when checking to see if the cube (or part of the cube is solved)
    private Pieces[] testFrontFace = {Pieces.ICORNER, Pieces.IEDGE, Pieces.JCORNER, Pieces.LEDGE, Pieces.REDCENTER, Pieces.JEDGE, Pieces.LCORNER, Pieces.KEDGE, Pieces.KCORNER};
    private Pieces[] testBackFace = {Pieces.QCORNER, Pieces.QEDGE, Pieces.RCORNER, Pieces.TEDGE, Pieces.ORANGECENTER, Pieces.REDGE, Pieces.TCORNER, Pieces.SEDGE, Pieces.SCORNER};
    private Pieces[] testLeftFace = {Pieces.ECORNER, Pieces.EEDGE, Pieces.FCORNER, Pieces.HEDGE, Pieces.WHITECENTER, Pieces.FEDGE, Pieces.HCORNER, Pieces.GEDGE, Pieces.GCORNER};
    private Pieces[] testRightFace = {Pieces.MCORNER, Pieces.MEDGE, Pieces.NCORNER, Pieces.PEDGE, Pieces.YELLOWCENTER, Pieces.NEDGE, Pieces.PCORNER, Pieces.OEDGE, Pieces.OCORNER};
    private Pieces[] testUpperFace = {Pieces.ACORNER, Pieces.AEDGE, Pieces.BCORNER, Pieces.DEDGE, Pieces.BLUECENTER, Pieces.BEDGE, Pieces.DCORNER, Pieces.CEDGE, Pieces.CCORNER};
    private Pieces[] testDownFace = {Pieces.UCORNER, Pieces.UEDGE, Pieces.VCORNER, Pieces.XEDGE, Pieces.GREENCENTER, Pieces.VEDGE, Pieces.XCORNER, Pieces.WEDGE, Pieces.WCORNER};

    private boolean cubeIsSolved;
    private boolean cornersAreSolved;
    private boolean edgesAreSolved;
    private boolean parityNeeded;
    private int parityCounter;
    private String turnList = "";
    private int numberOfTurnTypes = 12; //6 faces + inverses
    //TODO hotseats
    private Pieces cornerHotSeat;
    private Pieces edgeHotSeat;

    //constructor
    public Cube() {}

    //getters and setters
    public String getTurnList() {return turnList;}
    public void setTurnList(String turnList){this.turnList = turnList;}
    public Pieces[] getFrontFace() {return frontFace;}
    public Pieces[] getBackFace() {return backFace;}
    public Pieces[] getUpperFace() {return upperFace;}
    public Pieces[] getDownFace() {return downFace;}
    public Pieces[] getRightFace() {return rightFace;}
    public Pieces[] getLeftFace() {return leftFace;}
    public Pieces[] getTempFrontFace() {return tempFrontFace;}
    public Pieces[] getTempBackFace() {return tempBackFace;}
    public Pieces[] getTempUpperFace() {return tempUpperFace;}
    public Pieces[] getTempDownFace() {return tempDownFace;}
    public Pieces[] getTempRightFace() {return tempRightFace;}
    public Pieces[] getTempLeftFace() {return tempLeftFace;}
    public boolean isCubeIsSolved() {
        return cubeIsSolved;
    }
    public void setCubeIsSolved(boolean cubeIsSolved) {
        this.cubeIsSolved = cubeIsSolved;
    }
    public int getParityCounter() {
        return parityCounter;
    }
    public void setParityCounter(int parityCounter) {
        this.parityCounter = parityCounter;
    }

    //Methods
        //print methods
    public void printFace(Pieces[] faceArray){
        for (int i = 0; i < faceArray.length; i++) {
            System.out.print(faceArray[i].color);
            if(i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
    public void printCube(){
        System.out.println("Front Face:");
        printFace(frontFace);
        System.out.println();
        System.out.println("Back Face:");
        printFace(backFace);
        System.out.println();
        System.out.println("Left Face:");
        printFace(leftFace);
        System.out.println();
        System.out.println("Right Face:");
        printFace(rightFace);
        System.out.println();
        System.out.println("Upper Face:");
        printFace(upperFace);
        System.out.println();
        System.out.println("Down Face:");
        printFace(downFace);
        System.out.println();
    }
    public void printMove(String moveDescription) {
        System.out.println("**********************");
        System.out.println(moveDescription);
        System.out.println("**********************");
    }
        //Cube state
    public void randomize(){
        double selector;
        for (int i = 0; i < 20; i++) {
            selector = ((Math.random()) * numberOfTurnTypes) + 1;
            switch ((int) selector) {
                case 1:
                    turnR();
                    break;
                case 2:
                    turnRInverse();
                    break;
                case 3:
                    turnL();
                    break;
                case 4:
                    turnLInverse();
                    break;
                case 5:
                    turnF();
                    break;
                case 6:
                    turnFInverse();
                    break;
                case 7:
                    turnB();
                    break;
                case 8:
                    turnBInverse();
                    break;
                case 9:
                    turnU();
                    break;
                case 10:
                    turnUInverse();
                    break;
                case 11:
                    turnD();
                    break;
                case 12:
                    turnDInverse();
                    break;
                /*
                case 13:
                    turnM();
                    break;
                case 14:
                    turnMInverse();
                    break;
                 */
            }
        }
    }
    public boolean checkCubeIfSolved(){
        cubeIsSolved = false;
        if(
            getFrontFace() == testFrontFace
            && getBackFace() == testBackFace
            && getLeftFace() == testLeftFace
            && getRightFace() == testRightFace
            && getUpperFace() == testUpperFace
            && getDownFace() == testDownFace
        ){
            cubeIsSolved = true;
        }
        return cubeIsSolved;
    }
    public boolean checkCornersIfSolved(){
        cornersAreSolved = false;
        if(
            getFrontFace()[0] == testFrontFace[0]
            && getFrontFace()[2] == testFrontFace[2]
            && getFrontFace()[4] == testFrontFace[4]
            && getFrontFace()[6] == testFrontFace[6]
            && getFrontFace()[8] == testFrontFace[8]

            && getBackFace()[0] == testBackFace[0]
            && getBackFace()[2] == testBackFace[2]
            && getBackFace()[4] == testBackFace[4]
            && getBackFace()[6] == testBackFace[6]
            && getBackFace()[8] == testBackFace[8]

            && getLeftFace()[0] == testLeftFace[0]
            && getLeftFace()[2] == testLeftFace[2]
            && getLeftFace()[4] == testLeftFace[4]
            && getLeftFace()[6] == testLeftFace[6]
            && getLeftFace()[8] == testLeftFace[8]

            && getRightFace()[0] == testRightFace[0]
            && getRightFace()[2] == testRightFace[2]
            && getRightFace()[4] == testRightFace[4]
            && getRightFace()[6] == testRightFace[6]
            && getRightFace()[8] == testRightFace[8]

            && getUpperFace()[0] == testUpperFace[0]
            && getUpperFace()[2] == testUpperFace[2]
            && getUpperFace()[4] == testUpperFace[4]
            && getUpperFace()[6] == testUpperFace[6]
            && getUpperFace()[8] == testUpperFace[8]

            && getDownFace()[0] == testDownFace[0]
            && getDownFace()[2] == testDownFace[2]
            && getDownFace()[4] == testDownFace[4]
            && getDownFace()[6] == testDownFace[6]
            && getDownFace()[8] == testDownFace[8]
        ){
            cornersAreSolved = true;
        }
        return cornersAreSolved;
    }
    public boolean checkEdgesIfSolved(){
        edgesAreSolved = false;
        if(
            (getFrontFace()[1] == testFrontFace[1]
            && getFrontFace()[3] == testFrontFace[3]
            && getFrontFace()[4] == testFrontFace[4]
            && getFrontFace()[5] == testFrontFace[5]
            && getFrontFace()[7] == testFrontFace[7]

            && getBackFace()[1] == testBackFace[1]
            && getBackFace()[3] == testBackFace[3]
            && getBackFace()[4] == testBackFace[4]
            && getBackFace()[5] == testBackFace[5]
            && getBackFace()[7] == testBackFace[7]

            && getLeftFace()[1] == testLeftFace[1]
            && getLeftFace()[3] == testLeftFace[3]
            && getLeftFace()[4] == testLeftFace[4]
            && getLeftFace()[5] == testLeftFace[5]
            && getLeftFace()[7] == testLeftFace[7]

            && getRightFace()[1] == testRightFace[1]
            && getRightFace()[3] == testRightFace[3]
            && getRightFace()[4] == testRightFace[4]
            && getRightFace()[5] == testRightFace[5]
            && getRightFace()[7] == testRightFace[7]

            && getUpperFace()[1] == testUpperFace[1]
            && getUpperFace()[3] == testUpperFace[3]
            && getUpperFace()[4] == testUpperFace[4]
            && getUpperFace()[5] == testUpperFace[5]
            && getUpperFace()[7] == testUpperFace[7]

            && getDownFace()[1] == testDownFace[1]
            && getDownFace()[3] == testDownFace[3]
            && getDownFace()[4] == testDownFace[4]
            && getDownFace()[5] == testDownFace[5]
            && getDownFace()[7] == testDownFace[7])
            ||
            (getFrontFace()[1] == testBackFace[7]
            && getFrontFace()[3] == testFrontFace[3]
            && getFrontFace()[4] == testBackFace[4]
            && getFrontFace()[5] == testFrontFace[5]
            && getFrontFace()[7] == testFrontFace[7]

            && getBackFace()[1] == testBackFace[1]
            && getBackFace()[3] == testBackFace[3]
            && getBackFace()[4] == testFrontFace[4]
            && getBackFace()[5] == testBackFace[5]
            && getBackFace()[7] == testFrontFace[1]

            && getLeftFace()[1] == testLeftFace[1]
            && getLeftFace()[3] == testLeftFace[3]
            && getLeftFace()[4] == testLeftFace[4]
            && getLeftFace()[5] == testLeftFace[5]
            && getLeftFace()[7] == testLeftFace[7]

            && getRightFace()[1] == testRightFace[1]
            && getRightFace()[3] == testRightFace[3]
            && getRightFace()[4] == testRightFace[4]
            && getRightFace()[5] == testRightFace[5]
            && getRightFace()[7] == testRightFace[7]

            && getUpperFace()[1] == testUpperFace[1]
            && getUpperFace()[3] == testUpperFace[3]
            && getUpperFace()[4] == testDownFace[4]
            && getUpperFace()[5] == testUpperFace[5]
            && getUpperFace()[7] == testDownFace[7]

            && getDownFace()[1] == testDownFace[1]
            && getDownFace()[3] == testDownFace[3]
            && getDownFace()[4] == testUpperFace[4]
            && getDownFace()[5] == testDownFace[5]
            && getDownFace()[7] == testUpperFace[7])
        ){
            edgesAreSolved = true;
        }
        return edgesAreSolved;
    }
    //TODO check parity

    public void solveCorners(){
        boolean cornersAreSolved = checkCornersIfSolved();
        while(!cornersAreSolved){
            cornerHotSeat = getUpperFace()[0];
            switch (cornerHotSeat) {
                case BCORNER:
                    cornerAlgB();
                    break;
                case CCORNER:
                    cornerAlgC();
                    break;
                case DCORNER:
                    cornerAlgD();
                    break;
                case FCORNER:
                    cornerAlgF();
                    break;
                case GCORNER:
                    cornerAlgG();
                    break;
                case HCORNER:
                    cornerAlgH();
                    break;
                case ICORNER:
                    cornerAlgI();
                    break;
                case JCORNER:
                    cornerAlgJ();
                    break;
                case KCORNER:
                    cornerAlgK();
                    break;
                case LCORNER:
                    cornerAlgL();
                    break;
                case MCORNER:
                    cornerAlgM();
                    break;
                case NCORNER:
                    cornerAlgN();
                    break;
                case OCORNER:
                    cornerAlgO();
                    break;
                case PCORNER:
                    cornerAlgP();
                    break;
                case QCORNER:
                    cornerAlgQ();
                    break;
                case SCORNER:
                    cornerAlgS();
                    break;
                case TCORNER:
                    cornerAlgT();
                    break;
                case UCORNER:
                    cornerAlgU();
                    break;
                case VCORNER:
                    cornerAlgV();
                    break;
                case WCORNER:
                    cornerAlgW();
                    break;
                case XCORNER:
                    cornerAlgX();
                    break;
                default:
                    if(getUpperFace()[2] != Pieces.BCORNER){
                        cornerAlgB();
                    } else if (getUpperFace()[8] != Pieces.CCORNER){
                        cornerAlgC();
                    } else if (getUpperFace()[6] != Pieces.DCORNER){
                        cornerAlgD();
                    } else if (getDownFace()[0] != Pieces.UCORNER){
                        cornerAlgU();
                    } else if (getDownFace()[2] != Pieces.VCORNER){
                        cornerAlgV();
                    } else if (getDownFace()[8] != Pieces.WCORNER){
                        cornerAlgW();
                    } else if (getDownFace()[6] != Pieces.XCORNER){
                        cornerAlgX();
                    }
                    break;
            }
            cornersAreSolved = checkCornersIfSolved();
        }
    }
    public void solveEdges(){
        boolean edgesAreSolved = checkEdgesIfSolved();
        int counter = 1;
        while(!edgesAreSolved){
            edgeHotSeat = getDownFace()[1];
            switch (edgeHotSeat){
                case AEDGE:
                    edgeAlgA();
                    counter++;
                    break;
                case BEDGE:
                    edgeAlgB();
                    counter++;
                    break;
                case CEDGE:
                    if(counter % 2 == 1){
                        edgeAlgC();
                    }else{
                        edgeAlgW();
                    }
                    counter++;
                    break;
                case DEDGE:
                    edgeAlgD();
                    counter++;
                    break;
                case EEDGE:
                    edgeAlgE();
                    counter++;
                    break;
                case FEDGE:
                    edgeAlgF();
                    counter++;
                    break;
                case GEDGE:
                    edgeAlgG();
                    counter++;
                    break;
                case HEDGE:
                    edgeAlgH();
                    counter++;
                    break;
                case IEDGE:
                    if(counter % 2 == 1){
                        edgeAlgI();
                    }else{
                        edgeAlgS();
                    }
                    counter++;
                    break;
                case JEDGE:
                    edgeAlgJ();
                    counter++;
                    break;
                case LEDGE:
                    edgeAlgL();
                    counter++;
                    break;
                case MEDGE:
                    edgeAlgM();
                    counter++;
                    break;
                case NEDGE:
                    edgeAlgN();
                    counter++;
                    break;
                case OEDGE:
                    edgeAlgO();
                    counter++;
                    break;
                case PEDGE:
                    edgeAlgP();
                    counter++;
                    break;
                case QEDGE:
                    edgeAlgQ();
                    counter++;
                    break;
                case REDGE:
                    edgeAlgR();
                    counter++;
                    break;
                case SEDGE:
                    if(counter % 2 == 1){
                        edgeAlgS();
                    }else{
                        edgeAlgI();
                    }
                    counter++;
                    break;
                case TEDGE:
                    edgeAlgT();
                    counter++;
                    break;
                case VEDGE:
                    edgeAlgV();
                    counter++;
                    break;
                case WEDGE:
                    if(counter % 2 == 1){
                        edgeAlgW();
                    }else{
                        edgeAlgC();
                    }
                    counter++;
                    break;
                case XEDGE:
                    edgeAlgX();
                    counter++;
                    break;
                default:
                    if(getUpperFace()[1] != Pieces.AEDGE){
                        edgeAlgA();
                        counter++;
                    }else if(getUpperFace()[5] != Pieces.BEDGE){
                        edgeAlgB();
                        counter++;
                    }else if(getUpperFace()[7] != Pieces.CEDGE && counter % 2 == 1){
                        edgeAlgC();
                        counter++;
                    }else if(getDownFace()[7] != Pieces.CEDGE && counter % 2 == 0){
                        edgeAlgW();
                        counter++;
                    }
                    else if(getUpperFace()[3] != Pieces.DEDGE){
                        edgeAlgD();
                        counter++;
                    }else if(getFrontFace()[3] != Pieces.LEDGE){
                        edgeAlgL();
                        counter++;
                    }else if(getFrontFace()[5] != Pieces.JEDGE){
                        edgeAlgJ();
                        counter++;
                    }else if(getBackFace()[5] != Pieces.REDGE){
                        edgeAlgR();
                        counter++;
                    }else if(getBackFace()[3] != Pieces.TEDGE){
                        edgeAlgT();
                        counter++;
                    }else if(getDownFace()[3] != Pieces.XEDGE){
                        edgeAlgX();
                        counter++;
                    }else if(getDownFace()[5] != Pieces.VEDGE){
                        edgeAlgV();
                        counter++;
                    }else if(getDownFace()[7] != Pieces.WEDGE && counter % 2 == 1){
                        edgeAlgW();
                        counter++;
                    }else if(getUpperFace()[7] != Pieces.WEDGE && counter % 2 == 0){
                        edgeAlgC();
                        counter++;
                    }
                    break;
            }
            edgesAreSolved = checkEdgesIfSolved();
        }
//        if(counter % 2 == 1) {
//            paritySwap();
//        }
        setParityCounter(counter);
    }
    public void solveCube(){
        solveCorners();
        solveEdges();
    }

    //Turn Methods
    public void turnR(){
        //Right turn affects 5 faces of the cube, all but the Left face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < rightFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempRightFace[i] = rightFace[i];
            tempUpperFace[i] = upperFace[i];
            tempDownFace[i] = downFace[i];
        }
        /* Right face turn 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5 (unchanged always)
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempRightFace[0] = rightFace[6];
        tempRightFace[1] = rightFace[3];
        tempRightFace[2] = rightFace[0];
        tempRightFace[3] = rightFace[7];
        tempRightFace[5] = rightFace[1];
        tempRightFace[7] = rightFace[5];
        tempRightFace[6] = rightFace[8];
        tempRightFace[8] = rightFace[2];
        /*Front face turn 3,6,9
        3 = Down 3
        6 = Down 6
        9 = Down 9
        subtract 1 from all for array indexing
         */
        tempFrontFace[2] = downFace[2];
        tempFrontFace[5] = downFace[5];
        tempFrontFace[8] = downFace[8];
        /*Back face turn 1,4,7
        1 = Upper 9
        4 = Upper 6
        7 = Upper 3
        subtract 1 from all for array indexing
         */
        tempBackFace[0] = upperFace[8];
        tempBackFace[3] = upperFace[5];
        tempBackFace[6] = upperFace[2];
        /*Upper face turn 3,6,9
        3 = Front 3
        6 = Front 6
        9 = Front 9
        subtract 1 from all for array indexing
         */
        tempUpperFace[2] = frontFace[2];
        tempUpperFace[5] = frontFace[5];
        tempUpperFace[8] = frontFace[8];
        /*Down face turn 3,6,9
        3 = Back 7
        6 = Back 4
        9 = Back 1
        subtract 1 from all for array indexing
         */
        tempDownFace[2] = backFace[6];
        tempDownFace[5] = backFace[3];
        tempDownFace[8] = backFace[0];
        //update the original faces to match the temp ones
        for (int i=0; i < rightFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            rightFace[i] = tempRightFace[i];
            upperFace[i] = tempUpperFace[i];
            downFace[i] = tempDownFace[i];
        }
        turnList += "R,";
    }
    public void turnR2(){
        turnR();
        turnR();
    }
    public void turnRInverse(){
        //Right inverse turn affects 5 faces of the cube, all but the Left face
        for (int i=0; i < rightFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempRightFace[i] = rightFace[i];
            tempUpperFace[i] = upperFace[i];
            tempDownFace[i] = downFace[i];
        }
        /* Right face inverse turn 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (unchanged)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempRightFace[0] = rightFace[2];
        tempRightFace[1] = rightFace[5];
        tempRightFace[2] = rightFace[8];
        tempRightFace[3] = rightFace[1];
        tempRightFace[5] = rightFace[7];
        tempRightFace[6] = rightFace[0];
        tempRightFace[7] = rightFace[3];
        tempRightFace[8] = rightFace[6];
        /*Front face turn 3,6,9
        3 = Upper 3
        6 = Upper 6
        9 = Upper 9
        subtract 1 from all for array indexing
         */
        tempFrontFace[2] = upperFace[2];
        tempFrontFace[5] = upperFace[5];
        tempFrontFace[8] = upperFace[8];
        /*Back face turn 1,4,7
        1 = Down 9
        4 = Down 6
        7 = Down 3
        subtract 1 from all for array indexing
         */
        tempBackFace[0] = downFace[8];
        tempBackFace[3] = downFace[5];
        tempBackFace[6] = downFace[2];
        /*Upper face turn 3,6,9
        3 = Back 7
        6 = Back 4
        9 = Back 1
        subtract 1 from all for array indexing
         */
        tempUpperFace[2] = backFace[6];
        tempUpperFace[5] = backFace[3];
        tempUpperFace[8] = backFace[0];
        /*Down face turn 3,6,9
        3 = Front 3
        6 = Front 6
        9 = Front 9
        subtract 1 from all for array indexing
         */
        tempDownFace[2] = frontFace[2];
        tempDownFace[5] = frontFace[5];
        tempDownFace[8] = frontFace[8];
        //update the original faces to match the temp ones
        for (int i=0; i < rightFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            rightFace[i] = tempRightFace[i];
            upperFace[i] = tempUpperFace[i];
            downFace[i] = tempDownFace[i];
        }
        turnList += "R',";
    }
    public void turnL(){
        //Left turn affects 5 faces of the cube, all but the Right face
        for (int i=0; i < leftFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempDownFace[i] = downFace[i];
        }
        /* Left 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempLeftFace[0] = leftFace[6];
        tempLeftFace[1] = leftFace[3];
        tempLeftFace[2] = leftFace[0];
        tempLeftFace[3] = leftFace[7];
        tempLeftFace[5] = leftFace[1];
        tempLeftFace[6] = leftFace[8];
        tempLeftFace[7] = leftFace[5];
        tempLeftFace[8] = leftFace[2];
        /*Front face turn 1,4,7
        1 = Upper 1
        4 = Upper 4
        7 = Upper 7
        subtract 1 from all for array indexing
         */
        tempFrontFace[0] = upperFace[0];
        tempFrontFace[3] = upperFace[3];
        tempFrontFace[6] = upperFace[6];
        /*Back face turn 3,6,9
        3 = Down 7
        6 = Down 4
        9 = Down 1
        subtract 1 from all for array indexing
         */
        tempBackFace[2] = downFace[6];
        tempBackFace[5] = downFace[3];
        tempBackFace[8] = downFace[0];
        /*Upper face 1,4,7
        1 = Back 9
        4 = Back 6
        7 = Back 3
        subtract 1 from all for array indexing
         */
        tempUpperFace[0] = backFace[8];
        tempUpperFace[3] = backFace[5];
        tempUpperFace[6] = backFace[2];
        /*Down face 1,4,7
        1 = Front 1
        4 = Front 4
        7 = Front 7
        subtract 1 from all for array indexing
         */
        tempDownFace[0] = frontFace[0];
        tempDownFace[3] = frontFace[3];
        tempDownFace[6] = frontFace[6];
        //update the original faces to match the temp ones
        for (int i=0; i < leftFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            downFace[i] = tempDownFace[i];
        }
        turnList += "L,";
    }
    public void turnL2(){
        turnL();
        turnL();
    }
    public void turnLInverse(){
        //Left Inverse turn affects 5 faces of the cube, all but the Right face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < leftFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempDownFace[i] = downFace[i];
        }
        /* Left turn 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (skip)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempLeftFace[0] = leftFace[2];
        tempLeftFace[1] = leftFace[5];
        tempLeftFace[2] = leftFace[8];
        tempLeftFace[3] = leftFace[1];
        tempLeftFace[5] = leftFace[7];
        tempLeftFace[6] = leftFace[0];
        tempLeftFace[7] = leftFace[3];
        tempLeftFace[8] = leftFace[6];
        /*Front face turn 1,4,7
        1 = Down 1
        4 = Down 4
        7 = Down 7
        subtract 1 from all for array indexing
         */
        tempFrontFace[0] = downFace[0];
        tempFrontFace[3] = downFace[3];
        tempFrontFace[6] = downFace[6];
        /*Back face turn 3,6,9
        3 = Upper 7
        6 = Upper 4
        9 = Upper 1
        subtract 1 from all for array indexing
         */
        tempBackFace[2] = upperFace[6];
        tempBackFace[5] = upperFace[3];
        tempBackFace[8] = upperFace[0];
        /*Upper face 1,4,7
        1 = Front 1
        4 = Front 4
        7 = Front 7
        subtract 1 from all for array indexing
         */
        tempUpperFace[0] = frontFace[0];
        tempUpperFace[3] = frontFace[3];
        tempUpperFace[6] = frontFace[6];
        /*Down face 1,4,7
        1 = Back 9
        4 = Back 6
        7 = Back 3
        subtract 1 from all for array indexing
         */
        tempDownFace[0] = backFace[8];
        tempDownFace[3] = backFace[5];
        tempDownFace[6] = backFace[2];
        //update the original faces to match the temp ones
        for (int i=0; i < leftFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            downFace[i] = tempDownFace[i];
        }
        turnList += "L',";
    }
    public void turnU(){
        //Upper turn affects 5 faces of the cube, all but the Down face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < upperFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Upper 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempUpperFace[0] = upperFace[6];
        tempUpperFace[1] = upperFace[3];
        tempUpperFace[2] = upperFace[0];
        tempUpperFace[3] = upperFace[7];
        tempUpperFace[5] = upperFace[1];
        tempUpperFace[6] = upperFace[8];
        tempUpperFace[7] = upperFace[5];
        tempUpperFace[8] = upperFace[2];
        /*Front face turn 1,2,3
        1 = Right 1
        2 = Right 2
        3 = Right 3
        subtract 1 from all for array indexing
         */
        tempFrontFace[0] = rightFace[0];
        tempFrontFace[1] = rightFace[1];
        tempFrontFace[2] = rightFace[2];
        /*Back face 1,2,3
        1 = Left 1
        2 = Left 2
        3 = Left 3
        subtract 1 from all for array indexing
         */
        tempBackFace[0] = leftFace[0];
        tempBackFace[1] = leftFace[1];
        tempBackFace[2] = leftFace[2];
        /*Right face 1,2,3
        1 = Back 1
        2 = Back 2
        3 = Back 3
        subtract 1 from all for array indexing
         */
        tempRightFace[0] = backFace[0];
        tempRightFace[1] = backFace[1];
        tempRightFace[2] = backFace[2];
        /*Left face 1,2,3
        1 = Front 1
        2 = Front 2
        3 = Front 3
        subtract 1 from all for array indexing
         */
        tempLeftFace[0] = frontFace[0];
        tempLeftFace[1] = frontFace[1];
        tempLeftFace[2] = frontFace[2];
        //update the original faces to match the temp ones
        for (int i=0; i < upperFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "U,";
    }
    public void turnU2(){
        turnU();
        turnU();
    }
    public void turnUInverse(){
        //Upper Inverse turn affects 5 faces of the cube, all but the Down face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < upperFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Upper 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (unchanged)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempUpperFace[0] = upperFace[2];
        tempUpperFace[1] = upperFace[5];
        tempUpperFace[2] = upperFace[8];
        tempUpperFace[3] = upperFace[1];
        tempUpperFace[5] = upperFace[7];
        tempUpperFace[6] = upperFace[0];
        tempUpperFace[7] = upperFace[3];
        tempUpperFace[8] = upperFace[6];
        /*Front face 1,2,3
        1 = Left 1
        2 = Left 2
        3 = Left 3
        subtract 1 from all for array indexing
         */
        tempFrontFace[0] = leftFace[0];
        tempFrontFace[1] = leftFace[1];
        tempFrontFace[2] = leftFace[2];
        /*Back face 1,2,3
        1 = Right 1
        2 = Right 2
        3 = Right 3
        subtract 1 from all for array indexing
         */
        tempBackFace[0] = rightFace[0];
        tempBackFace[1] = rightFace[1];
        tempBackFace[2] = rightFace[2];
        /*Right face 1,2,3
        1 = Front 1
        2 = Front 2
        3 = Front 3
        subtract 1 from all for array indexing
         */
        tempRightFace[0] = frontFace[0];
        tempRightFace[1] = frontFace[1];
        tempRightFace[2] = frontFace[2];
        /*Left face 1,2,3
        1 = Back 1
        2 = Back 2
        3 = Back 3
        subtract 1 from all for array indexing
         */
        tempLeftFace[0] = backFace[0];
        tempLeftFace[1] = backFace[1];
        tempLeftFace[2] = backFace[2];
        //update the original faces to match the temp ones
        for (int i=0; i < upperFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "U',";
    }
    public void turnD(){
        //Down turn affects 5 faces of the cube, all but the Upper face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < downFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempDownFace[i] = downFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Down 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempDownFace[0] = downFace[6];
        tempDownFace[1] = downFace[3];
        tempDownFace[2] = downFace[0];
        tempDownFace[3] = downFace[7];
        tempDownFace[5] = downFace[1];
        tempDownFace[6] = downFace[8];
        tempDownFace[7] = downFace[5];
        tempDownFace[8] = downFace[2];
        /*Front face turn 7,8,9
        7 = Left 7
        8 = Left 8
        9 = Left 9
        subtract 1 from all for array indexing
         */
        tempFrontFace[6] = leftFace[6];
        tempFrontFace[7] = leftFace[7];
        tempFrontFace[8] = leftFace[8];
        /*Back face 7,8,9
        7 = Right 7
        8 = Right 8
        9 = Right 9
        subtract 1 from all for array indexing
         */
        tempBackFace[6] = rightFace[6];
        tempBackFace[7] = rightFace[7];
        tempBackFace[8] = rightFace[8];
        /*Right face 7,8,9
        7 = Front 7
        8 = Front 8
        9 = Front 9
        subtract 1 from all for array indexing
         */
        tempRightFace[6] = frontFace[6];
        tempRightFace[7] = frontFace[7];
        tempRightFace[8] = frontFace[8];
        /*Left face 7,8,9
        7 = Back 7
        8 = Back 8
        9 = Back 9
        subtract 1 from all for array indexing
         */
        tempLeftFace[6] = backFace[6];
        tempLeftFace[7] = backFace[7];
        tempLeftFace[8] = backFace[8];
        //update the original faces to match the temp ones
        for (int i=0; i < downFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            downFace[i] = tempDownFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "D,";
    }
    public void turnD2(){
        turnD();
        turnD();
    }
    public void turnDInverse(){
        //Down Inverse turn affects 5 faces of the cube, all but the Upper face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < downFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempBackFace[i] = backFace[i];
            tempLeftFace[i] = leftFace[i];
            tempDownFace[i] = downFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Down 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (unchanged)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempDownFace[0] = downFace[2];
        tempDownFace[1] = downFace[5];
        tempDownFace[2] = downFace[8];
        tempDownFace[3] = downFace[1];
        tempDownFace[5] = downFace[7];
        tempDownFace[6] = downFace[0];
        tempDownFace[7] = downFace[3];
        tempDownFace[8] = downFace[6];
        /*Front face 7,8,9
        7 = Right 7
        8 = Right 8
        9 = Right 9
        subtract 1 from all for array indexing
         */
        tempFrontFace[6] = rightFace[6];
        tempFrontFace[7] = rightFace[7];
        tempFrontFace[8] = rightFace[8];
        /*Back face 7,8,9
        7 = Left 7
        8 = Left 8
        9 = Left 9
        subtract 1 from all for array indexing
         */
        tempBackFace[6] = leftFace[6];
        tempBackFace[7] = leftFace[7];
        tempBackFace[8] = leftFace[8];
        /*Right face 7,8,9
        7 = Back 7
        8 = Back 8
        9 = Back 9
        subtract 1 from all for array indexing
         */
        tempRightFace[6] = backFace[6];
        tempRightFace[7] = backFace[7];
        tempRightFace[8] = backFace[8];
        /*Left face 7,8,9
        7 = Front 7
        8 = Front 8
        9 = Front 9
        subtract 1 from all for array indexing
         */
        tempLeftFace[6] = frontFace[6];
        tempLeftFace[7] = frontFace[7];
        tempLeftFace[8] = frontFace[8];
        //update the original faces to match the temp ones
        for (int i=0; i < downFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            backFace[i] = tempBackFace[i];
            leftFace[i] = tempLeftFace[i];
            downFace[i] = tempDownFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "D',";
    }
    public void turnF(){
        //Front turn affects 5 faces of the cube, all but the Back face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < frontFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempDownFace[i] = downFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Front 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempFrontFace[0] = frontFace[6];
        tempFrontFace[1] = frontFace[3];
        tempFrontFace[2] = frontFace[0];
        tempFrontFace[3] = frontFace[7];
        tempFrontFace[5] = frontFace[1];
        tempFrontFace[6] = frontFace[8];
        tempFrontFace[7] = frontFace[5];
        tempFrontFace[8] = frontFace[2];
        /*Upper face 7,8,9
        7 = Left 9
        8 = Left 6
        9 = Left 3
        subtract 1 from all for array indexing
         */
        tempUpperFace[6] = leftFace[8];
        tempUpperFace[7] = leftFace[5];
        tempUpperFace[8] = leftFace[2];
        /*Down face 1,2,3
        1 = Right 7
        2 = Right 4
        3 = Right 1
        subtract 1 from all for array indexing
         */
        tempDownFace[0] = rightFace[6];
        tempDownFace[1] = rightFace[3];
        tempDownFace[2] = rightFace[0];
        /*Right face 1,4,7
        1 = Upper 7
        4 = Upper 8
        7 = Upper 9
        subtract 1 from all for array indexing
         */
        tempRightFace[0] = upperFace[6];
        tempRightFace[3] = upperFace[7];
        tempRightFace[6] = upperFace[8];
        /*Left face 3,6,9
        3 = Down 1
        6 = Down 2
        9 = Down 3
        subtract 1 from all for array indexing
         */
        tempLeftFace[2] = downFace[0];
        tempLeftFace[5] = downFace[1];
        tempLeftFace[8] = downFace[2];
        //update the original faces to match the temp ones
        for (int i=0; i < frontFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            downFace[i] = tempDownFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "F,";
    }
    public void turnF2() {
        turnF();
        turnF();
    }
    public void turnFInverse(){
        //Front Inverse turn affects 5 faces of the cube, all but the Back face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < frontFace.length; i++) {
            tempFrontFace[i] = frontFace[i];
            tempDownFace[i] = downFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Front 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (unchanged)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempFrontFace[0] = frontFace[2];
        tempFrontFace[1] = frontFace[5];
        tempFrontFace[2] = frontFace[8];
        tempFrontFace[3] = frontFace[1];
        tempFrontFace[5] = frontFace[7];
        tempFrontFace[6] = frontFace[0];
        tempFrontFace[7] = frontFace[3];
        tempFrontFace[8] = frontFace[6];
        /*Upper face 7,8,9
        7 = Right 1
        8 = Right 4
        9 = Right 7
        subtract 1 from all for array indexing
         */
        tempUpperFace[6] = rightFace[0];
        tempUpperFace[7] = rightFace[3];
        tempUpperFace[8] = rightFace[6];
        /*Down face 1,2,3
        1 = Left 3
        2 = Left 6
        3 = Left 9
        subtract 1 from all for array indexing
         */
        tempDownFace[0] = leftFace[2];
        tempDownFace[1] = leftFace[5];
        tempDownFace[2] = leftFace[8];
        /*Right face 1,4,7
        1 = Down 3
        4 = Down 2
        7 = Down 1
        subtract 1 from all for array indexing
         */
        tempRightFace[0] = downFace[2];
        tempRightFace[3] = downFace[1];
        tempRightFace[6] = downFace[0];
        /*Left face 3,6,9
        3 = Upper 9
        6 = Upper 8
        9 = Upper 7
        subtract 1 from all for array indexing
         */
        tempLeftFace[2] = upperFace[8];
        tempLeftFace[5] = upperFace[7];
        tempLeftFace[8] = upperFace[6];
        //update the original faces to match the temp ones
        for (int i=0; i < frontFace.length; i++) {
            frontFace[i] = tempFrontFace[i];
            downFace[i] = tempDownFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "F',";
    }
    public void turnB(){
        //Back turn affects 5 faces of the cube, all but the Front face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < backFace.length; i++) {
            tempBackFace[i] = backFace[i];
            tempDownFace[i] = downFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Back 1,2,3,4,5,6,7,8,9
        1 = 7
        2 = 4
        3 = 1
        4 = 8
        5 = 5
        6 = 2
        7 = 9
        8 = 6
        9 = 3
        subtract 1 from all for array indexing
        */
        tempBackFace[0] = backFace[6];
        tempBackFace[1] = backFace[3];
        tempBackFace[2] = backFace[0];
        tempBackFace[3] = backFace[7];
        tempBackFace[5] = backFace[1];
        tempBackFace[6] = backFace[8];
        tempBackFace[7] = backFace[5];
        tempBackFace[8] = backFace[2];
        /*Upper face 1,2,3
        1 = Right 3
        2 = Right 6
        3 = Right 9
        subtract 1 from all for array indexing
         */
        tempUpperFace[0] = rightFace[2];
        tempUpperFace[1] = rightFace[5];
        tempUpperFace[2] = rightFace[8];
        /*Down face 7,8,9
        7 = Left 1
        8 = Left 4
        9 = Left 7
        subtract 1 from all for array indexing
         */
        tempDownFace[6] = leftFace[0];
        tempDownFace[7] = leftFace[3];
        tempDownFace[8] = leftFace[6];
        /*Right face 3,6,9
        3 = Down 9
        6 = Down 8
        9 = Down 7
        subtract 1 from all for array indexing
         */
        tempRightFace[2] = downFace[8];
        tempRightFace[5] = downFace[7];
        tempRightFace[8] = downFace[6];
        /*Left face 1,4,7
        1 = Upper 3
        4 = Upper 2
        7 = Upper 1
        subtract 1 from all for array indexing
         */
        tempLeftFace[0] = upperFace[2];
        tempLeftFace[3] = upperFace[1];
        tempLeftFace[6] = upperFace[0];
        //update the original faces to match the temp ones
        for (int i=0; i < backFace.length; i++) {
            backFace[i] = tempBackFace[i];
            downFace[i] = tempDownFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "B,";
    }
    public void turnB2() {
        turnB();
        turnB();
    }
    public void turnBInverse(){
        //Back Inverse turn affects 5 faces of the cube, all but the Front face
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < backFace.length; i++) {
            tempBackFace[i] = backFace[i];
            tempDownFace[i] = downFace[i];
            tempLeftFace[i] = leftFace[i];
            tempUpperFace[i] = upperFace[i];
            tempRightFace[i] = rightFace[i];
        }
        /* Back 1,2,3,4,5,6,7,8,9
        1 = 3
        2 = 6
        3 = 9
        4 = 2
        5 = 5 (unchanged)
        6 = 8
        7 = 1
        8 = 4
        9 = 7
        subtract 1 from all for array indexing
        */
        tempBackFace[0] = backFace[2];
        tempBackFace[1] = backFace[5];
        tempBackFace[2] = backFace[8];
        tempBackFace[3] = backFace[1];
        tempBackFace[5] = backFace[7];
        tempBackFace[6] = backFace[0];
        tempBackFace[7] = backFace[3];
        tempBackFace[8] = backFace[6];
        /*Upper face 1,2,3
        1 = Left 7
        2 = Left 4
        3 = Left 1
        subtract 1 from all for array indexing
         */
        tempUpperFace[0] = leftFace[6];
        tempUpperFace[1] = leftFace[3];
        tempUpperFace[2] = leftFace[0];
        /*Down face 7,8,9
        7 = Right 9
        8 = Right 6
        9 = Right 3
        subtract 1 from all for array indexing
         */
        tempDownFace[6] = rightFace[8];
        tempDownFace[7] = rightFace[5];
        tempDownFace[8] = rightFace[2];
        /*Right face 3,6,9
        3 = Upper 1
        6 = Upper 2
        9 = Upper 3
        subtract 1 from all for array indexing
         */
        tempRightFace[2] = upperFace[0];
        tempRightFace[5] = upperFace[1];
        tempRightFace[8] = upperFace[2];
        /*Left face 1,4,7
        1 = Down 7
        4 = Down 8
        7 = Down 9
        subtract 1 from all for array indexing
         */
        tempLeftFace[0] = downFace[6];
        tempLeftFace[3] = downFace[7];
        tempLeftFace[6] = downFace[8];
        //update the original faces to match the temp ones
        for (int i=0; i < backFace.length; i++) {
            backFace[i] = tempBackFace[i];
            downFace[i] = tempDownFace[i];
            leftFace[i] = tempLeftFace[i];
            upperFace[i] = tempUpperFace[i];
            rightFace[i] = tempRightFace[i];
        }
        turnList += "B',";
    }
    public void turnM(){
        //Middle turn affects 4 faces of the cube, all but the left and right faces
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < frontFace.length; i++) {
            tempBackFace[i] = backFace[i];
            tempDownFace[i] = downFace[i];
            tempUpperFace[i] = upperFace[i];
            tempFrontFace[i] = frontFace[i];
        }
        /* Front 2,5,8
        2 = Upper 2
        5 = Upper 5
        8 = Upper 8
        subtract 1 from all for array indexing
        */
        tempFrontFace[1] = upperFace[1];
        tempFrontFace[4] = upperFace[4];
        tempFrontFace[7] = upperFace[7];
        /*Upper face 2,5,8
        2 = Back 8
        5 = Back 5
        8 = Back 2
        subtract 1 from all for array indexing
         */
        tempUpperFace[1] = backFace[7];
        tempUpperFace[4] = backFace[4];
        tempUpperFace[7] = backFace[1];
        /*Down face 2,5,8
        2 = Front 2
        5 = Front 5
        8 = Front 8
        subtract 1 from all for array indexing
         */
        tempDownFace[1] = frontFace[1];
        tempDownFace[4] = frontFace[4];
        tempDownFace[7] = frontFace[7];
        /*Back face 2,5,8
        2 = Down 8
        5 = Down 5
        8 = Down 2
        subtract 1 from all for array indexing
         */
        tempBackFace[1] = downFace[7];
        tempBackFace[4] = downFace[4];
        tempBackFace[7] = downFace[1];
        //update the original faces to match the temp ones
        for (int i=0; i < backFace.length; i++) {
            backFace[i] = tempBackFace[i];
            downFace[i] = tempDownFace[i];
            upperFace[i] = tempUpperFace[i];
            frontFace[i] = tempFrontFace[i];
        }
        turnList += "M,";
    }
    public void turnM2(){
        turnM();
        turnM();
    }
    public void turnMInverse(){
        //Middle Inverse turn affects 4 faces of the cube, all but the left and right faces
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < frontFace.length; i++) {
            tempBackFace[i] = backFace[i];
            tempDownFace[i] = downFace[i];
            tempUpperFace[i] = upperFace[i];
            tempFrontFace[i] = frontFace[i];
        }
        /* Front 2,5,8
        2 = Down 2
        5 = Down 5
        8 = Down 8
        subtract 1 from all for array indexing
        */
        tempFrontFace[1] = downFace[1];
        tempFrontFace[4] = downFace[4];
        tempFrontFace[7] = downFace[7];
        /*Upper face 2,5,8
        2 = Front 2
        5 = Front 5
        8 = Front 8
        subtract 1 from all for array indexing
         */
        tempUpperFace[1] = frontFace[1];
        tempUpperFace[4] = frontFace[4];
        tempUpperFace[7] = frontFace[7];
        /*Down face 2,5,8
        2 = Back 8
        5 = Back 5
        8 = Back 2
        subtract 1 from all for array indexing
         */
        tempDownFace[1] = backFace[7];
        tempDownFace[4] = backFace[4];
        tempDownFace[7] = backFace[1];
        /*Back face 2,5,8
        2 = Upper 8
        5 = Upper 5
        8 = Upper 2
        subtract 1 from all for array indexing
         */
        tempBackFace[1] = upperFace[7];
        tempBackFace[4] = upperFace[4];
        tempBackFace[7] = upperFace[1];
        //update the original faces to match the temp ones
        for (int i=0; i < backFace.length; i++) {
            backFace[i] = tempBackFace[i];
            downFace[i] = tempDownFace[i];
            upperFace[i] = tempUpperFace[i];
            frontFace[i] = tempFrontFace[i];
        }
        turnList += "M',";
    }

    //Algorithms
        //general
    public void cornerSwap(){
        turnR();
        turnUInverse();
        turnRInverse();
        turnUInverse();
        turnR();
        turnU();
        turnRInverse();
        turnFInverse();
        turnR();
        turnU();
        turnRInverse();
        turnUInverse();
        turnRInverse();
        turnF();
        turnR();
    }
    public void edgeSwap(){
        turnM2();
    }
    public void paritySwap(){
        turnDInverse();
        turnL2();
        turnD();
        turnM2();
        turnDInverse();
        turnL2();
        turnD();
    }
    public void starU(){
        turnU();
        turnR2();
        turnUInverse();
    }

        //Algs for the  corner pieces. These algs consist of: 1. set-up moves, 2. the cornerSwap alg, 3. reversing the set-up moves.
        //there is  no agl for A,E, or R because the method solves all other pieces first and then A is inherently solved
    public void cornerAlgB(){
        turnR();
        turnDInverse();
        cornerSwap();
        turnD();
        turnRInverse();
    }
    public void cornerAlgC(){
        turnF();
        cornerSwap();
        turnF();
    }
    public void cornerAlgD(){
        turnF();
        turnRInverse();
        cornerSwap();
        turnR();
        turnFInverse();
    }
    public void cornerAlgF(){
        turnF2();
        cornerSwap();
        turnF2();
    }
    public void cornerAlgG(){
        turnF2();
        turnRInverse();
        cornerSwap();
        turnR();
        turnF2();
    }
    public void cornerAlgH(){
        turnD2();
        cornerSwap();
        turnD2();
    }
    public void cornerAlgI(){
        turnFInverse();
        turnD();
        cornerSwap();
        turnDInverse();
        turnF();
    }
    public void cornerAlgJ(){
        turnF2();
        turnD();
        cornerSwap();
        turnDInverse();
        turnF2();
    }
    public void cornerAlgK(){
        turnF();
        turnD();
        cornerSwap();
        turnDInverse();
        turnFInverse();
    }
    public void cornerAlgL(){
        turnD();
        cornerSwap();
        turnDInverse();
    }
    public void cornerAlgM(){
        turnRInverse();
        cornerSwap();
        turnR();
    }
    public void cornerAlgN(){
        turnR2();
        cornerSwap();
        turnR2();
    }
    public void cornerAlgO(){
        turnR();
        cornerSwap();
        turnRInverse();
    }
    public void cornerAlgP(){
        cornerSwap();
    }
    public void cornerAlgQ(){
        turnRInverse();
        turnF();
        cornerSwap();
        turnFInverse();
        turnR();
    }
    public void cornerAlgS(){
        turnDInverse();
        turnR();
        cornerSwap();
        turnRInverse();
        turnD();
    }
    public void cornerAlgT(){
        turnDInverse();
        cornerSwap();
        turnD();
    }
    public void cornerAlgU(){
        turnFInverse();
        cornerSwap();
        turnF();
    }
    public void cornerAlgV(){
        turnDInverse();
        turnFInverse();
        cornerSwap();
        turnF();
        turnD();
    }
    public void cornerAlgW(){
        turnD2();
        turnFInverse();
        cornerSwap();
        turnF();
        turnD2();
    }
    public void cornerAlgX(){
        turnD();
        turnFInverse();
        cornerSwap();
        turnF();
        turnDInverse();
    }

        //Algs for the edge pieces. set up moves, edgeswap alg, reverse set up move. there is no alg for U and K because it is the last piece to be solved
        //the Algs for S,I,W,C and Q are special.
    public void edgeAlgA(){
        edgeSwap();
    }
    public void edgeAlgB(){
        turnRInverse();
        turnU();
        turnR();
        turnUInverse();
        edgeSwap();
        turnU();
        turnRInverse();
        turnUInverse();
        turnR();
    }
    public void edgeAlgC(){
        turnU2();
        turnMInverse();
        turnU2();
        turnMInverse();
    }
    public void edgeAlgD(){
        turnL();
        turnUInverse();
        turnLInverse();
        turnU();
        edgeSwap();
        turnUInverse();
        turnL();
        turnU();
        turnLInverse();
    }
    public void edgeAlgE(){
        turnB();
        turnLInverse();
        turnBInverse();
        edgeSwap();
        turnB();
        turnL();
        turnBInverse();
    }
    public void edgeAlgF(){
        turnB();
        turnL2();
        turnBInverse();
        edgeSwap();
        turnB();
        turnL2();
        turnBInverse();
    }
    public void edgeAlgG(){
        turnB();
        turnL();
        turnBInverse();
        edgeSwap();
        turnB();
        turnLInverse();
        turnBInverse();
    }
    public void edgeAlgH(){
        turnLInverse();
        turnB();
        turnL();
        turnBInverse();
        edgeSwap();
        turnB();
        turnLInverse();
        turnBInverse();
        turnL();
    }
    public void edgeAlgI(){
        turnD();
        turnMInverse();
        starU();
        turnM();
        starU();
        turnDInverse();
        turnM2();
    }
    public void edgeAlgJ(){
        turnU();
        turnR();
        turnUInverse();
        edgeSwap();
        turnU();
        turnRInverse();
        turnUInverse();
    }
    public void edgeAlgL(){
        turnUInverse();
        turnLInverse();
        turnU();
        edgeSwap();
        turnUInverse();
        turnL();
        turnU();
    }
    public void edgeAlgM(){
        turnBInverse();
        turnR();
        turnB();
        edgeSwap();
        turnBInverse();
        turnRInverse();
        turnB();
    }
    public void edgeAlgN(){
        turnR();
        turnBInverse();
        turnRInverse();
        turnB();
        edgeSwap();
        turnBInverse();
        turnR();
        turnB();
        turnRInverse();
    }
    public void edgeAlgO(){
        turnBInverse();
        turnRInverse();
        turnB();
        edgeSwap();
        turnBInverse();
        turnR();
        turnB();
    }
    public void edgeAlgP(){
        turnBInverse();
        turnR2();
        turnB();
        edgeSwap();
        turnBInverse();
        turnR2();
        turnB();
    }
    public void edgeAlgQ(){
        turnBInverse();
        turnR();
        turnB();
        starU();
        turnM2();
        starU();
        turnBInverse();
        turnRInverse();
        turnB();
    }
    public void edgeAlgR(){
        turnUInverse();
        turnL();
        turnU();
        edgeSwap();
        turnUInverse();
        turnLInverse();
        turnU();
    }
    public void edgeAlgS(){
        turnM2();
        turnD();
        starU();
        turnMInverse();
        starU();
        turnM();
        turnDInverse();
    }
    public void edgeAlgT(){
        turnU();
        turnRInverse();
        turnUInverse();
        edgeSwap();
        turnU();
        turnR();
        turnUInverse();
    }
    public void edgeAlgV(){
        turnU();
        turnR2();
        turnUInverse();
        edgeSwap();
        turnU();
        turnR2();
        turnUInverse();
    }
    public void edgeAlgW(){
        turnM();
        turnU2();
        turnM();
        turnU2();
    }
    public void edgeAlgX(){
        turnUInverse();
        turnL2();
        turnU();
        edgeSwap();
        turnUInverse();
        turnL2();
        turnU();
    }







    //below is the final curly brace of the class
}


