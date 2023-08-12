package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;
import java.util.UUID;

public class LeadRepositoryCustomImpl<T extends LeadEntity> extends SimpleMongoRepository<T, UUID> implements LeadRepositoryCustom<T> {

    public LeadRepositoryCustomImpl(MongoEntityInformation<T, UUID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public <S extends T> S save(S entity) {
        generateId(entity);
        return super.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> generateId(entity));
        return super.saveAll(entities);
    }

    @Override
    public <S extends T> S insert(S entity) {
        generateId(entity);
        return super.insert(entity);
    }

    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        entities.forEach(entity -> generateId(entity));
        return super.insert(entities);
    }

    protected <S extends T> void generateId(S entity) {
        if(entity != null && entity.getInsuranceQuoteId() == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
    }

}
