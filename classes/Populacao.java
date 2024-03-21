/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 10514956607
 */
public class Populacao {
    private List<Individuo> populacao;
    
    private int individuos;
    
    private int nGenes;
    
    public Populacao(int individuos, int nGenes){
        this.populacao = new ArrayList<>();
        this.individuos = individuos;
        this.nGenes = nGenes;
        
        this.gerar();
    }
    
    public int maiorPontuacaoAtual() {
    	return this.populacao.get(0).getPontuacao();
    }
    
    public void gerar(){
        Individuo ind;
        for(int i = 0; i < this.individuos; i++){
            ind = new Individuo(this.nGenes);
            populacao.add(ind);
        }
    }
    
    public void remover(int n){
        for(int i = 0; i < n; i++){
            this.populacao.remove(n);
        }
    }
    
    public void cruzamento(){
        Individuo ind;
        while(this.individuos > this.populacao.size()){
            for(int i = 0; i < this.populacao.size()-1; i+=2){
                ind = new Individuo(this.populacao.get(i), this.populacao.get(i+1));
                this.populacao.add(ind);
            }
        }
    }
    
    public void mutacao(int n){
        Random r = new Random();
        for(int i = 0; i < n; i++){
            this.populacao.get(r.nextInt(this.individuos));
        }
    }
    
    public void ordena(){
        quickSort(0, this.individuos-1);
    }
    
    public void quickSort(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex-1);
            quickSort(partitionIndex+1, end);
        }
    }
    
    private int partition(int begin, int end) {
        Individuo pivot = this.populacao.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (this.populacao.get(j).getPontuacao() >= pivot.getPontuacao()) {
                i++;

                Individuo swapTemp = this.populacao.get(i);
                this.populacao.set(i, this.populacao.get(j));
                this.populacao.set(j, swapTemp);
            }
        }

        Individuo swapTemp = this.populacao.get(i+1);
        this.populacao.set(i+1, this.populacao.get(end));
        this.populacao.set(end, swapTemp);

        return i+1;
    }
    
    public void exibirPiorMelhor(){
        System.out.println("Pior Individuo");
        this.getWorst().exibir();
        
        System.out.println("Melhor Individuo");
        this.getTop1().exibir();
    }
    
    public Individuo getTop1(){
        return this.populacao.get(0);
    }
    
    public Individuo getWorst(){
        return this.populacao.get(this.individuos - 1);
    }
    
    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    public int getnGenes() {
        return nGenes;
    }

    public void setnGenes(int nGenes) {
        this.nGenes = nGenes;
    }

    public Individuo get(int pos){
        return this.populacao.get(pos);
    }
}
