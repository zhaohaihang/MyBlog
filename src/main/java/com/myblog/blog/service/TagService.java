package com.myblog.blog.service;

import com.myblog.blog.po.Tag;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Tag getTagByName(String name);
    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTag(String ids );
    List<Tag> listTagTop(Integer size);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
}
