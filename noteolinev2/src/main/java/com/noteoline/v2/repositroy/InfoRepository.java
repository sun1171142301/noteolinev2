package com.noteoline.v2.repositroy;


import com.noteoline.v2.entity.Infotable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoRepository extends JpaRepository <Infotable,Integer> {



        @Query(value = "select * from infotable where title=?1",nativeQuery = true)
        public List<Infotable> findAllByTitle(String title);

        @Query(value = "select * from infotable where info=?1",nativeQuery = true)
        public List<Infotable> findAllByInfo(String info);

        @Query(value = "select * from infotable where info=?1 and cid=?2",nativeQuery = true)
        public List<Infotable> findAllByInfoType(String info ,int type);

        @Query(value = "select * from infotable where title=?1 and cid=?2",nativeQuery = true)
        public List<Infotable> findAllByTitleType(String title ,int type);

        @Query(value = "select * from infotable where cid=?1",nativeQuery = true)
        public List<Infotable> findAllByType(int type);

        @Query(value = "select * from infotable where cid=?1 and hidden=false ",nativeQuery = true)
        public List<Infotable> findAllByTypeVersionControl(int type);


}
