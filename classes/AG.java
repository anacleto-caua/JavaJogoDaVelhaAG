/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author 10514956607
 */
public class AG {
   
    private Populacao populacao;

    private int individuos;
    
    private int nGenes;

    private double removerPercent;
    
    private char[][] jogo;
    
    private int rounds = 0;
    
    public AG(int individuos, int nGenes, double removerPercent){
        this.individuos = individuos;
        this.nGenes = nGenes;
        this.removerPercent = removerPercent;
        this.jogo = new char[3][3];
        this.populacao = new Populacao(individuos, nGenes);
    }
    
    public void resetarAg() {
    	rounds = 0;
    	this.populacao = new Populacao(individuos, nGenes);
    }
    
    public int getMaiorPontuacaoAtual() {
    	return populacao.maiorPontuacaoAtual();
    }
    
    public int getMaiorPontuacaoPossivel() {
    	return ((individuos-1) * 2) * 3;
    }
    
    public void selecaoNatural(){
        jogarTodos();
        populacao.ordena();
        populacao.remover((int) (individuos/100*removerPercent));
        populacao.cruzamento();
        
        rounds++;
    }
    
    public void jogarTodos(){
        Individuo a;
        Individuo b;

        for(int i = 0; i <  this.individuos; i++){
            a =  this.populacao.get(i);
            for(int j = 0; j <  this.individuos; j++){
                if(i != j){
                    b =  this.populacao.get(j);
                    this.jogar(a, b);
                }
            }
        }
    }
    
    public void jogar(Individuo a, Individuo b){
        char[][] jogo = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        
        this.jogo = jogo;
        
        int i = 0;
        int j = 0;
        Integer mA[];
        Integer mB[];
        boolean continua = true;
        
        while(i < this.nGenes && j < this.nGenes && continua){
            for(i = i; i < this.nGenes && continua; i++){
                mA = a.getDnaX(i);
                if(this.jogo[mA[0]][mA[1]] == ' '){
                   this.jogo[mA[0]][mA[1]] = 'X';
                   if(this.venceu('X')){
                       a.ganhou();
                       b.perdeu();
                       continua = false;
                   }
                   break;
                }
            }
            
            for(j = j; j < this.nGenes && continua; j++){
                mB = a.getDnaY(j);
                if(this.jogo[mB[0]][mB[1]] == ' '){
                   this.jogo[mB[0]][mB[1]] = 'O';
                   if(this.venceu('X')){
                       b.ganhou();
                       a.perdeu();
                       continua = false;
                   }
                    break;
                }
            }
        }
        a.jogou();
        b.jogou();
    }
    
    public boolean venceu(char s){
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
            a = this.jogo[vitorias[i][0][0]][vitorias[i][0][1]];
            b = this.jogo[vitorias[i][1][0]][vitorias[i][1][1]];
            c = this.jogo[vitorias[i][2][0]][vitorias[i][2][1]];
            if((a == s) && (b == s) && (c == s)){
                return true;
            }
        }
        
        return false;
    }
    
    public int getMediaTop1(){
        return this.populacao.getTop1().getPontuacao();
    }

    public Populacao getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Populacao populacao) {
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

    public double getRemoverPercent() {
        return removerPercent;
    }

    public void setRemoverPercent(double removerPercent) {
        this.removerPercent = removerPercent;
    }

    public char[][] getJogo() {
        return jogo;
    }

    public void setJogo(char[][] jogo) {
        this.jogo = jogo;
    }

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
    
}
