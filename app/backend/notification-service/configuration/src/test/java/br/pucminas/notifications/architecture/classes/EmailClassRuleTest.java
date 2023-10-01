package br.pucminas.notifications.architecture.classes;

import br.pucminas.notifications.common.stereotype.EmailAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.notifications.adapters.email", importOptions = {ImportOption.DoNotIncludeTests.class})
public class EmailClassRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.notifications.adapters.email..")
                                        .and()
                                        .areAnnotatedWith(EmailAdapter.class)
                                        .should()
                                        .haveNameMatching(".*EmailAdapter")
                                        .as("Email Adapter Classes Should Ends With Name EmailAdapter");
    
}
