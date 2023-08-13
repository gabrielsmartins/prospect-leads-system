package br.pucminas.leads.application.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Notification {

    private String email;
    private String content;

}
