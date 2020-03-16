package util;

import java.util.Date;

import until.enumeration.ClassifiedStatus;

public class Classified {

	private int classifiedId;
	private String title;
	private double price;
	private String description;
	private String category;
	private Date createdAt;
	private String createdBy;
	private ClassifiedStatus status = ClassifiedStatus.NA;
	
	//Getter And Setter
	public int getClassifiedId() {
		return classifiedId;
	}
	public void setClassifiedId(int classifiedId) {
		this.classifiedId = classifiedId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public ClassifiedStatus getStatus() {
		return status;
	}
	public void setStatus(ClassifiedStatus status) {
		this.status = status;
	}
	//toString function called
	@Override
	public String toString() {
		return "Classified [classifiedId=" + classifiedId + ", title=" + title + ", price=" + price + ", description="
				+ description + ", category=" + category + ", createdAt=" + createdAt + ", createdBy=" + createdBy
				+ ", status=" + status + "]";
	}
}


