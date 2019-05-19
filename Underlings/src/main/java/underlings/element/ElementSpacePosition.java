package underlings.element;

public enum ElementSpacePosition {
    L3_1, L3_2, L3_3, R3_1, R3_2, R3_3, L4_1, L4_2, L4_3, L4_4, R4_1, R4_2, R4_3, R4_4;

    public int x;
    public int y;

    static {
        L3_1.x = 0;
        L3_2.x = 0;
        L3_3.x = 0;
        R3_1.x = 1;
        R3_2.x = 1;
        R3_3.x = 1;

        L4_1.x = 0;
        L4_2.x = 0;
        L4_3.x = 0;
        L4_4.x = 0;
        R4_1.x = 1;
        R4_2.x = 1;
        R4_3.x = 1;
        R4_4.x = 1;

        L3_1.y = 0;
        L3_2.y = 1;
        L3_3.y = 2;
        R3_1.y = 0;
        R3_2.y = 1;
        R3_3.y = 2;

        L4_1.y = 0;
        L4_2.y = 1;
        L4_3.y = 2;
        L4_4.y = 3;
        R4_1.y = 0;
        R4_2.y = 1;
        R4_3.y = 2;
        R4_4.y = 3;
    }
}
