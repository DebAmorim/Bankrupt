package tabuleiro;

import java.util.List;

public class Tabuleiro {

    List<Propriedade> propriedadeList;

    public Tabuleiro(List<Propriedade> propriedadeList) {
        this.propriedadeList = propriedadeList;
    }

    public List<Propriedade> getPropriedadeList() {
        return propriedadeList;
    }

    public Propriedade getPropriedade(Integer posicaoTabuleiro){
        if(posicaoTabuleiro >= 0 && posicaoTabuleiro < propriedadeList.size()){
            return propriedadeList.get(posicaoTabuleiro);
        }
        return null;
    }
}
