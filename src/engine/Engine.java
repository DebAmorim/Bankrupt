package engine;

import comportamentos.*;
import jogador.Jogador;
import tabuleiro.Propriedade;
import tabuleiro.Tabuleiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Engine {

    private Tabuleiro tabuleiro;

    private List<Jogador> jogadorList;

    private Integer quantidadeMaximaRodadas;

    private Integer quantidadeTurnos;

    private ComportamentoEnum vencedor;

    private Boolean encerrouPorTimeOut;

    public Engine(Integer quantidadeMaximaRodadas, String nomeArquivoPropriedades) throws IOException{
        this.tabuleiro = new Tabuleiro(constroiPropriedades(nomeArquivoPropriedades));
        this.jogadorList = geraJogadores();
        this.quantidadeMaximaRodadas = quantidadeMaximaRodadas;
        this.quantidadeTurnos = 0;
        this.vencedor = null;
        this.encerrouPorTimeOut = false;
    }

    public Integer getQuantidadeTurnos() {
        return quantidadeTurnos;
    }

    public ComportamentoEnum getVencedor() {
        return vencedor;
    }

    public Boolean getEncerrouPorTimeOut() {
        return encerrouPorTimeOut;
    }

    private List<Propriedade> constroiPropriedades(String nomeArquivoPropriedades) throws IOException {
        List<Propriedade> propriedadeList = new ArrayList<>();

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(nomeArquivoPropriedades));
        String linha;
        String[] valores;
        while ((linha = bufferedReader.readLine()) != null) {
            valores = linha.split("\\s+");
            propriedadeList.add(new Propriedade(Integer.valueOf(valores[1]), Integer.valueOf(valores[0])));
        }

        return propriedadeList;
    }

    private List<Jogador> geraJogadores(){
        List<Jogador> jogadorList = new ArrayList<>();

        List<ComportamentoEnum> ordemJogador = new ArrayList<>();

        ordemJogador.add(ComportamentoEnum.IMPULSIVO);
        ordemJogador.add(ComportamentoEnum.EXIGENTE);
        ordemJogador.add(ComportamentoEnum.CAUTELOSO);
        ordemJogador.add(ComportamentoEnum.ALEATORIO);

        Collections.shuffle(ordemJogador);

        Jogador jogadorImpulsivo = new Jogador(0, 300, ordemJogador.get(0));
        Jogador jogadorExigente = new Jogador(1, 300, ordemJogador.get(1));
        Jogador jogadorCauteloso = new Jogador(2, 300, ordemJogador.get(2));
        Jogador jogadorAleatorio = new Jogador(3, 300, ordemJogador.get(3));


        jogadorList.add(jogadorImpulsivo);
        jogadorList.add(jogadorExigente);
        jogadorList.add(jogadorCauteloso);
        jogadorList.add(jogadorAleatorio);

        return jogadorList;
    }

    private Integer rolarDado(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }

    private Boolean decideCompra(Jogador jogador, Propriedade propriedade){
        switch (jogador.getComportamento()){
            case IMPULSIVO:
                return new Impulsivo().decidir(jogador.getCoins(), propriedade.getValorAluguel(), propriedade.getValorVenda());
            case EXIGENTE:
                return new Exigente().decidir(jogador.getCoins(), propriedade.getValorAluguel(), propriedade.getValorVenda());
            case CAUTELOSO:
                return new Cauteloso().decidir(jogador.getCoins(), propriedade.getValorAluguel(), propriedade.getValorVenda());
            case ALEATORIO:
                return new Aleatorio().decidir(jogador.getCoins(), propriedade.getValorAluguel(), propriedade.getValorVenda());
            default:
                return null;
        }
    }

    private void verificarVencedor(){
        Integer falidos = 0;
        ComportamentoEnum vencedor = ComportamentoEnum.IMPULSIVO;
        for (Jogador jogador : jogadorList) {
            if(jogador.getFalido()){
                falidos ++;
            } else {
                vencedor = jogador.getComportamento();
            }
        }
        if(falidos == 3){
            this.vencedor = vencedor;
        }
    }

    private ComportamentoEnum verificaMaiorSaldo(){
        ComportamentoEnum vencedor = ComportamentoEnum.IMPULSIVO;
        Integer maiorSaldo = 0;
        for (Jogador jogador : jogadorList) {
            if(jogador.getCoins()>maiorSaldo){
                maiorSaldo = jogador.getCoins();
                vencedor = jogador.getComportamento();
            }
        }
        return vencedor;
    }

    public void jogar(){
        int rodada = 0;
        for(rodada = 0; rodada < quantidadeMaximaRodadas; rodada ++){
            jogadorList.forEach(jogador -> {
                if(vencedor == null && !jogador.getFalido()){
                    if(jogador.andar(rolarDado())){
                        jogador.receber(100);
                    }

                    Integer posicaoAtual = jogador.getPosicaoAtual();
                    Propriedade propriedadeAtual = tabuleiro.getPropriedade(posicaoAtual);

                    if(propriedadeAtual.getProprietario() == null){
                        Boolean comprarPropriedade = decideCompra(jogador, propriedadeAtual);
                        if(comprarPropriedade != null && comprarPropriedade){
                            jogador.pagar(propriedadeAtual.getValorVenda());
                            propriedadeAtual.setProprietario(jogador);
                        }
                    } else if(!propriedadeAtual.getProprietario().getId().equals(jogador.getId())) {
                        propriedadeAtual.getProprietario().receber(jogador.pagar(propriedadeAtual.getValorAluguel()));
                    }

                    if(jogador.getCoins() < 0){
                        jogador.setFalido(true);
                        tabuleiro.liberarPropriedades(jogador);
                        verificarVencedor();
                    }

                    quantidadeTurnos ++;

                }
            });
            if(vencedor != null){
                break;
            }
        }
        if(rodada >= quantidadeMaximaRodadas){
            encerrouPorTimeOut = true;
            vencedor = verificaMaiorSaldo();
        }

    }
}
