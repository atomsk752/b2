package org.zerock.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;



@Entity
@Table(name="tbl_board")
@Data
public class BoardVO {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_board")//시퀀스 이름 줘야됨
	@SequenceGenerator(name="seq_board", sequenceName="SEQ_BOARD") //시퀀스 이름 주는데 이름은 둘이 같아도 상관없음
	private Long bno;
	
	@Column(length=300)
	private String title;
	
	@Lob
	private String content;
	private String writer;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
}
