package br.pucminas.leads.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.adapters.scheduler", importOptions = {ImportOption.DoNotIncludeTests.class})
public class SchedulerPackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.scheduler..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.leads.application..",
                                                    "br.pucminas.leads.adapters.scheduler..",
                                                    "org.jobrunr..",
                                                    "org.springframework..")
                                            .as("Scheduler Classes Depends Other Classes");

}
