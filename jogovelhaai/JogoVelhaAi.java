/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogovelhaai;

import classes.AG;
import classes.Individuo;
import classes.Populacao;

import java.util.Scanner;

/**
 *
 * @author 10514956607
 */
public class JogoVelhaAi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int individuos = 50;
        int nGenes = 30;
        double removerPercent =  0.5;
        
        AG ag = new AG(individuos, nGenes, removerPercent);
        
        int iteracoes = 0;
        long inicio = System.currentTimeMillis();
        
        System.out.print("Pontuacao necessária: ");
        System.out.println(ag.getMaiorPontuacaoPossivel() * 0.7);

        for(int i = 0; i < 1000; i++) {
	        System.out.println("Começo do looping de seleção.");
	        
	        System.out.print("Algoritmos count: ");
        	System.out.println(i);
        	System.out.println();
        	
        	ag.resetarAg();
	        
	        while(ag.getRounds() <  20 && ag.getMaiorPontuacaoAtual() < ag.getMaiorPontuacaoPossivel() * 0.7) {
	        	ag.selecaoNatural();
	        	
	        	System.out.print("Rounds: ");
	        	System.out.println(ag.getRounds());
	        	
	        	System.out.print("Maior pontuacao round: ");
	        	System.out.println(ag.getMaiorPontuacaoAtual());
	        }
	        
	        iteracoes+=ag.getRounds();
	        System.out.println("Fim do looping de seleção!");       
        }
        
        long fim = System.currentTimeMillis();
        
        long tempo = fim - inicio;
        
        long mediaPorAlgoritmo = tempo / 1000;
        long mediaPorIteracao = tempo / iteracoes;
        
        System.out.print("Tempo: ");
    	System.out.println(tempo);
    	
    	System.out.print("Media Por Algoritmo: ");
     	System.out.println(mediaPorAlgoritmo);
     	
     	System.out.print("Media Por Iteracao: ");
     	System.out.println(mediaPorIteracao);
       
    }
    
    public static boolean jogarContraPessoa(Individuo ind, boolean c){
        char[][] jogo = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        
        Integer[][] dna;
        if(c){
            dna = ind.getDnaY();
        }else{
            dna = ind.getDnaY();
        }
        
        boolean continua = true;
        Scanner sc = new Scanner(System.in);
        int posX = 0;
        int posY = 0;
        int i = 0;
        while(continua){
            if(c){
                System.out.print("Pos X: ");
                posX = sc.nextInt();
                
                System.out.print("Pos Y: ");
                posY = sc.nextInt();
                
                jogo[posX][posY] = 'X';
                
                if(JogoVelhaAi.venceu(jogo, 'X')){
                    return true;
                }
                
                c = false;
            }else{
                
                if(i >= ind.getnGenes()){
                    return true;
                }
                
                for(i = i;  i < ind.getnGenes(); i++){
                    posX = dna[i][0];
                    posY = dna[i][1];

                    if(jogo[posX][posY] == ' '){
                        break;
                    }
                }
                
                jogo[posX][posY] = 'O';

                if(JogoVelhaAi.venceu(jogo, 'O')){
                    return false;
                }
                
                c = true;
            }
            JogoVelhaAi.printaJogo(jogo);
        }

        return true;
    }
    
    public static boolean venceu(char[][] jogo, char s){
        int[][][] vitorias = {
            {{0,0}, {0,1}, {0,2}},
            {{1,0}, {1,1}, {1,2}},
            {{2,0}, {2,1}, {2,2}},
            {{0,0}, {1,0}, {2,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,2}, {1,2}, {2,2}},
            {{0,0}, {1,1}, {2,2}},
            {{2,0}, {1,1}, {0,2}}
        };
        
        char a;
        char b;
        char c;
        
        for(int i = 0; i < 8; i++){
            a = jogo[vitorias[i][0][0]][vitorias[i][0][1]];
            b = jogo[vitorias[i][1][0]][vitorias[i][1][1]];
            c = jogo[vitorias[i][2][0]][vitorias[i][2][1]];
            if((a == s) && (b == s) && (c == s)){
                return true;
            }
        }
                
        return false;
    }
    
    public static 
        void printaJogo(char jogo[][]){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(jogo[j][i] + " ");
            }
            System.out.println("");
        }
    }
    
}
