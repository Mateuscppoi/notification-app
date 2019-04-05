package br.com.notificationapp.kafka.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaMessage implements Serializable {

    @JsonProperty("uuid")
    private String uuid = UUID.randomUUID().toString();
    @JsonProperty("message")
    private String msg;

    public KafkaMessage() {
    }

    public KafkaMessage(final String msg) {
        this.msg = msg;
    }

    private String getMsg() {
        return msg;
    }

    private String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("uuid", uuid)
            .add("msg", msg)
            .toString();
    }
}
