package br.com.via.backoffice.integration.transactions.infra;

import br.com.via.backoffice.integration.transactions.infra.data.TransactionData;
import br.com.via.backoffice.integration.transactions.event.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CallDB2InsertProcedure {

    private final Logger logger = LoggerFactory.getLogger(CallDB2InsertProcedure.class);

    public TransactionData execute(TransactionEvent event) {
        logger.info("nothing yet {}", event);
        return new TransactionData();
    }
}
