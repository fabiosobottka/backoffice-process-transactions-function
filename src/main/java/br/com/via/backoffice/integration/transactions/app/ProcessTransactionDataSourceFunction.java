package br.com.via.backoffice.integration.transactions.app;

import br.com.via.backoffice.integration.transactions.event.dto.TransactionEvent;
import br.com.via.backoffice.integration.transactions.infra.CallDB2InsertProcedure;
import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class ProcessTransactionDataSourceFunction implements Function<ExecutionContext, Consumer<TransactionEvent>> {

    @Autowired
    private CallDB2InsertProcedure dataSource;

    @Override
    public Consumer<TransactionEvent> apply(ExecutionContext targetContext) {
        return event -> sendEventToDB2(targetContext, event);
    }

    private void sendEventToDB2(ExecutionContext targetContext, TransactionEvent event) {
        targetContext.getLogger().info(() -> "ProcessTransactionDataSourceFunction | Event: %s".formatted(event));
        // TODO call procedure on DB2
    }
}
