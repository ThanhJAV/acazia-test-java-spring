package com.acazia.testjavaspring.common.exception.pojo;

import com.acazia.testjavaspring.common.exception.constant.AlertType;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Alert {
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("type")
    private AlertType type;

    public Alert() {
        // Nothing to do there
    }

    public Alert(String code, String message, AlertType type) {
        super();
        this.code = code;
        this.message = message;
        this.type = type;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the type
     */
    public AlertType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AlertType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alert other = (Alert) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
