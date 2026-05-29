import java.util.Arrays;

/**
 * Map Class
 * Houses methods for:
 * <ul>
 *      Information about the current Map
 *      Creating a new map
 * </ul>
 */
public class Map {
    private int[][] map;

    public Map() {
        this.newMap();
    }

    public int[][] getMap() {
        return this.map;
    }

    private void newMap() {
        this.map = new int[4][4];
        System.out.println(Arrays.deepToString(this.map));
    }
}
