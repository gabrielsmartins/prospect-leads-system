package br.pucminas.leads.architecture.classes;

import br.pucminas.leads.common.stereotype.WebAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.leads.adapters.web", importOptions = {ImportOption.DoNotIncludeTests.class})
public class WebClassRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.leads.adapters.web.in..")
                                        .and()
                                        .areAnnotatedWith(RestController.class)
                                        .should()
                                        .haveNameMatching(".*Controller")
                                        .as("Controller Classes Should Ends With Name Controller");


    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.leads.adapters.web.out..")
                                            .and()
                                            .areAnnotatedWith(WebAdapter.class)
                                            .should()
                                            .haveNameMatching(".*WebAdapter")
                                            .as("Web Adapter Output Classes Should Ends With Name WebAdapter");

}
