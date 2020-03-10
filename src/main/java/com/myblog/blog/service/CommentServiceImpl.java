package com.myblog.blog.service;

import com.myblog.blog.dao.CommentRepository;
import com.myblog.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");
        List<Comment> comments=commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if (parentCommentId!=-1){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    //循环顶级评论节点
    private List<Comment> eachComment (List<Comment> comments){
        List<Comment> commentsView =new ArrayList<Comment>();
        for (Comment comment : comments){
            Comment c= new Comment();
            BeanUtils.copyProperties(comment , c);
            commentsView.add(c);
        }
        combineChildren(commentsView);
        return commentsView;
    }

    private void combineChildren(List<Comment> comments){
        for (Comment comment : comments){
            List<Comment> reply1s=comment.getReplyComments();
            for (Comment reply1 : reply1s ){
                recursively(reply1);//找出子代
            }
            //修改顶级节点的reply 为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清楚临时存放区
            tempReplys=new ArrayList<>();
        }
    }

    private List<Comment> tempReplys=new ArrayList<>();//临时存放区
    private void recursively(Comment comment){
        tempReplys.add(comment);//顶节点添加到临时集合
        if(comment.getReplyComments().size()>0){
            List<Comment> replys=comment.getReplyComments();
            for (Comment reply : replys){
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }

}
