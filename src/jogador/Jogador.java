package jogador;

import comportamentos.ComportamentoEnum;

public class Jogador {

    private Integer id;

    private Integer coins;

    private ComportamentoEnum comportamento;

    private Boolean falido;

    private Integer posicaoAtual;

    public Jogador(Integer id, Integer coins, ComportamentoEnum comportamento) {
        this.id = id;
        this.coins = coins;
        this.comportamento = comportamento;
        this.falido = false;
        this.posicaoAtual = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoins() {
        return coins;
    }

    public ComportamentoEnum getComportamento() {
        return comportamento;
    }

    public void setComportamento(ComportamentoEnum comportamento) {
        this.comportamento = comportamento;
    }

    public Boolean getFalido() {
        return falido;
    }

    public void setFalido(Boolean falido) {
        this.falido = falido;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void pagar(Integer valor){
        this.coins -= valor;
    }

    public void receber(Integer valor){
        this.coins += valor;
    }

    public Boolean andar(Integer passos){
        if(this.posicaoAtual == null){
            this.posicaoAtual = passos -1;
        } else {
            this.posicaoAtual += passos;
        }
        if(this.posicaoAtual > 19){
            this.posicaoAtual -= 20;
            return true;
        }

        return false;

    }


}
