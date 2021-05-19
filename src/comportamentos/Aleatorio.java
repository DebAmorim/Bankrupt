package comportamentos;

import java.util.Random;

public class Aleatorio implements Comportamento {
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {
        Random rand = new Random();

        return rand.nextBoolean();
    }
}
