package br.com.finance.cdd.form;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class ListStringForm {

	@NotNull
	private List<String> listString;

	public ListStringForm() {
	}

	public ListStringForm(@NotNull List<String> listString) {
		this.listString = listString;
	}

	public List<String> getListString() {
		return listString;
	}

	public void setListString(List<String> listString) {
		this.listString = listString;
	}

	@Override
	public int hashCode() {
		return Objects.hash(listString);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListStringForm other = (ListStringForm) obj;
		return Objects.equals(listString, other.listString);
	}
}
