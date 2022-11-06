package com.whycoding.rubikscube;

public class EdgePiece {
    private String pieceType = "Edge";
    private int numberOfSides = 2;
    private String sideOneLetter;
    private String sideTwoLetter;

    //Constructor
    public EdgePiece(String sideOneLetter, String sideTwoLetter){
        this.sideOneLetter = sideOneLetter;
        this.sideTwoLetter = sideTwoLetter;
    }



}
