package desafio.itau.springboot.model;

import java.time.OffsetDateTime;

public class Transaction {

    private double valor;
    private OffsetDateTime datahora;


    public Transaction(final double valor, final OffsetDateTime datahora) {
        this.valor = valor;
        this.datahora = datahora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDatahora() {
        return datahora;
    }

}
