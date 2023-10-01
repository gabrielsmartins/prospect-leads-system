package br.pucminas.bff.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.pucminas.bff", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerRuleTest {

    private static final String APPLICATION_PACKAGE = "br.pucminas.bff.application..";
    private static final String WEB_PACKAGE = "br.pucminas.bff.adapters.web..";
    private static final String STREAM_PACKAGE = "br.pucminas.bff.adapters.streams..";
    private static final String CONFIGURATION_PACKAGE = "br.pucminas.bff";
    private static final String APPLICATION = "application";
    private static final String STREAM = "stream";
    private static final String WEB = "web";
    private static final String CONFIGURATION = "configuration";

    @ArchTest
    public static final ArchRule layerRule = layeredArchitecture()
                                                .consideringAllDependencies()
                                                .layer(APPLICATION).definedBy(APPLICATION_PACKAGE)
                                                .layer(WEB).definedBy(WEB_PACKAGE)
                                                .layer(STREAM).definedBy(STREAM_PACKAGE)
                                                .layer(CONFIGURATION).definedBy(CONFIGURATION_PACKAGE)
                                                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(WEB, STREAM, CONFIGURATION)
                                                .whereLayer(WEB).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(STREAM).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(CONFIGURATION).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .as("Others Layers Should Only Depend Application Layer");

}
