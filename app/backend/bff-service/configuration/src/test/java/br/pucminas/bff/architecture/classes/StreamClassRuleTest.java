package br.pucminas.bff.architecture.classes;

import br.pucminas.bff.common.stereotype.StreamAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.bff.adapters.streams", importOptions = {ImportOption.DoNotIncludeTests.class})
public class StreamClassRuleTest {

    @ArchTest
    public static ArchRule adapterRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.bff.adapters.streams.out..")
                                            .and()
                                            .areAnnotatedWith(StreamAdapter.class)
                                            .should()
                                            .haveNameMatching(".*Publisher")
                                            .as("Stream Adapter Output Classes Should Ends With Name Publisher");

}
