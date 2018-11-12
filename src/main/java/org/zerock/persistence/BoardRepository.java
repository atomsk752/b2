package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long> {

/*	public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);*/
	
	@Query("select b from BoardVO b where b.bno > 0")
	public Page<BoardVO> getList(Pageable pageable);
	
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	public List<BoardVO> findByTitleLike(String keyword);
	
	public List<BoardVO> findByTitleContainingOrderByBnoDesc(String keyword);
	
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword, Pageable pageable);
}//여기 컬럼이름은 엔티티클래스를 참조
