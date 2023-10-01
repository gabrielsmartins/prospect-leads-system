package br.pucminas.products.architecture.classes;

import br.pucminas.products.common.stereotype.PersistenceAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.products.adapters.persistence", importOptions = {ImportOption.DoNotIncludeTests.class})
public class PersistenceClassRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.products.adapters.persistence..")
                                            .and()
                                            .areAnnotatedWith(PersistenceAdapter.class)
                                            .should()
                                            .haveNameMatching(".*PersistenceAdapter")
                                            .as("Adapter Classes Should Ends With Name PersistenceAdapter");

}
