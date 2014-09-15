/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todim;

/**
 *
 * @author Henrique
 */
public class PlayTodim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Double matrix[][] = {
            {3.0, 290.0, 3.0, 3.0, 1.0, 6.0, 4.0, 0.0},
            {4.0, 180.0, 2.0, 2.0, 1.0, 4.0, 2.0, 0.0},
            {3.0, 347.0, 1.0, 2.0, 2.0, 5.0, 1.0, 0.0},
            {3.0, 124.0, 2.0, 3.0, 2.0, 5.0, 4.0, 0.0},
            {5.0, 360.0, 3.0, 4.0, 4.0, 9.0, 1.0, 1.0},
            {2.0, 89.0,  2.0, 3.0, 1.0, 5.0, 1.0, 0.0},
            {1.0, 85.0,  1.0, 1.0, 1.0, 4.0, 0.0, 1.0},
            {5.0, 80.0,  2.0, 3.0, 1.0, 6.0, 0.0, 1.0},
            {2.0, 121.0, 2.0, 3.0, 0.0, 6.0, 0.0, 0.0},
            {2.0, 120.0, 1.0, 3.0, 1.0, 5.0, 1.0, 0.0},
            {4.0, 280.0, 2.0, 2.0, 2.0, 7.0, 3.0, 1.0},
            {1.0, 90.0,  1.0, 1.0, 1.0, 5.0, 2.0, 0.0},
            {2.0, 160.0, 3.0, 3.0, 2.0, 6.0, 1.0, 1.0},
            {3.0, 320.0, 3.0, 3.0, 2.0, 8.0, 2.0, 1.0},
            {4.0, 180.0, 2.0, 4.0, 1.0, 6.0, 1.0, 1.0},
        };
        
        Double criterio [] = {5.0, 3.0, 2.0, 4.0, 1.0, 2.0, 1.0, 2.0}; 
        
         Double matrix2[][] = {
            {0.93, 0.40, 0.818, 1.0},
            {0.94, 0.20, 0.912, 1.0},
            {0.71, 0.20, 1.0,   1.0},
            {0.94, 1.00, 0.951, 0.71},
            {0.84, 0.60, 0.799, 0.86},
            {0.84, 0.40, 0.031, 0.86},
            {1.0,  0.80, 0.882, 0.43},
        };
        
        Double criterio2 [] = {5.0, 5.0, 4.0, 2.0}; 

        Todim todim1 = new Todim(matrix, criterio);
        
        //Todim todim2 = new Todim(matrix2, criterio2);
    }
    
}
