package com.enjoytrip.model;

import com.enjoytrip.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments_board")
public class CommentBoard extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comments_id")
    int commentId;

    String content;
    @Column(name = "board_id")
    int boardId;
    @Column(name = "member_id")
    int memberId;

    int rgroup;
    @Column(name = "reply_depth")
    boolean replyDepth;

    public void updateContent(String content){
        this.content = content;
    }
    public void setRgroup(){
        this.rgroup = this.commentId;
    }

}
