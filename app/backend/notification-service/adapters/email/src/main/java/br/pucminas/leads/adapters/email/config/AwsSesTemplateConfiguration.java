package br.pucminas.leads.adapters.email.config;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.CreateTemplateRequest;
import com.amazonaws.services.simpleemail.model.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AwsSesTemplateConfiguration {

    public static final String TEMPLATE = "OfferTemplate";
    private final AmazonSimpleEmailService emailService;

    @PostConstruct
    public void init() {
        try {
            var template = new Template();
            template.setTemplateName(TEMPLATE);
            template.setSubjectPart("Olá {{customer_name}}");
            template.setHtmlPart(this.getResourceFileAsString("templates/template.html"));

            log.info("Registering e-mail template: {}", TEMPLATE);
            var request = new CreateTemplateRequest();
            request.setTemplate(template);
            emailService.createTemplate(request);
            log.info("E-mail {} template was registered successfully", TEMPLATE);
        } catch (AmazonSimpleEmailServiceException ex) {
            log.warn("Template already exists", ex);
        }
    }

    private String getResourceFileAsString(String fileName) {
        var is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    private InputStream getResourceFileAsInputStream(String fileName) {
        var classLoader = AwsSesConfiguration.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

}
