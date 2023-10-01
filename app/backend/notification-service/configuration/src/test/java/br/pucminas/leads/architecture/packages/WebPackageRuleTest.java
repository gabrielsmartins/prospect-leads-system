package br.pucminas.leads.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.quotes.adapters.web", importOptions = {ImportOption.DoNotIncludeTests.class})
public class WebPackageRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.quotes.adapters.web.in..")
                                        .should()
                                        .dependOnClassesThat()
                                        .resideInAnyPackage("br.pucminas.quotes.application..",
                                                            "br.pucminas.products.adapters.web.in..",
                                                            "java..")
                                        .as("Controller Classes Should Depends Other Classes");


    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.quotes.adapters.web.out..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.quotes.application..",
                                                                "br.pucminas.quotes.adapters.web.out..",
                                                                "java..")
                                            .as("Web Adapter Output Classes Should Depends Other Classes");

}
