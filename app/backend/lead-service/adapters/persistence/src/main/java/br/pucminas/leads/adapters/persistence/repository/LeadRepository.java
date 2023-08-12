package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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

}
