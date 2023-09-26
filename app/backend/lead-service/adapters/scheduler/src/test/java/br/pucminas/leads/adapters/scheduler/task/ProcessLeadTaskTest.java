package br.pucminas.leads.adapters.scheduler.task;

import br.pucminas.leads.adapters.scheduler.config.StorageProviderConfiguration;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.spring.autoconfigure.JobRunrAutoConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Import({ProcessLeadTask.class, JobRunrAutoConfiguration.class, StorageProviderConfiguration.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ActiveProfiles("test")
class ProcessLeadTaskTest {


    @InjectMocks
    private ProcessLeadTask task;

    @SpyBean
    private JobScheduler jobScheduler;

    @MockBean
    private ProcessLeadUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(task, "jobScheduler", jobScheduler);
        ReflectionTestUtils.setField(task, "useCase", useCase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Given Application When Startup Then Register Job")
    public void givenApplicationWhenStartupThenRegisterJob() throws Exception {
        this.task.run("args");
        verify(this.jobScheduler, times(1)).scheduleRecurrently(anyString(), eq(Cron.every5minutes()), any(JobLambda.class));
    }

}