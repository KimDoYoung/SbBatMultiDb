package kr.co.kalpa.batch.multi.sbbatmultidb.repository.des;

import kr.co.kalpa.batch.multi.sbbatmultidb.model.DesWords;
import kr.co.kalpa.batch.multi.sbbatmultidb.model.SrcWords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DesWordsRepository {
    int  insert(DesWords words);
}
