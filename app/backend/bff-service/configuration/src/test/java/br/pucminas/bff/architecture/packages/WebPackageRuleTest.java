package br.pucminas.bff.architecture.packages;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.bff.adapters.web", importOptions = {ImportOption.DoNotIncludeTests.class})
public class WebPackageRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.bff.adapters.web.in..")
                                        .should()
                                        .dependOnClassesThat()
                                        .resideInAnyPackage("br.pucminas.bff.application..",
                                                            "br.pucminas.bff.adapters.web.in..",
                                                            "java..")
                                        .as("Controller Classes Should Depends Other Classes");


    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.bff.adapters.web.out..")
                                            .should()
                                            .dependOnClassesThat()
                                            .resideInAnyPackage("br.pucminas.bff.application..",
                                                                "br.pucminas.bff.adapters.web.out..",
                                                                "java..")
                                            .as("Web Adapter Output Classes Should Depends Other Classes");

}
