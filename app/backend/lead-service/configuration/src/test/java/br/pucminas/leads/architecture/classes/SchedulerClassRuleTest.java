package br.pucminas.leads.architecture.classes;

import br.pucminas.leads.common.stereotype.SchedulerAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.adapters.scheduler", importOptions = {ImportOption.DoNotIncludeTests.class})
public class SchedulerClassRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.scheduler.task..")
                                            .and()
                                            .areAnnotatedWith(SchedulerAdapter.class)
                                            .should()
                                            .haveNameMatching(".*Task")
                                            .as("Scheduler Classes Should Ends With Name Task");

}
