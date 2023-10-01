package br.pucminas.leads.architecture.classes;

import br.pucminas.leads.common.stereotype.UseCase;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.application", importOptions = {ImportOption.DoNotIncludeTests.class})
public class DomainClassRuleTest {

    @ArchTest
    public static ArchRule portInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.leads.application.ports.in..")
                                        .should()
                                        .haveNameMatching(".*UseCase")
                                        .as("Use case interface should ends with name UseCase");

    @ArchTest
    public static ArchRule portOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.application.ports.out..")
                                            .should()
                                            .haveNameMatching(".*Port")
                                            .as("Port interface should ends with name Port");

    @ArchTest
    public static ArchRule serviceRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.application.service..")
                                            .and()
                                            .areAnnotatedWith(UseCase.class)
                                            .should()
                                            .haveNameMatching(".*Service")
                                            .as("Service class should ends with name Service");
}
