package comportamentos;

public enum ComportamentoEnum {
    IMPULSIVO("Impulsivo"),
    EXIGENTE("Exigente"),
    CAUTELOSO("Cauteloso"),
    ALEATORIO("Aleatório");

    private String comportamento;

    ComportamentoEnum(String comportamento){
        this.comportamento = comportamento;
    }

    public String getComportamento(){
        return comportamento;
    }
}
