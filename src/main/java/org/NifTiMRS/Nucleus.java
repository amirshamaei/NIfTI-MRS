package org.NifTiMRS;

public enum Nucleus {
    N_1H ("1H" ),
    N_3HE ("3HE" ),
    N_7LI ("7LI" ),
    N_13C ("13C" ),
    N_19F ("19F" ),
    N_23NA ("23NA" ),
    N_31P ("31P" ),
    N_129XE ("129XE" );


    private final String name;

    Nucleus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
