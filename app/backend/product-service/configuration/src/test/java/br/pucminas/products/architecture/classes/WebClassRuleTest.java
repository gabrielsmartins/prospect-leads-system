package br.pucminas.products.architecture.classes;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.products.adapters.web", importOptions = {ImportOption.DoNotIncludeTests.class})
public class WebClassRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.products.adapters.web.in..")
                                        .and()
                                        .areAnnotatedWith(RestController.class)
                                        .should()
                                        .haveNameMatching(".*Controller")
                                        .as("Controller Classes Should Ends With Name Controller");

}
