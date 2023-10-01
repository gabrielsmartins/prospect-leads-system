package br.pucminas.quotes.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.quotes.adapters.persistence", importOptions = {ImportOption.DoNotIncludeTests.class})
public class PersistencePackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.quotes.adapters.persistence..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.quotes.application..",
                                                                    "br.pucminas.quotes.adapters.persistence..")
                                            .as("Adapter Classes Should Depends Other Classes");

}
