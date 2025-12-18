package com.proativaservicos.model.integracao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("contact")
public class Contact {
	
	public Contact() {
		
	}

	public Contact(String status, String codQueue, Long id, Boolean willRetry, List<Number> listNumber) {
		
		this.status = status;
		this.codQueue = codQueue;
		this.id = id;
		this.willRetry = willRetry;
		this.listNumber = listNumber;
	}

	@XStreamAlias("status")
	private String status;
	
	@XStreamAlias("queue")
	private String codQueue;
	
	@XStreamAlias("id")
	private Long id;
	
	@XStreamAlias("will_retry")
	private Boolean willRetry;
	
	@XStreamImplicit(itemFieldName = "number")
	private List<Number> listNumber = new ArrayList<>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodQueue() {
		return codQueue;
	}

	public void setCodQueue(String codQueue) {
		this.codQueue = codQueue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getWillRetry() {
		return willRetry;
	}

	public void setWillRetry(Boolean willRetry) {
		this.willRetry = willRetry;
	}

	public List<Number> getListNumber() {
		return listNumber;
	}
	
	public void setListNumber(List<Number> listNumber) {
		this.listNumber = listNumber;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"status='" + status + '\'' +
				", codQueue='" + codQueue + '\'' +
				", id=" + id +
				", willRetry=" + willRetry +

				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Contact contact = (Contact) o;
		return Objects.equals(id, contact.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
