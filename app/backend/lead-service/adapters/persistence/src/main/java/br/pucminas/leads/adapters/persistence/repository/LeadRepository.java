package br.pucminas.leads.adapters.persistence.repository;

import br.pucminas.leads.adapters.persistence.entity.LeadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadRepository extends CrudRepository<LeadEntity, UUID> {

}
