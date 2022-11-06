package com.whycoding.rubikscube;

public enum Pieces {
    AEDGE("Blue", "A"),
    BEDGE("Blue", "B"),
    CEDGE("Blue", "C"),
    DEDGE("Blue", "D"),
    EEDGE("White", "E"),
    FEDGE("White", "F"),
    GEDGE("White", "G"),
    HEDGE("White", "H"),
    IEDGE("Red", "I"),
    JEDGE("Red", "J"),
    KEDGE("Red", "K"),
    LEDGE("Red", "L"),
    MEDGE("Yellow", "M"),
    NEDGE("Yellow", "N"),
    OEDGE("Yellow", "O"),
    PEDGE("Yellow", "P"),
    QEDGE("Orange", "Q"),
    REDGE("Orange", "R"),
    SEDGE("Orange", "S"),
    TEDGE("Orange", "T"),
    UEDGE("Green", "U"),
    VEDGE("Green", "V"),
    WEDGE("Green", "W"),
    XEDGE("Green", "X"),
    ACORNER("Blue", "A"),
    BCORNER("Blue", "B"),
    CCORNER("Blue", "C"),
    DCORNER("Blue", "D"),
    ECORNER("White", "E"),
    FCORNER("White", "F"),
    GCORNER("White", "G"),
    HCORNER("White", "H"),
    ICORNER("Red", "I"),
    JCORNER("Red", "J"),
    KCORNER("Red", "K"),
    LCORNER("Red", "L"),
    MCORNER("Yellow", "M"),
    NCORNER("Yellow", "N"),
    OCORNER("Yellow", "O"),
    PCORNER("Yellow", "P"),
    QCORNER("Orange", "Q"),
    RCORNER("Orange", "R"),
    SCORNER("Orange", "S"),
    TCORNER("Orange", "T"),
    UCORNER("Green", "U"),
    VCORNER("Green", "V"),
    WCORNER("Green", "W"),
    XCORNER("Green", "X"),
    REDCENTER("Red", "RC"),
    ORANGECENTER("Orange", "OC"),
    YELLOWCENTER("Yellow", "YC"),
    WHITECENTER("White", "WC"),
    BLUECENTER("Blue", "BC"),
    GREENCENTER("Green", "GC"),;

    String color;
    String letter;

    Pieces (String color, String letter) {
        this.color = color;
        this.letter = letter;
    }
}
