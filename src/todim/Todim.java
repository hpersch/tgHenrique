/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todim;

import static java.lang.Math.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.util.Arrays.sort;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Henrique
 */
public class Todim {

    private Double originalMatrix[][];
    private Double normalizedMatrix[][];
    private Double taxa_criterios[];
    private Double normalizedVector[];
    private double maior_crit = 0;
    private Double matrizDominPar[][];
    private Double matrizDominFinal[][];
    private Double resultadoFinal[];
    
public Todim (Double[][] matriz, Double[] taxa_criterios){

    this.originalMatrix = matriz;
    this.normalizedMatrix = originalMatrix.clone();
    this.normalizedVector = taxa_criterios.clone();
    this.taxa_criterios = taxa_criterios.clone();
    compute();
    
}

public void compute(){
    normalizedMatrix = normaliza_matrizCol(originalMatrix);
    normalizedVector = normaliza_vetor(taxa_criterios);
    maior_criterio();
        int lin, col;
    lin = ((normalizedMatrix.length) * (normalizedMatrix.length));
    col = taxa_criterios.length;
    System.out.println("Linhas: " + lin + "  Colunas:  " + col);
    
    this.matrizDominPar = new Double[lin][col];
    this.matrizDominFinal = new Double[originalMatrix.length][originalMatrix.length];
    this.resultadoFinal = new Double[originalMatrix.length];
    
    executa();
    soma_MatrizParcial();
    normaliza_MatrizFinal();
}


    private Double [][] normaliza_matrizCol(Double [][] originalMatrix) {
        Double [][] normalizedMatrix = originalMatrix.clone();
        //Normaliza a Matriz

        for (int c = 0; c < originalMatrix[0].length; c++) {
            //somatório da Coluna
            Double sum = 0.0;

            for (Double[] originalMatrix1 : originalMatrix) {
                sum += originalMatrix1[c];
            }
            //total[c] = sum;
            //Se o somatório for 0, cai fora (não deve ocorrer)
            if (sum == 0) {
                continue;
            }
            //Divide cada valor da coluna pelo somatório Total da Coluna
            for (int r = 0; r < originalMatrix.length; r++) {
                Double newValue = (originalMatrix[r][c] / sum);
                normalizedMatrix[r][c] = arredonda(newValue);
            }
        }

        System.out.println("Matriz Normalizada:");
        for (Double[] normalizedMatrix1 : normalizedMatrix) {
            for (int r = 0; r < normalizedMatrix[0].length; r++) {
                System.out.print("       " + normalizedMatrix1[r]);
            }
            System.out.println("");
        }
        
        return normalizedMatrix;
    }
    
    private Double [][] normaliza_matrizLin(Double [][] originalMatrix) {
        Double [][] normalizedMatrix = originalMatrix.clone();
        //Normaliza a Matriz

        for (int c = 0; c < originalMatrix[0].length; c++) {
            //somatório da Coluna
            Double sum = 0.0;

            for (Double[] originalMatrix1 : originalMatrix) {
                sum = sum + originalMatrix1[c];
            }
            //total[c] = sum;
            //Se o somatório for 0, cai fora (não deve ocorrer)
            if (sum == 0) {
                continue;
            }
            //Divide cada valor da coluna pelo somatório Total da Coluna
            for (int r = 0; r < originalMatrix.length; r++) {
                Double newValue = (originalMatrix[c][r] / sum);
                normalizedMatrix[c][r] = arredonda(newValue);
            }
        }

        System.out.println("Matriz Normalizada:");
        for (Double[] normalizedMatrix1 : normalizedMatrix) {
            for (int r = 0; r < normalizedMatrix[0].length; r++) {
                System.out.print("       " + normalizedMatrix1[r]);
            }
            System.out.println("");
        }
        
        return normalizedMatrix;
    }
    
    
    private Double [] normaliza_vetor(Double[] vetor_criterios){
        Double [] normalizedVector = vetor_criterios.clone();
        Double sum = 0.0;
        
        for (int c = 0; c < vetor_criterios.length; c++) {
             sum+= vetor_criterios[c];   
            }
        
        for (int i = 0; i < vetor_criterios.length; i++) {
             Double newValue = vetor_criterios[i] / sum;
             normalizedVector[i] = arredonda(newValue);
        }
        
        System.out.println("Vetor Normalizado: ");
        
        for (int c = 0; c < normalizedVector.length; c++) {
            System.out.println(" " + normalizedVector[c]);
        }
        
        return normalizedVector;
    }
    
    private double arredonda(double valor){
        BigDecimal valorExato = new BigDecimal(valor).setScale(3, RoundingMode.HALF_UP);
       
        return valorExato.doubleValue();
       
    }
    
    private double maior_zero(double result_pesos, double a_rc, double soma){
        
        double multp_aux,  result;
                
                multp_aux = a_rc * result_pesos;
                
                result = sqrt(multp_aux / soma);
                
                result = arredonda(result);
                
                return result;
    }
    
