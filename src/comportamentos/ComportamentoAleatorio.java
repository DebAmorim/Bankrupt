package comportamentos;

import java.util.Random;

public class ComportamentoAleatorio implements Comportamento {
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {
        Random rand = new Random();

        if(coins<preco){
            return false;
        }

        return rand.nextBoolean();
    }
}
