package br.pucminas.leads.adapters.scheduler.task;

import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.common.stereotype.SchedulerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.boot.CommandLineRunner;

import static net.logstash.logback.argument.StructuredArguments.kv;

@SchedulerAdapter
@RequiredArgsConstructor
@Slf4j
public class ProcessLeadTask implements CommandLineRunner {

    private static final String LEAD_JOB = "lead_job";

    private final JobScheduler jobScheduler;
    private final ProcessLeadUseCase useCase;

    @Override
    public void run(String... args) throws Exception {
        log.info("Scheduling job: {}", kv("job", LEAD_JOB));
        jobScheduler.scheduleRecurrently(LEAD_JOB, Cron.every5minutes(), useCase::process);
        log.info("Job {} was scheduled successfully", kv("job", LEAD_JOB));
    }
}
