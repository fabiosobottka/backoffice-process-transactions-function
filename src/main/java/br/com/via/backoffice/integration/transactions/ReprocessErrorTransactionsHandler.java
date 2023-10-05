package br.com.via.backoffice.integration.transactions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.springframework.stereotype.Component;

@Component
public class ReprocessErrorTransactionsHandler {

    @FunctionName("processTransactionErrors")
    public void run(@TimerTrigger(name = "keepAliveTrigger", schedule = "0 */5 * * * *") String timerInfo,
                    ExecutionContext context) {

        context.getLogger().info("Timer is triggered: " + timerInfo);

    }

}
