package com.acazia.testjavaspring.common.exception.pojo;

import java.util.Arrays;
import java.util.Objects;

public class AlertMessages {
    private final IAlertCode alertCode;

    private final Object[] args;

    private AlertMessages(IAlertCode alertCode) {
        this(alertCode, null);
    }

    private AlertMessages(IAlertCode alertCode, Object[] args) {
        this.alertCode = alertCode;
        this.args = args;
    }

    /**
     * @return the alertCode
     */
    public IAlertCode getAlertCode() {
        return alertCode;
    }

    /**
     * The args is to be used in the message formatter
     *
     * @return the args
     */
    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "AlertMessages [alertCode=" + alertCode + ", args=" + Arrays.toString(args) + "]";
    }

    /**
     * Create new AlertMessages code with AlertCode
     *
     * @param alertCode
     * @return instance of AlertMessages
     * @throws IllegalArgumentException if errorCode is <code>null</code>
     */
    public static AlertMessages alert(IAlertCode alertCode) {
        if (Objects.isNull(alertCode)) {
            throw new IllegalArgumentException("The AlertCode must not be null.");
        }

        return new AlertMessages(alertCode);
    }

    /**
     * Create new AlertMessages with AlertCode and Args
     *
     * @param alertCode
     * @param args
     * @return instance of Error
     * @throws IllegalArgumentException if alertCode is <code>null</code>
     */
    public static AlertMessages alert(IAlertCode alertCode, Object... args) {
        if (Objects.isNull(alertCode)) {
            throw new IllegalArgumentException("The ErrorCode must not be null.");
        }

        return new AlertMessages(alertCode, args);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alertCode == null) ? 0 : alertCode.hashCode());
        result = prime * result + Arrays.hashCode(args);
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlertMessages other = (AlertMessages) obj;
        if (alertCode == null) {
            if (other.alertCode != null)
                return false;
        } else if (!alertCode.equals(other.alertCode))
            return false;
        if (!Arrays.equals(args, other.args))
            return false;
        return true;
    }
}
