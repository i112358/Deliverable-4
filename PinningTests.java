import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

/**
 *
 * @author Rachel
 */
public class PinningTests {
    
    MainPanel m = new MainPanel(10);
    
    public PinningTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        m.clear();
    }
    
    //Change Method ConvertToInt
    @Test //Test 0
    public void convertToIntZero() {
        int n = m.convertToInt(0);
        assertEquals(0, n);
    }
    
    @Test //test 100
    public void convertToInt100() {
        int n = m.convertToInt(100);
        assertEquals(100, n);
    }
    
    @Test //test max int
    public void convertToIntMax() {
        int n = m.convertToInt(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, n);
    }
    
    //Change method CalculateNextIteration,  iterateCell and getNumNeighbors
    @Test //Test Run under rule: Any live cell with fewer than two live neighbours dies, as if caused by under-population
    public void runNoLiveNeighbor(){
        int size=10;
        //initialize _cell
        Cell[][] testCell= new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                testCell[i][j]=new Cell();
                testCell[i][j].setAlive(false);
            }
        }
        testCell[0][0].setAlive(true);
        m.setCells(testCell);
        m.run();
        assertFalse(testCell[0][0].getAlive());
    }
    
    @Test //Test Run under rule: Any live cell with two or three live neighbours lives on to the next generation.
    public void run3LiveNeighbors(){
        int size=10;
        //initialize _cell
        Cell[][] testCell= new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                testCell[i][j]=new Cell();
                testCell[i][j].setAlive(false);
            }
        }
        testCell[0][0].setAlive(true);
        testCell[1][0].setAlive(true);
        testCell[0][1].setAlive(true);
        testCell[1][1].setAlive(true);
        //  11
        //  11
        m.setCells(testCell);
        m.run();
        assertTrue(testCell[0][0].getAlive());
    }
    
     @Test //Test Run under rule: Any live cell with more than three live neighbours dies, as if by over-population.
     public void run4LiveNeighbors(){
        int size=10;
        //initialize _cell
        Cell[][] testCell= new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                testCell[i][j]=new Cell();
                testCell[i][j].setAlive(false);
            }
        }
        testCell[0][0].setAlive(true);
        testCell[1][0].setAlive(true);
        testCell[0][1].setAlive(true);
        testCell[2][0].setAlive(true);
        testCell[1][1].setAlive(true);
        //  111
        //  110
        m.setCells(testCell);
        m.run();
        assertFalse(testCell[1][1].getAlive());
    }
     
     @Test //Test Run under rule: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     public void deadRun3LiveNeighbors(){
        int size=10;
        //initialize _cell
        Cell[][] testCell= new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                testCell[i][j]=new Cell();
                testCell[i][j].setAlive(false);
            }
        }
        testCell[0][0].setAlive(true);
        testCell[1][0].setAlive(true);
        testCell[0][1].setAlive(true);
        //   110
        //   100
        m.setCells(testCell);
        m.run();
        assertTrue(testCell[1][1].getAlive());
    }
}
