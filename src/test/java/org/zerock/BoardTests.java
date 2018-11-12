package org.zerock;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.persistence.BoardRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardRepository boardRepository;
	
	@Test
	public void testQ1() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		boardRepository.getList(pageable);
		
		Page<BoardVO> result = boardRepository.getList(pageable);
		
		log.info(""+result);
		
		log.info("TOTAL pages"+result.getTotalPages());
		log.info("" + result.getNumber());
		log.info("Next"+result.hasNext());
		log.info("PREV"+result.hasPrevious());
		
		log.info("p NEXT: "+result.nextPageable());
		log.info("P PREV: "+result.previousPageable());
		
		result.getContent().forEach(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testFind4() {
		
		Pageable pageable = PageRequest.of(0, 5,Sort.Direction.DESC,"bno");
		
		boardRepository.findByTitleContainingAndBnoGreaterThan("title2", pageable).forEach(vo->log.info(""+vo));
	}
	
	@Test
	public void testTitle() {
		
		boardRepository.findByTitleContainingOrderByBnoDesc("2").forEach(vo->log.info(""+vo));
	}
	
	@Test
	public void testFind2() {
		
		boardRepository.findByTitleLike("title2").forEach(vo->log.info(""+vo));
	}
	
	@Test
	public void testFind1() {
		
		Pageable pageable = PageRequest.of(0, 10,Sort.Direction.DESC,"bno");
		
		Page<BoardVO> result = boardRepository.findByBnoGreaterThan(0L, pageable);
		
		log.info(""+result);
		
		log.info("TOTAL pages"+result.getTotalPages());
		log.info("" + result.getNumber());
		log.info("Next"+result.hasNext());
		log.info("PREV"+result.hasPrevious());
		
		log.info("p NEXT: "+result.nextPageable());
		log.info("P PREV: "+result.previousPageable());
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testUpdate() {
		
/*		boardRepository.findById(10L).ifPresent(vo ->{
			vo.setContent("updated");
			
			boardRepository.save(vo);
			
		});*/
		
		BoardVO vo = new BoardVO();
		vo.setBno(10L);
		vo.setTitle("update title");
		vo.setContent("update content");
		vo.setWriter("user");
		
		boardRepository.save(vo); //설정이 안되어 있어서 regdate는 null값이 들어감
		
	}
	
	@Test
	public void testRead() {
		
/*		Optional<BoardVO> result = boardRepository.findById(10L);
		
		result.ifPresent(vo -> log.info(""+vo));*/
		
		boardRepository.findById(10L).ifPresent(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testInserts() {
		
		IntStream.range(100, 300).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("lestitle" + i);
			vo.setContent("contentatomsk"+i);
			vo.setWriter("userles"+(i % 10));
			boardRepository.save(vo);
			
			
		});
	}

}
