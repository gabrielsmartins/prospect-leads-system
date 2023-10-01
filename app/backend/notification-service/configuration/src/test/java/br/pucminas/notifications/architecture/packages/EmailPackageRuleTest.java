package br.pucminas.notifications.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.notifications.adapters.email", importOptions = {ImportOption.DoNotIncludeTests.class})
public class EmailPackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.notifications.adapters.email..")
                                        .should()
                                        .dependOnClassesThat()
                                        .resideInAnyPackage("br.pucminas.notifications.application..",
                                                            "br.pucminas.notifications.adapters.email..",
                                                            "java..")
                                        .as("Controller Classes Should Depends Other Classes");

}
