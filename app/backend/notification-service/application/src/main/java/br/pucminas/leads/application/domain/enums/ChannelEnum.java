package br.pucminas.leads.application.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChannelEnum {

    SMS("SMS"),
    E_MAIL("E-MAIL"),
    WHATSAPP("WHATSAPP");

    private final String description;

}
