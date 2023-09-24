package br.pucminas.leads.adapters.web.in.leads;

import br.pucminas.leads.adapters.web.in.leads.dto.LeadDto;
import br.pucminas.leads.adapters.web.in.leads.mapper.LeadControllerMapper;
import br.pucminas.leads.application.ports.in.SearchLeadUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.pucminas.leads.adapters.web.config.ControllerRoutes.LEAD_ROUTE;
import static net.logstash.logback.marker.Markers.append;

@RestController
@RequestMapping(LEAD_ROUTE)
@RequiredArgsConstructor
@Slf4j
public class LeadController {

    private final SearchLeadUseCase useCase;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LeadDto> findById(@PathVariable("id") UUID id) {
        log.info("Searching lead by id: {}", id);
        var lead = this.useCase.findById(id);
        log.info(append("lead", lead), "Lead was found successfully");

        log.info(append("lead", lead), "Mapping lead");
        var leadDto = LeadControllerMapper.mapToDto(lead);
        log.info(append("lead", leadDto), "Lead was mapped successfully");
        return new ResponseEntity<>(leadDto, HttpStatus.OK);
    }

}
