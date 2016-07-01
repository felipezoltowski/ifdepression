package br.edu.ifrs.canoas.lds.starter.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Deck {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String nickname;
	
	@NotEmpty
	private String clanname;
	
	@NotEmpty
	private Integer kingtowerlevel;
	
	@NotEmpty
	private Integer arena;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String deckname;

	@NotEmpty
	@Size(min = 1, max = 500)
	private String guide;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String statusName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getClanname() {
		return clanname;
	}

	public void setClanname(String clanname) {
		this.clanname = clanname;
	}

	public Integer getKingtowerlevel() {
		return kingtowerlevel;
	}

	public void setKingtowerlevel(Integer kingtowerlevel) {
		this.kingtowerlevel = kingtowerlevel;
	}

	public Integer getArena() {
		return arena;
	}

	public void setArena(Integer arena) {
		this.arena = arena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeckname() {
		return deckname;
	}

	public void setDeckname(String deckname) {
		this.deckname = deckname;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
