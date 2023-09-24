package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.config.AmazonDynamoDBConfiguration;
import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import br.pucminas.leads.adapters.persistence.support.DynamoDBExtension;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static br.pucminas.leads.adapters.persistence.support.LeadEntitySupport.defaultLeadEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class, DynamoDBExtension.class})
@ActiveProfiles("test")
@Import({AmazonDynamoDBConfiguration.class, LeadRepository.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class LeadRepositoryTest {
    private static final String TABLE_NAME = "Leads";

    @Autowired
    private LeadRepository repository;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @BeforeEach
    public void setup() {
        try {
            var config = new DynamoDBMapperConfig.TableNameOverride(TABLE_NAME).config();
            var tableRequest = dynamoDBMapper.generateCreateTableRequest(LeadEntity.class, config);
            tableRequest.setBillingMode(BillingMode.PAY_PER_REQUEST.toString());
            //tableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            // Do nothing, table already created
        }
    }

    @Test
    @DisplayName("Given Lead When Save Then Return Saved Lead")
    public void givenLeadWhenSaveThenReturnSavedLead() {
        var leadEntity = defaultLeadEntity().build();

        var savedLeadEntity = this.repository.save(leadEntity);

        assertThat(savedLeadEntity).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Lead")
    public void givenIdWhenExistsThenReturnLead() {
        var leadEntity = defaultLeadEntity().build();
        var savedLeadEntity = this.repository.save(leadEntity);

        var optionalLeadEntity = this.repository.findById(savedLeadEntity.getId());

        assertThat(optionalLeadEntity).isPresent();
    }

    @Test
    @DisplayName("Given Leads When Exist Then Return Leads")
    public void givenLeadsWhenExistThenReturnLeads() {
        var leadEntity = defaultLeadEntity().build();

        this.repository.save(leadEntity);

        var leads = this.repository.findAll();

        assertThat(leads).isNotEmpty();
    }

    @Test
    @DisplayName("Given Datetime When Exists Then Return Leads")
    public void givenDatetimeWhenExistsThenReturnLeads() {
        var dateTime = LocalDateTime.now();

        var leadEntity = defaultLeadEntity().withCreatedAt(dateTime.minusMinutes(30))
                                            .withFinished(false)
                                            .build();

        this.repository.save(leadEntity);

        var leads = this.repository.findAllPendingReceivedLessThan(dateTime);

        assertThat(leads).isNotEmpty();
    }

}