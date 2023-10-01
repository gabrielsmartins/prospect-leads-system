package br.pucminas.leads.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.adapters.streams", importOptions = {ImportOption.DoNotIncludeTests.class})
public class StreamPackageRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.streams.in..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.leads.application..",
                                                    "br.pucminas.leads.adapters.streams..")
                                            .as("Stream Adapter Output Classes Depends Other Classes");

}
