package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface LeadRepositoryCustom<T extends LeadEntity> extends MongoRepository<T, UUID> {
}
