package com.xvarria.createcrud.external;

import java.io.Serializable;
import java.util.Date;

public class Cycle implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String type;
	private String descr;
	private String status;
	private Date defFromDate;
	private Date defToDate;
	private Date defAsofDate;

	public Cycle() {
		super();
	}
	
	public Cycle(FilterStandardOption filterStandardOption) {
		super();
		this.id = filterStandardOption.getNumericId();
		this.name = filterStandardOption.getLabel();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDefFromDate() {
		return defFromDate;
	}

	public void setDefFromDate(Date defFromDate) {
		this.defFromDate = defFromDate;
	}

	public Date getDefToDate() {
		return defToDate;
	}

	public void setDefToDate(Date defToDate) {
		this.defToDate = defToDate;
	}

	public Date getDefAsofDate() {
		return defAsofDate;
	}

	public void setDefAsofDate(Date defAsofDate) {
		this.defAsofDate = defAsofDate;
	}
	
	/*
	public boolean isOpen(){
		return CycleStatus.Open.toString().equals(this.status);
	}
	
	public final String getFromDateStr() {
		return this.defFromDate != null ? Utils.dateToStr(this.defFromDate) : "";
	}

	public final String getToDateStr() {
		return this.defToDate != null ? Utils.dateToStr(this.defToDate) : "";
	}
	
	public final String getAsofDateDateStr() {
		return this.defAsofDate != null ? Utils.dateToStr(this.defAsofDate) : "";
	}
	*/	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defAsofDate == null) ? 0 : defAsofDate.hashCode());
		result = prime * result + ((defFromDate == null) ? 0 : defFromDate.hashCode());
		result = prime * result + ((defToDate == null) ? 0 : defToDate.hashCode());
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Cycle other = (Cycle) obj;
		if (defAsofDate == null) {
			if (other.defAsofDate != null)
				return false;
		} else if (!defAsofDate.equals(other.defAsofDate))
			return false;
		if (defFromDate == null) {
			if (other.defFromDate != null)
				return false;
		} else if (!defFromDate.equals(other.defFromDate))
			return false;
		if (defToDate == null) {
			if (other.defToDate != null)
				return false;
		} else if (!defToDate.equals(other.defToDate))
			return false;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cycle [\n id=");
		builder.append(id);
		builder.append(", \n name=");
		builder.append(name);
		builder.append(", \n type=");
		builder.append(type);
		builder.append(", \n descr=");
		builder.append(descr);
		builder.append(", \n status=");
		builder.append(status);
		builder.append(", \n defFromDate=");
		builder.append(defFromDate);
		builder.append(", \n defToDate=");
		builder.append(defToDate);
		builder.append(", \n defAsofDate=");
		builder.append(defAsofDate);
		builder.append("]");
		return builder.toString();
	}

}
