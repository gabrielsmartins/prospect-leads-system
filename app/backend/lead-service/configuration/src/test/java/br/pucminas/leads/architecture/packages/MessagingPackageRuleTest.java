package br.pucminas.leads.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.adapters.messaging", importOptions = {ImportOption.DoNotIncludeTests.class})
public class MessagingPackageRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.messaging.in..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.leads.application..",
                                                    "br.pucminas.leads.adapters.messaging..")
                                            .as("Messaging Adapter Input Classes Depends Other Classes");
    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.messaging.out..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.leads.application..",
                                                    "br.pucminas.leads.adapters.messaging..")
                                            .as("Messaging Adapter Output Classes Depends Other Classes");

}
