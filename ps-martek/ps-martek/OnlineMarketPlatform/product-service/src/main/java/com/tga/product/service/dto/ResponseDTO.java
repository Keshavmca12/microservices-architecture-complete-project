package com.tga.product.service.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;

@Getter
public class ResponseDTO {

	/*
	 * private String responseCode; private String responseMsg; private Long dataId;
	 * private Object data; private Type type;
	 */
	public static enum Type {
		INFO, WARNING, ERROR;
	}

	@Data
	public static class MessageValue {
		private String msgText;
		private String msgCode;
		private Map<String, Object> documentIds;
		private Object objData;

		public MessageValue(String msgText, String msgCode) {
			super();
			this.msgText = msgText;
			this.msgCode = msgCode;
		}

		public MessageValue(String msgText, String msgCode, Object objData) {
			super();
			this.msgText = msgText;
			this.msgCode = msgCode;
			this.objData = objData;
		}

		public MessageValue(String msgText, String msgCode, Map<String, Object> documentIds) {
			super();
			this.msgText = msgText;
			this.msgCode = msgCode;
			this.documentIds = documentIds;
		}

	}

	@Getter
	public static class Message {
		private List<MessageValue> info;
		private List<MessageValue> warning;
		private List<MessageValue> error;

		public void addInfo(String msg, String code) {
			if (info == null)
				info = new ArrayList<MessageValue>();

			info.add(new MessageValue(msg, code));
		}

		public void addInfo(String msg, String code, Object data) {
			if (info == null)
				info = new ArrayList<MessageValue>();

			info.add(new MessageValue(msg, code, data));
		}

		public void addInfo(String msg, String code, Map<String, Object> data) {
			if (info == null)
				info = new ArrayList<MessageValue>();

			info.add(new MessageValue(msg, code, data));
		}

		public void addInfo(MessageValue msg) {
			if (info == null)
				info = new ArrayList<MessageValue>();

			info.add(msg);
		}

		public void addWarning(String msg, String code) {
			if (warning == null)
				warning = new ArrayList<MessageValue>();

			warning.add(new MessageValue(msg, code));
		}

		public void addWarning(MessageValue msg) {
			if (warning == null)
				warning = new ArrayList<MessageValue>();

			warning.add(msg);
		}

		public void addError(String msg, String code) {
			if (error == null)
				error = new ArrayList<MessageValue>();

			error.add(new MessageValue(msg, code));
		}

		public void addError(MessageValue msg) {
			if (error == null)
				error = new ArrayList<MessageValue>();

			error.add(msg);
		}

		public void addError(String msg, String code, Map<String, Object> documentIds) {
			if (error == null)
				error = new ArrayList<MessageValue>();

			error.add(new MessageValue(msg, code, documentIds));
		}

		public void addError(String msg, String code, Object objData) {
			if (error == null)
				error = new ArrayList<MessageValue>();

			error.add(new MessageValue(msg, code, objData));
		}
	}

	private Message global;

	public ResponseDTO addMessage(String msg, Type type) {
		if (global == null)
			global = new Message();

		switch (type) {
		case INFO:
			global.addInfo(msg, null);
			break;
		case WARNING:
			global.addWarning(msg, null);
			break;
		case ERROR:
			global.addError(msg, null);
			break;
		default:
			break;
		}

		return this;
	}

	public ResponseDTO addMessage(String msg, String code, Type type) {
		if (global == null)
			global = new Message();

		switch (type) {
		case INFO:
			global.addInfo(msg, code);
			break;
		case WARNING:
			global.addWarning(msg, code);
			break;
		case ERROR:
			global.addError(msg, code);
			break;
		default:
			break;
		}

		return this;
	}

	public ResponseDTO addMessage(String msg, String code, Map<String, Object> documentIds, Type type) {
		if (global == null)
			global = new Message();

		switch (type) {
		case INFO:
			global.addInfo(msg, code, documentIds);
			break;
		case WARNING:
			global.addWarning(msg, code);
			break;
		case ERROR:
			global.addError(msg, code, documentIds);
			break;
		default:
			break;
		}

		return this;
	}

	public ResponseDTO addMessage(String msg, HttpStatus status, Map<String, Object> documentIds, Type type) {
		if (global == null)
			global = new Message();

		String code = String.valueOf(status.value());
		switch (type) {
		case INFO:
			global.addInfo(msg, code, documentIds);
			break;
		case WARNING:
			global.addWarning(msg, code);
			break;
		case ERROR:
			global.addError(msg, code, documentIds);
			break;
		default:
			break;
		}

		return this;
	}

	public ResponseDTO addMessage(String msg, HttpStatus status, Object objData, Type type) {
		if (global == null)
			global = new Message();

		String code = String.valueOf(status.value());
		switch (type) {
		case INFO:
			global.addInfo(msg, code, objData);
			break;
		case WARNING:
			global.addWarning(msg, code);
			break;
		case ERROR:
			global.addError(msg, code, objData);
			break;
		default:
			break;
		}

		return this;
	}

	public ResponseDTO addMessage(MessageValue msg, Type type) {
		if (global == null)
			global = new Message();

		switch (type) {
		case INFO:
			global.addInfo(msg);
			break;
		case WARNING:
			global.addWarning(msg);
			break;
		case ERROR:
			global.addError(msg);
			break;
		default:
			break;
		}

		return this;
	}

}

