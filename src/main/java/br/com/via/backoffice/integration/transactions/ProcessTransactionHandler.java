package br.com.via.backoffice.integration.transactions;

import br.com.via.backoffice.integration.transactions.app.ProcessTransactionDataSourceFunction;
import br.com.via.backoffice.integration.transactions.app.UpdateTransactionFunction;
import br.com.via.backoffice.integration.transactions.event.dto.TransactionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.KafkaTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Level;


@Component
public class ProcessTransactionHandler {

    @Autowired
    private ProcessTransactionDataSourceFunction processTransactionDataSourceFunction;

    @Autowired
    private UpdateTransactionFunction updateTransactionFunction;

    @FunctionName("KafkaTriggerFunction")
    public void run(
            @KafkaTrigger(
                    name = "onInsertTransactionEvent",
                    topic = "transactions",
                    consumerGroup = "backoffice-process-group-1",
                    brokerList = "BootstrapServer"
            ) String kafkaEvent,
            final ExecutionContext context
    ) throws JsonProcessingException {

        final TransactionEvent transactionEvent = getTransactionEvent(kafkaEvent);
        context.getLogger().log(Level.INFO, () -> "Received Kafka event: %s".formatted(transactionEvent));

        processTransactionDataSourceFunction
                .apply(context)
                .accept(transactionEvent);


        updateTransactionFunction
                .apply(context)
                .accept(transactionEvent);
    }

    private static TransactionEvent getTransactionEvent(String kafkaEvent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = (String) mapper.readValue(kafkaEvent, Map.class).get("Value");
        return mapper.readValue(value, TransactionEvent.class);
    }

}
