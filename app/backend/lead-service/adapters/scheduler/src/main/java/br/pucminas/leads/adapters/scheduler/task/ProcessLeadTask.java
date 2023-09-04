package br.pucminas.leads.adapters.scheduler.task;

import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.out.SearchLeadPort;
import br.pucminas.leads.common.stereotype.SchedulerAdapter;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@SchedulerAdapter
@RequiredArgsConstructor
public class ProcessLeadTask {

    private static final String LEAD_JOB = "lead_job";

    private final JobScheduler jobScheduler;
    private final SearchLeadPort searchLeadPort;
    private final ProcessLeadUseCase useCase;

    @Scheduled(fixedRate = 1000L)
    public void schedule() {
        var dateTime = LocalDateTime.now().minusMinutes(30);
        var leads = this.searchLeadPort.findAllPendingReceivedLessThan(dateTime);
        jobScheduler.scheduleRecurrently(LEAD_JOB, Cron.everyHalfHour(), () -> useCase.process(leads));
    }

}
