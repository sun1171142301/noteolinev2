package com.noteoline.v2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "infotable")
public class Infotable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int infoid;

    @Column(name = "title",nullable = false)
    String title;

    @Column(name = "info",nullable = false)
    /*Text info;*/
            String info;

    @Column(name = "cid",nullable = false)
    int cid;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "updatetime",nullable = false)
    Timestamp updatetime;//Timestamp序列化问题
//    updatetime
}
