package underlings.field;

import org.junit.Test;

public class GetGridTests {

    @Test
    public void testGetGrid() {
        Field field = new Field(new FieldSpaceFactory());

        FieldSpace[][] grid = new FieldSpace[4][9];
        grid[0][0] = field.field.get(21);
        grid[0][1] = field.field.get(0);
        grid[0][2] = field.field.get(1);
        grid[0][3] = field.field.get(2);
        grid[0][4] = field.field.get(3);
        grid[0][5] = field.field.get(4);
        grid[0][6] = field.field.get(5);
        grid[0][7] = field.field.get(6);
        grid[0][8] = field.field.get(7);
        grid[1][0] = field.field.get(20);
        grid[1][8] = field.field.get(8);
        grid[2][0] = field.field.get(19);
        grid[2][8] = field.field.get(9);
        grid[3][0] = field.field.get(18);
        grid[3][1] = field.field.get(17);
        grid[3][2] = field.field.get(16);
        grid[3][3] = field.field.get(15);
        grid[3][4] = field.field.get(14);
        grid[3][5] = field.field.get(13);
        grid[3][6] = field.field.get(12);
        grid[3][7] = field.field.get(11);
        grid[3][8] = field.field.get(10);

        assertEquals(grid, field.getGrid());
    }
}
