package comportamentos;

public class Exigente implements Comportamento{
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {

        if(coins<preco){
            return false;
        }

        return aluguel>50;
    }
}
