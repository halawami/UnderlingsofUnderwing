package underlings.field;

import java.util.List;

import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.player.Player;

public class Field {

    private FieldSpace white;

    public List<FieldSpace> field;

    public Field(FieldSpaceFactory fieldSpaceFactory) {
        this.white = fieldSpaceFactory.createFieldSpace(DrawChoice.WHITE);
        this.field = fieldSpaceFactory.createFieldArray();
    }

    public void addHandler(int fieldPosition, Handler handler) {
        this.field.get(fieldPosition).addHandler(handler);
    }

    public void addHandlerWhitespace(Handler handler) {
        this.white.addHandler(handler);
    }

    public void rotate(Handler handler) {
        FieldSpace space = this.findHandler(handler);
        space.remove(handler);
        int index = this.field.indexOf(space);
        index++;
        index %= this.field.size();
        this.field.get(index).addHandler(handler);
    }

    public FieldSpace findHandler(Handler handler) {
        for (FieldSpace space : this.field) {
            if (space.contains(handler)) {
                return space;
            }
        }
        if (this.white.contains(handler)) {
            return this.white;
        }
        return null;
    }

    public void removeHandler(Handler handler) {
        this.findHandler(handler).remove(handler);
    }

    public FieldSpace[][] getGrid() {
        FieldSpace[][] grid = new FieldSpace[4][9];
        grid[0][0] = this.field.get(21);
        grid[0][1] = this.field.get(0);
        grid[0][2] = this.field.get(1);
        grid[0][3] = this.field.get(2);
        grid[0][4] = this.field.get(3);
        grid[0][5] = this.field.get(4);
        grid[0][6] = this.field.get(5);
        grid[0][7] = this.field.get(6);
        grid[0][8] = this.field.get(7);
        grid[1][0] = this.field.get(20);
        grid[1][8] = this.field.get(8);
        grid[2][0] = this.field.get(19);
        grid[2][8] = this.field.get(9);
        grid[3][0] = this.field.get(18);
        grid[3][1] = this.field.get(17);
        grid[3][2] = this.field.get(16);
        grid[3][3] = this.field.get(15);
        grid[3][4] = this.field.get(14);
        grid[3][5] = this.field.get(13);
        grid[3][6] = this.field.get(12);
        grid[3][7] = this.field.get(11);
        grid[3][8] = this.field.get(10);

        return grid;
    }

    public FieldSpace[][] getValidFieldSpaces(Player player) {
        FieldSpace[][] grid = this.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                FieldSpace space = grid[i][j];

                if (space == null) {
                    continue;
                }

                int count = 0;
                for (Handler handler : player.handlers) {
                    if (space.contains(handler)) {
                        count++;
                    }
                }

                if (count == player.maxHandlersOnSpace) {
                    grid[i][j] = null;
                }
            }
        }

        return grid;
    }


}

