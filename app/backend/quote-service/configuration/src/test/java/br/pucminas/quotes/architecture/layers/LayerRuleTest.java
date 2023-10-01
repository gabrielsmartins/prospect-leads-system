package br.pucminas.quotes.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.pucminas.quotes", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerRuleTest {

    private static final String APPLICATION_PACKAGE = "br.pucminas.quotes.application..";
    private static final String WEB_PACKAGE = "br.pucminas.quotes.adapters.web..";
    private static final String PERSISTENCE_PACKAGE = "br.pucminas.quotes.adapters.persistence..";
    private static final String CONFIGURATION_PACKAGE = "br.pucminas.quotes";
    private static final String APPLICATION = "application";
    private static final String PERSISTENCE = "persistence";
    private static final String WEB = "web";
    private static final String CONFIGURATION = "configuration";

    @ArchTest
    public static final ArchRule layerRule = layeredArchitecture()
                                                .consideringAllDependencies()
                                                .layer(APPLICATION).definedBy(APPLICATION_PACKAGE)
                                                .layer(WEB).definedBy(WEB_PACKAGE)
                                                .layer(PERSISTENCE).definedBy(PERSISTENCE_PACKAGE)
                                                .layer(CONFIGURATION).definedBy(CONFIGURATION_PACKAGE)
                                                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(WEB, PERSISTENCE, CONFIGURATION)
                                                .whereLayer(WEB).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(PERSISTENCE).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(CONFIGURATION).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .as("Others Layers Should Only Depend Application Layer");

}
