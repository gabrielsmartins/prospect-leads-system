package br.pucminas.products.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.products.adapters.persistence", importOptions = {ImportOption.DoNotIncludeTests.class})
public class PersistencePackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.products.adapters.persistence..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.products.application..",
                                                                    "br.pucminas.products.adapters.persistence..")
                                            .as("Adapter Classes Should Depends Other Classes");

}
