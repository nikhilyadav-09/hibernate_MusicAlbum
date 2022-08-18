package org.jspider.springcore.premitive.Zproject.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SongDeatails")
public class SongDTO {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "songId")
	private int id;
	
	@Column(name = "songName")
	private String name;
	
	@Column(name = "songDuration")
	private double length;
	
	@Column(name = "singerName")
	private String singer;
	
	
}
