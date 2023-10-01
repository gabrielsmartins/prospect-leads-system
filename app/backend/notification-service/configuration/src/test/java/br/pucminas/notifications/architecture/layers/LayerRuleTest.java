package br.pucminas.notifications.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.pucminas.notifications", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerRuleTest {

    private static final String APPLICATION_PACKAGE = "br.pucminas.notifications.application..";
    private static final String MESSAGING_PACKAGE = "br.pucminas.notifications.adapters.messaging..";
    private static final String EMAIL_PACKAGE = "br.pucminas.notifications.adapters.email..";
    private static final String CONFIGURATION_PACKAGE = "br.pucminas.notifications";
    private static final String APPLICATION = "application";
    private static final String MESSAGING = "messaging";
    private static final String EMAIL = "email";
    private static final String CONFIGURATION = "configuration";

    @ArchTest
    public static final ArchRule layerRule = layeredArchitecture()
                                                .consideringAllDependencies()
                                                .layer(APPLICATION).definedBy(APPLICATION_PACKAGE)
                                                .layer(MESSAGING).definedBy(MESSAGING_PACKAGE)
                                                .layer(EMAIL).definedBy(EMAIL_PACKAGE)
                                                .layer(CONFIGURATION).definedBy(CONFIGURATION_PACKAGE)
                                                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(MESSAGING, EMAIL, CONFIGURATION)
                                                .whereLayer(MESSAGING).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(EMAIL).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(CONFIGURATION).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .as("Others Layers Should Only Depend Application Layer");

}
