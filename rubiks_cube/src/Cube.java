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


    private String[] frontFace = {"Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red", "Red"};
    private String[] backFace = {"Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange"};
    private String[] leftFace = {"White", "White", "White", "White", "White", "White", "White", "White", "White"};
    private String[] rightFace = {"Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow", "Yellow"};
    private String[] upperFace = {"Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue", "Blue"};
    private String[] downFace = {"Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green", "Green"};

    private String turnList = "";
    private int numberOfTurnTypes = 14; //6 faces + middle + inverses

    private String[] tempFrontFace = new String[9];
    private String[] tempBackFace = new String[9];
    private String[] tempRightFace = new String[9];
    private String[] tempLeftFace = new String[9];
    private String[] tempUpperFace = new String[9];
    private String[] tempDownFace = new String[9];

    //constructor
    public Cube() {}

    //getters and setters
    public String getTurnList() {return turnList;}
    public void setTurnList(String turnList){this.turnList = turnList;}
    public String[] getFrontFace() {return frontFace;}
    public String[] getBackFace() {return backFace;}
    public String[] getUpperFace() {return upperFace;}
    public String[] getDownFace() {return downFace;}
    public String[] getRightFace() {return rightFace;}
    public String[] getLeftFace() {return leftFace;}
    public String[] getTempFrontFace() {return tempFrontFace;}
    public String[] getTempBackFace() {return tempBackFace;}
    public String[] getTempUpperFace() {return tempUpperFace;}
    public String[] getTempDownFace() {return tempDownFace;}
    public String[] getTempRightFace() {return tempRightFace;}
    public String[] getTempLeftFace() {return tempLeftFace;}

    //methods
    public void printFace(String[] faceArray){
        for (int i = 0; i < faceArray.length; i++) {
            System.out.print(faceArray[i]);
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
    public void randomize(){
        double selector;

        for (int i = 0; i < 25; i++) {
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
                case 13:
                    turnM();
                    break;
                case 14:
                    turnMInverse();
                    break;
            }
        }
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
        tempRightFace[6] = rightFace[8];
        tempRightFace[7] = rightFace[5];
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
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
        for (int i=0; i < frontFace.length; i++) {
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
        for (int i=0; i < frontFace.length; i++) {
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
        //create temporary arrays for the faces. need to create new String arrays so that the TEMP and originals don't point to the same place on the heap
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

        //special algorithms for edge pieces
    public void edgeAlgS(){
        turnM2();
        turnD();
        starU();
        turnMInverse();
        starU();
        turnM();
        turnDInverse();
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
    public void edgeAlgC(){
        turnU2();
        turnMInverse();
        turnU2();
        turnMInverse();
    }
    public void edgeAlgW(){
        turnM();
        turnU2();
        turnM();
        turnU2();
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

        //Algs for the various corner pieces. These algs consist of: 1. set-up moves, 2. the cornerSwap alg, 3. reversing the set-up moves.
        //To note, the letter P has no alg of its own because it is the same as the cornerSwap. there is also no agl for A,E, or R because the method solves all other pieces first and then A is inherently solved

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

    //below is the final curly brace of the class
}


