import engine.Engine;

import java.io.IOException;

public class Main {

    public static void main (String args[]) throws IOException {

        Float quantidadeTurnos = Float.valueOf(0);
        Float vitoriasImpulsivo = Float.valueOf(0);
        Float vitoriasExigente = Float.valueOf(0);
        Float vitoriasCauteloso = Float.valueOf(0);
        Float vitoriasAleatorio = Float.valueOf(0);
        Float vitoriasPorTimeOut = Float.valueOf(0);

        for (int i = 0; i < 300; i++) {
            Engine engine = new Engine(1000, "gameConfig.txt");
            engine.jogar();
            quantidadeTurnos += engine.getQuantidadeTurnos();
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
        quantidadeTurnos = quantidadeTurnos/300;
        vitoriasImpulsivo = vitoriasImpulsivo/300;
        vitoriasExigente = vitoriasExigente/300;
        vitoriasCauteloso = vitoriasCauteloso/300;
        vitoriasAleatorio = vitoriasAleatorio/300;



        System.out.println(
                "Partidas encerradas por time out: " + vitoriasPorTimeOut + "\n" +
                        "Quantidade média de turnos: " + quantidadeTurnos + "\n" +
                        "Vitórias - Impulso: " + vitoriasImpulsivo*100 + "% \n" +
                        "Vitórias - Exigente: " + vitoriasExigente*100 + "% \n" +
                        "Vitórias - Cauteloso: " + vitoriasCauteloso*100 + "% \n" +
                        "Vitórias - Aleatório: " + vitoriasAleatorio*100 + "% \n"
        );
    }

//    TODO informar comportamento que mais vence
}
