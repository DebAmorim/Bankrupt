package tabuleiro;

import jogador.Jogador;

public class Propriedade {

    private Jogador proprietario;

    private Integer valorAluguel;

    private Integer valorVenda;

    public Propriedade(Integer valorAluguel, Integer valorVenda) {
        this.proprietario = null;
        this.valorAluguel = valorAluguel;
        this.valorVenda = valorVenda;
    }

    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }

    public Integer getValorAluguel() {
        return valorAluguel;
    }

    public Integer getValorVenda() {
        return valorVenda;
    }

}
