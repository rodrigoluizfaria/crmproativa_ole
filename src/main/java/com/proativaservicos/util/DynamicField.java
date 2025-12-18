package com.proativaservicos.util;

import jakarta.faces.model.SelectItem;

import java.io.Serializable;
import java.util.List;


public class DynamicField implements Serializable {

	private static final long serialVersionUID = 1L;
	private String label;
	private Object value;
	private boolean required;

	private List<SelectItem> selectItems;

	public DynamicField() {
	}

	public DynamicField(String label, Object value, boolean required, List<SelectItem> selectItems) {
		this.label = label;
		this.value = value;
		this.required = required;
		this.selectItems = selectItems;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}
}
