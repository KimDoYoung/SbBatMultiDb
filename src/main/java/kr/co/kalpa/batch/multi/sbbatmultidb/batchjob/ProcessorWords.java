package kr.co.kalpa.batch.multi.sbbatmultidb.batchjob;

import kr.co.kalpa.batch.multi.sbbatmultidb.model.DesWords;
import kr.co.kalpa.batch.multi.sbbatmultidb.model.SrcWords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
@Slf4j
public class ProcessorWords implements ItemProcessor<SrcWords, DesWords> {

    @Override
    public DesWords process(SrcWords srcWords) throws Exception {
        DesWords desWords = new DesWords();
        desWords.setHan( srcWords.getHan() );
        desWords.setEng( srcWords.getEng() );
        desWords.setChina( srcWords.getChina() );
        desWords.setNote( srcWords.getNote() );
        log.info("desWords: " + desWords.toString());
        return desWords;
    }
}
