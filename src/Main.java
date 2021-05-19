import comportamentos.ComportamentoEnum;
import engine.Engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author Débora Amorim
 */

public class Main {

    public static void main (String args[]) throws IOException {

        /**
         * Parâmetros da simulação
         */

        Integer quantidadePartidas = 300;
        Integer quantidadeRodadasMax = 1000;
        String nomeArquivoConfiguracao = "gameConfig.txt";

        /**
         * Variáveis para análise estatística
         */

        Float quantidadeTurnos = Float.valueOf(0);
        Float quantidadeRodadas = Float.valueOf(0);
        Float vitoriasImpulsivo = Float.valueOf(0);
        Float vitoriasExigente = Float.valueOf(0);
        Float vitoriasCauteloso = Float.valueOf(0);
        Float vitoriasAleatorio = Float.valueOf(0);
        Float vitoriasPorTimeOut = Float.valueOf(0);
        final Float[] maiorVitoria = {Float.valueOf(0)};

        for (int i = 0; i < quantidadePartidas; i++) {
            Engine engine = new Engine(quantidadeRodadasMax, nomeArquivoConfiguracao);
            engine.jogar();
            quantidadeTurnos += engine.getQuantidadeTurnos();
            quantidadeRodadas += engine.getQuantidadeRodadas();
            switch (engine.getVencedor()){
                case IMPULSIVO:
                    vitoriasImpulsivo ++;
                    break;
                case EXIGENTE:
                    vitoriasExigente ++;
                    break;
                case CAUTELOSO:
                    vitoriasCauteloso ++;
                    break;
                case ALEATORIO:
                    vitoriasAleatorio ++;
                    break;
            }
            if(engine.getEncerrouPorTimeOut()){
                vitoriasPorTimeOut ++;
            }
        }
        quantidadeTurnos = quantidadeTurnos/quantidadePartidas;
        quantidadeRodadas = quantidadeRodadas/quantidadePartidas;
        vitoriasImpulsivo = vitoriasImpulsivo/quantidadePartidas;
        vitoriasExigente = vitoriasExigente/quantidadePartidas;
        vitoriasCauteloso = vitoriasCauteloso/quantidadePartidas;
        vitoriasAleatorio = vitoriasAleatorio/quantidadePartidas;

        Map<Float, ComportamentoEnum> mapaVitorias = new HashMap<>();
        mapaVitorias.put(vitoriasImpulsivo, ComportamentoEnum.IMPULSIVO);
        mapaVitorias.put(vitoriasExigente, ComportamentoEnum.EXIGENTE);
        mapaVitorias.put(vitoriasCauteloso, ComportamentoEnum.CAUTELOSO);
        mapaVitorias.put(vitoriasAleatorio, ComportamentoEnum.ALEATORIO);

        mapaVitorias.forEach((aFloat, comportamentoEnum) -> {
            if(aFloat > maiorVitoria[0]){
                maiorVitoria[0] = aFloat;
            }
        });

        System.out.println(
                "Partidas encerradas por time out: " + vitoriasPorTimeOut + "\n" +
                        "Quantidade média de rodadas: " + quantidadeRodadas + "\n" +
                        "Quantidade média de turnos: " + quantidadeTurnos + "\n" +
                        "Vitórias - Impulsivo: " + vitoriasImpulsivo*100 + "% \n" +
                        "Vitórias - Exigente: " + vitoriasExigente*100 + "% \n" +
                        "Vitórias - Cauteloso: " + vitoriasCauteloso*100 + "% \n" +
                        "Vitórias - Aleatório: " + vitoriasAleatorio*100 + "% \n" +
                        "Comportamento com mais vitórias: " + mapaVitorias.get(maiorVitoria[0]).toString()
        );
    }
}
