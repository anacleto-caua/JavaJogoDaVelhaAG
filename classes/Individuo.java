package classes;

import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 10514956607
 */
public class Individuo {

    private Integer[][] dnaX;
    private Integer[][] dnaY;
    
    private int nGenes;
    
    private int rounds;

    private int partidas;
    
    private int pontuacao; // vitoria + 3 / empate + 1 / derrota - 0
    
    private double mediaPontuacao;
    
    public Individuo(int nGenes){
        this.nGenes = nGenes;
        this.pontuacao = 0;
        this.rounds = 0;
        this.partidas = 0;
       
        this.dnaX = this.gerar();
        this.dnaY = this.gerar();
    }
    
    public Individuo(Individuo pai, Individuo mae){
        Random r = new Random();
        
        this.nGenes = pai.getnGenes();
        
        for(int i = 0; i < this.nGenes; i++){
            if(r.nextInt(2) == 0){
                this.dnaX[i] = pai.getDnaX(i);
            }else{
                this.dnaX[i] = mae.getDnaX(i);
            }
        }
        
        for(int i = 0; i < this.nGenes; i++){
            if(r.nextInt(2) == 0){
                this.dnaY[i] = pai.getDnaY(i);
            }else{
                this.dnaY[i] = mae.getDnaY(i);
            }
        }
    }
    
    public void limpar(){
        this.pontuacao = 0;
        this.rounds++;
    }
    
    public Integer[][] gerar(){
        Integer[][] dna = new Integer[this.nGenes][2];
        Random r = new Random();
        int x;
        int y;
        
        for(int i = 0; i < this.nGenes; i++){
            x = r.nextInt(3);
            y = r.nextInt(3);
            dna[i][0] = x;
            dna[i][1] = y;
        }
        return dna;
    }
    
    public void mutacao(){
        Random r = new Random();
        int n;
        
        for(int i = 0; i < r.nextInt(3); i++){
            n = r.nextInt(this.nGenes);
            this.dnaX[n][0] = r.nextInt(3);
            this.dnaX[n][1] = r.nextInt(3);
        }
        
        for(int i = 0; i < r.nextInt(3); i++){
            n = r.nextInt(this.nGenes);
            this.dnaY[n][0] = r.nextInt(3);
            this.dnaY[n][1] = r.nextInt(3);
        }
    }
    
    public void exibir(){
        System.out.println("Rounds: " + this.rounds);
        System.out.println("Partidas: " + this.partidas);
        System.out.println("Pontuacao: " + this.pontuacao);
        System.out.println("Media Pontuacao: " + this.mediaPontuacao);
        System.out.println("");
    }
    
    public void exibirDna(){
        
        System.out.println();

        
        System.out.print("Dna X: ");
        for(int i = 0; i < this.nGenes; i++){
            System.out.print("[" + this.dnaX[i][0] + ", " + this.dnaX[i][1] + "]");
        }
        
        System.out.println();
        
        System.out.print("Dna Y: ");
        for(int i = 0; i < this.nGenes; i++){
            System.out.print("[" + this.dnaY[i][0] + ", " + this.dnaY[i][1] + "]");
        }
    }
    
    public void ganhou(){
        pontuacao+=3;
    }
    
    public void perdeu(){
        pontuacao+=1;
    }
    
    public void jogou(){
        partidas+=0;
    }

    public Integer[][] getDnaX() {
        return dnaX;
    }

    public void setDnaX(Integer[][] dnaX) {
        this.dnaX = dnaX;
    }

    public Integer[][] getDnaY() {
        return dnaY;
    }

    public void setDnaY(Integer[][] dnaY) {
        this.dnaY = dnaY;
    }

    public int getnGenes() {
        return nGenes;
    }

    public void setnGenes(int nGenes) {
        this.nGenes = nGenes;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public double getMediaPontuacao() {
        return mediaPontuacao;
    }

    public void setMediaPontuacao(double mediaPontuacao) {
        this.mediaPontuacao = mediaPontuacao;
    }
    
    public Integer[] getDnaX(int pos){
        return this.dnaX[pos];
    }
    
    public Integer[] getDnaY(int pos){
        return this.dnaY[pos];
    }
}
