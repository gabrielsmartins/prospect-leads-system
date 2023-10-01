package br.pucminas.notifications.architecture.classes;

import br.pucminas.notifications.common.stereotype.MessagingAdapter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.pucminas.notifications.adapters.messaging", importOptions = {ImportOption.DoNotIncludeTests.class})
public class MessagingClassRuleTest {

    @ArchTest
    public static ArchRule adapterInRule = classes()
                                        .that()
                                        .resideInAPackage("br.pucminas.notifications.adapters.messaging.in..")
                                        .and()
                                        .areAnnotatedWith(MessagingAdapter.class)
                                        .should()
                                        .haveNameMatching(".*Consumer")
                                        .as("Consumer Classes Should Ends With Name Consumer");


    @ArchTest
    public static ArchRule adapterOutRule = classes()
                                            .that()
                                            .resideInAPackage("br.pucminas.notifications.adapters.messaging.out..")
                                            .and()
                                            .areAnnotatedWith(MessagingAdapter.class)
                                            .should()
                                            .haveNameMatching(".*Producer")
                                            .as("Producer Classes Should Ends With Name Producer");

}
