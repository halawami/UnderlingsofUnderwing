package underlings.element;

public enum ElementSpacePosition {
    L3_1, L3_2, L3_3, R3_1, R3_2, R3_3, L4_1, L4_2, L4_3, L4_4, R4_1, R4_2, R4_3, R4_4;

    public int posX;
    public int posY;

    static {
        L3_1.posX = 0;
        L3_2.posX = 0;
        L3_3.posX = 0;
        R3_1.posX = 1;
        R3_2.posX = 1;
        R3_3.posX = 1;

        L4_1.posX = 0;
        L4_2.posX = 0;
        L4_3.posX = 0;
        L4_4.posX = 0;
        R4_1.posX = 1;
        R4_2.posX = 1;
        R4_3.posX = 1;
        R4_4.posX = 1;

        L3_1.posY = 0;
        L3_2.posY = 1;
        L3_3.posY = 2;
        R3_1.posY = 0;
        R3_2.posY = 1;
        R3_3.posY = 2;

        L4_1.posY = 0;
        L4_2.posY = 1;
        L4_3.posY = 2;
        L4_4.posY = 3;
        R4_1.posY = 0;
        R4_2.posY = 1;
        R4_3.posY = 2;
        R4_4.posY = 3;
    }
}
