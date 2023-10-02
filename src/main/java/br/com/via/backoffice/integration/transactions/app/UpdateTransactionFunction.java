package br.com.via.backoffice.integration.transactions.app;

import br.com.via.backoffice.integration.transactions.event.dto.TransactionEvent;
import br.com.via.backoffice.integration.transactions.infra.api.SpringFeignPlatformsApi;
import br.com.via.backoffice.integration.transactions.infra.api.TransactionUpdateRequest;
import br.com.via.backoffice.integration.transactions.infra.api.dto.TransactionStatus;
import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class UpdateTransactionFunction  implements Function<ExecutionContext, Consumer<TransactionEvent>> {

    @Autowired
    private SpringFeignPlatformsApi api;

    @Override
    public Consumer<TransactionEvent> apply(ExecutionContext targetContext) {
        return event -> updateTransactionFunction(targetContext, event);
    }

    private void updateTransactionFunction(ExecutionContext targetContext, TransactionEvent event) {
        targetContext.getLogger().info(() -> "UpdateTransactionFunction | Event: %s".formatted(event));
        api.updateStatus(event.getTransactionId(), new TransactionUpdateRequest(TransactionStatus.PROCESSED));
    }
}
