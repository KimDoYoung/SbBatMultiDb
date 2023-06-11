package kr.co.kalpa.batch.multi.sbbatmultidb.model;

import lombok.Data;

import java.util.Date;

@Data
public class SrcWords {
    private long id;
    private String han;
    private String eng;
    private String china;
    private String note;
    private Date updateDt;
}
