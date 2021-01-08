package com.noteoline.v2.repositroy;

import com.noteoline.v2.entity.Infotable;
import com.noteoline.v2.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureTepository extends JpaRepository <Picture,Integer>{
}
