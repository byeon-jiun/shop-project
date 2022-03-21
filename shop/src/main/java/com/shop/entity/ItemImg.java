package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter @Setter
public class ItemImg extends BaseEntity {

    @Id @Column @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    private String imgName;

    private String oriImgname;

    private  String imgUrl;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "item_id") private  Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgname = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
