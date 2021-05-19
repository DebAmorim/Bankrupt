package comportamentos;

public class ComportamentoCauteloso implements Comportamento{
    @Override
    public boolean decidir(int coins, int aluguel, int preco) {
        return coins - preco >= 80;
    }
}
