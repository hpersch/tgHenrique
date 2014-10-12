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
        
        
                Double matrix [][] = {{9.0, 7.0, 5.0, 3.0, 9.0, 1.0},
                                        {1.0, 3.0, 5.0, 9.0, 9.0, 9.0},
                                        {7.0, 5.0, 7.0, 5.0, 3.0, 9.0},
                                        {1.0, 1.0, 1.0, 9.0, 9.0, 3.0},
                                        {7.0, 7.0, 7.0, 5.0, 5.0, 3.0},
                                        {9.0, 3.0, 9.0, 3.0, 7.0, 1.0},  
        };
                
                        Double [] criterio = {9.0, 9.0, 9.0, 9.0, 9.0, 9.0};
        
        Todim teste = new Todim (matrix, criterio, 1.0);
        teste.normaliza();
        teste.compute();
    }
    
}
