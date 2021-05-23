package com.acazia.testjavaspring.common.exception.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ErrorMessage {
    @JsonProperty("errorCode")
    private String errorCode = null;

    @JsonProperty("detail")
    private String detail = null;

    public ErrorMessage errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Get errorCode
     *
     * @return errorCode
     **/

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorMessage detail(String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * Get detail
     *
     * @return detail
     **/

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorMessage errorMessage = (ErrorMessage) o;
        return Objects.equals(this.errorCode, errorMessage.errorCode) &&
                Objects.equals(this.detail, errorMessage.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, detail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorMessage {\n");

        sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
        sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
