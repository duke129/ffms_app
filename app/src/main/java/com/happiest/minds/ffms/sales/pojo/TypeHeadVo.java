package com.happiest.minds.ffms.sales.pojo;

public class TypeHeadVo {
	
	private Long id;
	private String Name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return Name;
	}
}
