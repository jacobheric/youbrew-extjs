package com.jacobheric.youbrew.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Consolidates some generic controller functionality, including spring security refernces and
 *   validation message building.
 * @author: jacob
 * @created: 2/22/2011
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   see <http://www.gnu.org/licenses/>.
 */
public class BaseController {

	@Autowired
    private Validator validator;

	@Autowired
	private MessageSource messages;

	private String validationMessage;


	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public MessageSource getMessages() {
		return messages;
	}

	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	/**
	 * A little generic validation routine.  This allows us to send back all or our server side
	 * validation error detail.  This should not result in validation errors very often as
	 * client-side validation should also be in effect
	 *
	 * @param entityList- the list of T entities to validate
	 * @return ServiceOperationResult<I> - the service result for entity identifier of type I
	 */
	public <T,I> ServiceResultWrapper<I> validateEntities(ServiceResultWrapper<I> result, List<T> entityList){

		for (T entity : entityList) {

			Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(entity);

			if (constraintViolations.size() > 0 ){
				 result.setSuccess(false);
				 result.setMessage("Validation failed.");
				//
				//Somewhat crude, just append them all for sending back.
				for (ConstraintViolation<T> failure : constraintViolations) {
					if (StringUtils.isBlank(failure.getPropertyPath().toString())){
						continue;
					}

					result.getErrors().put(failure.getPropertyPath().toString(), failure.getMessage());
				}
			}

		}

		return result;

	}

	public class ServiceResultWrapper<T>
	{
		private T items;

		/**
		 *  Success property of transaction. true/false to be interpreted by client
		 */
		private Boolean success;

		/**
		 *  Optional message property of transaction. To be consumed by client
		 */
		private String message;

		/**
		 * Total items in resultset (used for paging)
		 */
		private int total;


		private Map<String,String> errors = new HashMap<String, String>();


		public ServiceResultWrapper()
		{
		}

		public ServiceResultWrapper(T items, Boolean success, int total)
		{
			this.items = items;
			this.success = success;
			this.total = total;
		}

		public ServiceResultWrapper(T items, Boolean success, String message, int total)
		{
			this.items = items;
			this.success = success;
			this.message = message;
			this.total = total;
		}

		public ServiceResultWrapper(T items, Boolean success, String message, Map<String, String> errors, int total)
		{
			this.items = items;
			this.success = success;
			this.message = message;
			this.errors = errors;
			this.total = total;
		}

		public T getItems()
		{
			return items;
		}

		public void setItem(T item)
		{
			this.items = item;
		}

		public Boolean getSuccess()
		{
			return success;
		}

		public void setSuccess(Boolean success)
		{
			this.success = success;
		}

		public String getMessage()
		{
			return message;
		}

		public void setMessage(String message)
		{
			this.message = message;
		}

		public Map<String, String> getErrors()
		{
			return errors;
		}

		public void setErrors(Map<String, String> errors)
		{
			this.errors = errors;
		}


		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
		
	}
}

