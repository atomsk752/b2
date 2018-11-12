package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long> {

	@Query("select b from BoardVO b where bno > 0 order by b.bno desc")
	public Page<BoardVO> getList(Pageable pageable);
	
	@Query("select b from BoardVO b where b.title like %:title% bno > 0 order by b.bno desc")
	public Page<BoardVO> getListByTitle(@Param("title") String title, Pageable pageable);

}//여기 컬럼이름은 엔티티클래스를 참조
