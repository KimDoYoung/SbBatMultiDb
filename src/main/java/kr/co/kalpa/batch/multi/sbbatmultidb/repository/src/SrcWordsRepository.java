package kr.co.kalpa.batch.multi.sbbatmultidb.repository.src;

import kr.co.kalpa.batch.multi.sbbatmultidb.model.SrcWords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SrcWordsRepository {
    List<SrcWords> selectAll(SrcWords words);
}
