package br.pucminas.bff.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.bff.adapters.streams", importOptions = {ImportOption.DoNotIncludeTests.class})
public class StreamPackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.bff.adapters.streams.out..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.bff.application..",
                                                    "br.pucminas.bff.adapters.streams..")
                                            .as("Stream Adapter Output Classes Depends Other Classes");

}
