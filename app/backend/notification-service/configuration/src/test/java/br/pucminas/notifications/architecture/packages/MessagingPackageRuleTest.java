package br.pucminas.notifications.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.notifications.adapters.messaging", importOptions = {ImportOption.DoNotIncludeTests.class})
public class MessagingPackageRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.notifications.adapters.messaging.in..")
                                        .should()
                                        .dependOnClassesThat()
                                        .resideInAnyPackage("br.pucminas.notifications.application..",
                                                            "br.pucminas.notifications.adapters.messaging.in..",
                                                            "java..")
                                        .as("Consumer Classes Should Depends Other Classes");


    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.notifications.adapters.messaging.out..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.notifications.application..",
                                                                "br.pucminas.notifications.adapters.messaging.out..",
                                                                "java..")
                                            .as("Producer Classes Should Depends Other Classes");

}
