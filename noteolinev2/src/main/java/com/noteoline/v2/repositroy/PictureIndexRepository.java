package com.noteoline.v2.repositroy;

import com.noteoline.v2.entity.Picture;
import com.noteoline.v2.entity.PictureIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureIndexRepository extends JpaRepository <PictureIndex,Integer>{
}
