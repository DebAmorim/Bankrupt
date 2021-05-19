package comportamentos;

public class Exigente implements Comportamento{
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {

        return aluguel>50;
    }
}