    private double igual_zero(){
        return 0;
    }
    
    private double menor_zero(double result_pesos, double a_rc, double soma, double fator_atenuacao){
        
        double multp_aux, divisao, result;
            
            multp_aux = (result_pesos) * soma;
            
            divisao = (multp_aux) / a_rc;
            
            result = (((-1) / fator_atenuacao) * (sqrt(divisao)));
            
           result = arredonda(result);
            
        return result;
    }
    
    private void maior_criterio(){
        for (int i = 0; i < normalizedVector.length; i++) {
                if(normalizedVector[i] > maior_crit){
                    maior_crit = normalizedVector[i];
                }
        } 
    }
    
    private double valor_arc(double criterioAtual){
        
        double a_rc = criterioAtual / maior_crit; 
        
        return arredonda(a_rc);
    }
    
    
    private void executa(){
        double a_rc, result_pesos, aux = 0, soma_arc, fator = 1;
        int line;
        
        for (int c = 0; c < normalizedMatrix[0].length; c++) {
            line = 0;
            System.out.println("==================   " + (c+1) + "   ===============================");
            for (int j = 0; j < normalizedMatrix.length; j++) {
                for (int i = 0; i < normalizedMatrix.length; i++) {
                    result_pesos = normalizedMatrix[j][c] - normalizedMatrix[i][c];
                        System.out.println("Valor 1: " + normalizedMatrix[j][c] + "  Valor 2: " + normalizedMatrix[i][c]);
                        
                        a_rc = valor_arc(normalizedVector[c]);
                        soma_arc = i + 1;
                        
                        if(result_pesos > 0){
                            aux = maior_zero(result_pesos, a_rc, soma_arc);              
                        }
                        else if(result_pesos == 0){
                            aux = igual_zero();
                        }       
                        else if(result_pesos < 0){
                            result_pesos = normalizedMatrix[i][c] - normalizedMatrix[j][c];
                            aux = menor_zero(result_pesos, a_rc, soma_arc, fator);
                        }
                        matrizDominPar[line][c] = aux;
                        line++;
                        System.out.println("Resultado Posição (" + (j+1) + "-" + (i+1) + "): " + aux);
                    aux = 0;
                }
            }     
        }
        System.out.println("\n->Matriz Dominancias Parciais: ");
        for (int i = 0; i < matrizDominPar.length; i++) {
            System.out.print("\n"+(i+1) + "):   ");
            for (int r = 0; r < matrizDominPar[0].length; r++) {
                System.out.print("       " + matrizDominPar[i][r]);
            }  
        }
    }
    
    private void soma_MatrizParcial(){
        double sum = 0;
        int col = 0, line = 0;
        
        for (int i = 0; i < matrizDominPar.length; i++) {
                for (int r = 0; r < matrizDominPar[0].length; r++) {
                    System.out.println("Valor " + (r+1) + ": " + matrizDominPar[i][r]);
                    sum += matrizDominPar[i][r];
                }   
                matrizDominFinal[line][col] = arredonda(sum);
                System.out.println("Soma " + (i+1) + ": " + matrizDominFinal[line][col]);
                sum = 0;
                line++;
            
            if (line == matrizDominFinal.length){
                col++;
                line = 0;
            }
        }
        System.out.println("\n->Matriz Dominancias Final: \n");
        for (int i = 0; i < matrizDominFinal.length; i++) {
            System.out.print("\n"+(i+1) + "):   ");
            for (int r = 0; r < matrizDominFinal[0].length; r++) {
                System.out.print("       " + matrizDominFinal[i][r]);
            }  
        }
    }
    
    private void normaliza_MatrizFinal(){
        double sum = 0.0, max = 0, min = 99999;
        for (int i = 0; i < matrizDominFinal.length; i++) {
            max =  matrizDominFinal[i][0];
            min = matrizDominFinal[i][0];
                for (int r = 0; r < matrizDominFinal[0].length; r++) {
                    System.out.println("Valor " + (r+1) + ": " + matrizDominFinal[i][r]);
                    sum = sum + matrizDominFinal[i][r];
                    if(matrizDominFinal[i][r] > max)
                        max = matrizDominFinal[i][r];
                    if (matrizDominFinal[i][r] < min)
                        min = matrizDominFinal[i][r];
            }
            System.out.println("Soma: " + sum + " - Menor Valor: " + min + " - Maior Valor: " + max);
            resultadoFinal[i] = arredonda(calc_normaliza(sum, min, max));
            //resultadoFinal[i] = sum;
            sum = 0.0;
        }
               
       // resultadoFinal = normaliza_vetor(resultadoFinal);
        
        for (int i = 0; i < resultadoFinal.length; i++) {
            System.out.println("\n Resultado " + (i+1) + " : " + resultadoFinal[i]);
        }
    }
    
    private double calc_normaliza(double soma, double minimo, double maximo){
        double result;
        
        result = ((soma - minimo) / (maximo - minimo));
        
        return result;
    }
}
