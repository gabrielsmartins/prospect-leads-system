package br.pucminas.notifications.application.domain;

import br.pucminas.notifications.application.domain.enums.ChannelEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Notification {

    private UUID id;
    private InsuranceQuote insuranceQuote;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private LocalDateTime notifiedAt;

    @Builder.Default
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<ChannelEnum> channels = new ArrayList<>();

    public Integer addChannel(ChannelEnum channel) {
        this.channels.add(channel);
        return this.channels.size();
    }

    public List<ChannelEnum> getChannels() {
        return Collections.unmodifiableList(this.channels);
    }

}
