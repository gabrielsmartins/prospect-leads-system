package br.pucminas.leads.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "br.pucminas.leads", importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayerRuleTest {

    private static final String APPLICATION_PACKAGE = "br.pucminas.leads.application..";
    private static final String WEB_PACKAGE = "br.pucminas.leads.adapters.web..";
    private static final String PERSISTENCE_PACKAGE = "br.pucminas.leads.adapters.persistence..";
    private static final String STREAM_PACKAGE = "br.pucminas.leads.adapters.streams..";
    private static final String MESSAGING_PACKAGE = "br.pucminas.leads.adapters.messaging..";
    private static final String SCHEDULER_PACKAGE = "br.pucminas.leads.adapters.scheduler..";
    private static final String CONFIGURATION_PACKAGE = "br.pucminas.leads";
    private static final String APPLICATION = "application";
    private static final String STREAM = "stream";
    private static final String MESSAGING = "messaging";
    private static final String SCHEDULER = "scheduler";
    private static final String WEB = "web";
    private static final String PERSISTENCE = "persistence";
    private static final String CONFIGURATION = "configuration";

    @ArchTest
    public static final ArchRule layerRule = layeredArchitecture()
                                                .consideringAllDependencies()
                                                .layer(APPLICATION).definedBy(APPLICATION_PACKAGE)
                                                .layer(WEB).definedBy(WEB_PACKAGE)
                                                .layer(PERSISTENCE).definedBy(PERSISTENCE_PACKAGE)
                                                .layer(MESSAGING).definedBy(MESSAGING_PACKAGE)
                                                .layer(SCHEDULER).definedBy(SCHEDULER_PACKAGE)
                                                .layer(STREAM).definedBy(STREAM_PACKAGE)
                                                .layer(CONFIGURATION).definedBy(CONFIGURATION_PACKAGE)
                                                .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(WEB, PERSISTENCE, MESSAGING, SCHEDULER, STREAM, CONFIGURATION)
                                                .whereLayer(WEB).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(PERSISTENCE).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(MESSAGING).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(SCHEDULER).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(STREAM).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .whereLayer(CONFIGURATION).mayOnlyBeAccessedByLayers(CONFIGURATION)
                                                .as("Others Layers Should Only Depend Application Layer");

}
