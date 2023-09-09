package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class LeadRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public LeadEntity save(LeadEntity leadEntity) {
        this.dynamoDBMapper.save(leadEntity);
        return leadEntity;
    }

    public Optional<LeadEntity> findById(UUID id) {
        var leadEntity = this.dynamoDBMapper.load(LeadEntity.class, id);
        return Optional.ofNullable(leadEntity);
    }

    public List<LeadEntity> findAllPendingReceivedLessThan(LocalDateTime dateTime) {
        Map<String, AttributeValue> values = new HashMap<>();
        values.put(":CreatedAt", new AttributeValue().withS(dateTime.format(DateTimeFormatter.ISO_DATE_TIME)));
        values.put(":Sent", new AttributeValue().withBOOL(false));
        var expression = new DynamoDBScanExpression()
                                .withConsistentRead(false)
                                .withFilterExpression("CreatedAt <= :CreatedAt AND Sent = :Sent")
                                .withExpressionAttributeValues(values);
        return this.dynamoDBMapper.scan(LeadEntity.class, expression);
    }

}
