/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todim;

import static java.lang.Math.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Henrique
 */
public class Todim {

    private Double originalMatrix[][];
    private Double normalizedMatrix[][];
    private Double taxa_criterios[];
    private Double normalizedVector[];
    private double sum_TxSubst = 0;
    private final double fator_atenuacao;
    private Double matrizDominPar[][];
    private Double matrizDominFinal[][];
    private Double resultadoFinal[];
    private Double taxa_substituicao[];
    
public Todim (Double[][] matriz, Double[] criterios, double ft_atenua){

    this.originalMatrix = matriz;
    this.normalizedMatrix = matriz.clone();
    this.normalizedVector = criterios.clone();
    this.taxa_criterios = criterios.clone();
    this.taxa_substituicao = criterios.clone();
    this.fator_atenuacao = ft_atenua;
}

public void compute(){
    int lin, col;

   
    lin = ((normalizedMatrix.length) * (normalizedMatrix.length));
    col = taxa_criterios.length;
    
    this.matrizDominPar = new Double[lin][col];
    this.matrizDominFinal = new Double[originalMatrix.length][originalMatrix.length];
    this.resultadoFinal = new Double[originalMatrix.length];
    
    taxa_substituicao();
    executa();
    soma_MatrizParcial();
    soma_MatrizFinal();
    result_final();
}

public void normaliza (){
    normalizedMatrix = normaliza_matriz(originalMatrix);
    normalizedVector = normaliza_vetor(taxa_criterios);
}


    private Double [][] normaliza_matriz(Double [][] originalMatrix) {
            double sum = 0.0;
            //Normaliza a Matriz
        for (int c = 0; c < originalMatrix[0].length; c++) {
            //somatório da Coluna
            sum = 0.0;
            for (Double[] originalMatrix1 : originalMatrix) {
                sum += originalMatrix1[c];
            }
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
         
    private Double[] normaliza_vetor(Double[] vetor_criterios){
        double sum = 0.0;
        
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
       BigDecimal valorExato = new BigDecimal(valor).setScale(5, RoundingMode.HALF_UP);
       
       return valorExato.doubleValue();
       
    }
    
    private double maior_zero(double result_pesos, double a_rc){
        
        double multp_aux,  result;
                
                multp_aux = a_rc * result_pesos;
                
                result = sqrt(multp_aux / sum_TxSubst);
                
                result = arredonda(result);
                
                return result;
    }
    
    private double igual_zero(){
        return 0;
    }
    
    private double menor_zero(double result_pesos, double a_rc){
        
        double multp_aux, divisao, result;
            
            multp_aux = result_pesos * sum_TxSubst;
            
            divisao = multp_aux / a_rc;
            
            result = (((-1) / fator_atenuacao) * (sqrt(divisao)));
            
            result = arredonda(result);
            
        return result;
    }
    
    private double maior_criterio(Double[] vetorMaior){
        double maior_crit = vetorMaior[0];
        for (int i = 0; i < vetorMaior.length; i++) {
                if(vetorMaior[i] > maior_crit){
                    maior_crit = vetorMaior[i];
                }
        }
        return maior_crit;
    }
    
    private double menor_criterio(Double[] vetorMenor){
        double menor_crit = vetorMenor[0];
        for (int i = 0; i < vetorMenor.length; i++) {
                if(vetorMenor[i] < menor_crit){
                    menor_crit = vetorMenor[i];
                }
        }
        return menor_crit;
    }
    
    private void taxa_substituicao(){
        double a_rc, maior_crit;
        maior_crit =  maior_criterio(normalizedVector);
        for (int i = 0; i < normalizedVector.length; i++) {
            a_rc = normalizedVector[i] / maior_crit;
            taxa_substituicao[i] = a_rc;
            sum_TxSubst = sum_TxSubst + a_rc;
        }
    }
    
    
    private void executa(){
        double a_rc, result_pesos, aux = 0;
        int line;
          
        for (int c = 0; c < normalizedMatrix[0].length; c++) {
            line = 0;
            for (int j = 0; j < normalizedMatrix.length; j++) {
                for (int i = 0; i < normalizedMatrix.length; i++) {
                        result_pesos = normalizedMatrix[j][c] - normalizedMatrix[i][c];
                        
                        a_rc = taxa_substituicao[c];
                        
                        if(result_pesos > 0){
                            aux = maior_zero(result_pesos, a_rc);              
                        }
                        else if(result_pesos == 0){
                            aux = igual_zero();
                        }       
                        else if(result_pesos < 0){
                            result_pesos = normalizedMatrix[i][c] - normalizedMatrix[j][c];
                            aux = menor_zero(result_pesos, a_rc);
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
                    sum += matrizDominPar[i][r];
                }   
            matrizDominFinal[line][col] = arredonda(sum);
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
    
    private void soma_MatrizFinal(){
        double sum = 0.0;
        
        for (int r = 0; r < matrizDominFinal[0].length; r++) {
                for (int i = 0; i < matrizDominFinal.length; i++) {
                    sum += matrizDominFinal[i][r];
            }
            resultadoFinal[r] = sum;
            System.out.println("Soma: " + resultadoFinal[r]);
            sum = 0.0;
        }

    }
    
    private double calc_normaliza(double soma, double minimo, double maximo){
        double result;
        
        result = ((soma - minimo) / (maximo - minimo));
        
        return result;
    }
    
    private void result_final(){

        double max, min;
        
        max = maior_criterio(resultadoFinal);
        min = menor_criterio(resultadoFinal);
        System.out.println("");
        for (int i = 0; i < resultadoFinal.length; i++) {
            resultadoFinal[i] = arredonda(calc_normaliza(resultadoFinal[i], min, max));
            System.out.println("Alternativa " + (i+1) + ": " + resultadoFinal[i]);
        }
    }
    
    
}
