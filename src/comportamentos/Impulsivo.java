package comportamentos;

public class Impulsivo implements Comportamento {

    @Override
    public boolean decidir(int coins, int aluguel, int preco) {

        if(coins<preco){
            return false;
        }

        return true;
    }
}
