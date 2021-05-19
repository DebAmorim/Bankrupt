package comportamentos;

public class Cauteloso implements Comportamento{
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {
        return coins - preco >= 80;
    }
}
