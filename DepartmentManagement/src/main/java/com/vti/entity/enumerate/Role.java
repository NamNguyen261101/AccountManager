package com.vti.entity.enumerate;

public enum Role {
	ADMIN("Admin"), USER("User"), MANAGER("Manager");

	private String value;

	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Role of(String value) {
		if (value == null) {
			return null;
		}

		for (Role name : Role.values()) {
			if (name.getValue().equals(value)) {
				return name;
			}
		}

		return null;
	}

}
