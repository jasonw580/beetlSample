package org.jason.test;

import org.beetl.sql.core.SQLManager;
import org.jason.beans.Party;
import org.jason.service.InsertSample;

import java.util.List;
import java.util.Random;

public class PartySample {
    private SQLManager sqlManager;

    public PartySample(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public Party generateParty(){
        Party party = new Party();
        Integer base = Math.abs(new Random().nextInt()%100);
        party.setId1(base);
        party.setId2(base+1);
        party.setName("bai");
        return party;
    }
    public Party generateParty(int id1,int id2){
        Party party = new Party();
        party.setId1(id1);
        party.setId2(id2);
        party.setName("bai");
        return party;
    }
    public void insertParty(){
        Party party = generateParty();
        sqlManager.insert(party.getClass(),party);
    }
    public void insertParty(Party party){
        sqlManager.insert(party.getClass(),party);
    }

    public List<Party> queryAll(){
        return sqlManager.all(Party.class);
    }

    public Party queryById(Integer id1,Integer id2){
        Party party = new Party();
        party.setId1(id1);
        party.setId2(id2);
        return sqlManager.unique(Party.class,party);
    }
}
