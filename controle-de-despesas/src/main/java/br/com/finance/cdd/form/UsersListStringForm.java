package br.com.finance.cdd.form;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class UsersListStringForm {

	@NotNull
	private List<String> usersId;

	
	public UsersListStringForm() {
	}

	public UsersListStringForm(@NotNull List<String> usersId) {
		this.usersId = usersId;
	}

	public List<String> getUsersId() {
		return usersId;
	}

	public void setUsersId(List<String> usersId) {
		this.usersId = usersId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usersId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersListStringForm other = (UsersListStringForm) obj;
		return Objects.equals(usersId, other.usersId);
	}
}
