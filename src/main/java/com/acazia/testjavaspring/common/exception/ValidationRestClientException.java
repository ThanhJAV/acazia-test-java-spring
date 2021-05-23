package com.acazia.testjavaspring.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ValidationRestClientException extends Exception {

	private static final long serialVersionUID = 1254672957987028725L;
	private transient Optional<HttpStatus> status;
	private transient Optional<Object> body;

	public ValidationRestClientException(Throwable arg1) {
		super(arg1);
	}

	/**
	 *
	 * @param status
	 */
	public ValidationRestClientException(HttpStatus status, Object body) {
		super();
		this.status = Optional.ofNullable(status);
		this.body = Optional.ofNullable(body);
	}

	/**
	 *
	 * @return HttpStatus
	 */
	public Optional<HttpStatus> getStatus() {
		return status;
	}

	public Optional<Object> getBody() {
		return body;
	}
}
