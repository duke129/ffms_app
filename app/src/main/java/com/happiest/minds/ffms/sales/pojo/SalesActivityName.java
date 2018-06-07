package com.happiest.minds.ffms.sales.pojo;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class SalesActivityName implements ParentListItem {

	private String name;
	private int thumbnail;
	private List<ActivityChild> activityItemList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(int thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<ActivityChild> getActivityItemList() {
		return activityItemList;
	}

	public void setActivityItemList(List<ActivityChild> activityItemList) {
		this.activityItemList = activityItemList;
	}

	@Override
	public List<?> getChildItemList() {
		return activityItemList;
	}

	public void setChildItemList(List<ActivityChild> activityChild) {
		 this.activityItemList = activityItemList;

	}

	@Override
	public boolean isInitiallyExpanded() {
		return false;
	}

}
