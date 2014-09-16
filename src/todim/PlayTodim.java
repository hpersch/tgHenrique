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
        
        Double matrix3[][] =    {{1.00, 0.96, 2.75, 3.25, 5.03, 3.85, 1.47, 4.83, 3.53, 2.50, 5.92, 4.63},
                                {1.04, 1.00, 5.13, 3.60, 5.40, 4.00, 2.62, 4.13, 4.03, 2.82, 6.83, 5.20},
                                {0.36, 0.20, 1.00, 2.37, 3.22, 2.49, 1.43, 2.13, 2.03, 1.10, 3.93, 3.32},
                                {0.31, 0.28, 0.42, 1.00, 2.28, 1.81, 0.62, 2.44, 2.02, 0.88, 4.18, 2.63},
                                {0.20, 0.19, 0.31, 0.44, 1.00, 1.63, 0.60, 1.60, 1.32, 0.70, 3.90, 1.60},
                                {0.26, 0.25, 0.40, 0.55, 0.61, 1.00, 0.66, 2.30, 2.25, 1.66, 4.33, 2.73},
                                {0.68, 0.38, 0.70, 1.61, 1.67, 1.52, 1.00, 4.55, 3.55, 1.78, 5.22, 3.03},
                                {0.21, 0.24, 0.47, 0.41, 0.63, 0.43, 0.22, 1.00, 1.26, 0.92, 3.57, 2.47},
                                {0.28, 0.25, 0.49, 0.50, 0.76, 0.44, 0.28, 0.79, 1.00, 1.32, 4.36, 3.08},
                                {0.40, 0.35, 0.91, 1.13, 1.42, 0.60, 0.56, 1.09, 0.76, 1.00, 5.56, 3.66},
                                {0.17, 0.15, 0.25, 0.24, 0.26, 0.23, 0.19, 0.28, 0.23, 0.18, 1.00, 0.56},
                                {0.22, 0.19, 0.30, 0.38, 0.62, 0.37, 0.33, 0.40, 0.32, 0.27, 1.79, 1.00},
        };
        
        Double [] criterio3 = {0.235, 0.203, 0.104,0.079, 0.069, 0.067, 0.057, 0.047, 0.046, 0.041, 0.031, 0.021};

        Todim todim1 = new Todim(matrix, criterio);
        
        //Todim todim2 = new Todim(matrix2, criterio2);
        
        //Todim todim3 = new Todim(matrix3, criterio3);
    }
    
}
