package com.whycoding.rubikscube;

public class CornerPiece {
    private String pieceType = "Corner";
    private int numberOfsides = 3;
    private String sideOneLetter;
    private String sideTwoLetter;
    private String sideThreeLetter;


    //Constructor
    public CornerPiece(String sideOneLetter, String sideTwoLetter){
        this.sideOneLetter = sideOneLetter;
        this.sideTwoLetter = sideTwoLetter;
        this.sideThreeLetter = sideTwoLetter;
    }
}
