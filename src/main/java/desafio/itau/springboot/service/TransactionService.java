package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void clearTransaction(){
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistic(){
        OffsetDateTime now = OffsetDateTime.now();
            return transactions.stream()
                    .filter(t -> t.getDatahora().isAfter(now.minusSeconds(60)))
                    .mapToDouble(Transaction::getValor)
                    .summaryStatistics();
    }

    ///-----DAR COMMIT DE REGRA DE NEGÓCIO------

}
