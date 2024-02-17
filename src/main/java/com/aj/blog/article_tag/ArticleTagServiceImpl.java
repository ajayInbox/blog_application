package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.article_tag.ArticleTagRepository;
import com.aj.blog.article_tag.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    private final ArticleTagRepository tagRepository;

    public ArticleTagServiceImpl(ArticleTagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<ArticleTag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public ArticleTag createTag(String tagLabel) {
        ArticleTag tag = null;
        Optional<ArticleTag> optional = tagRepository.findByTagLabel(tagLabel);
        if(optional.isPresent()){
            tag = optional.get();
        }
        tag = ArticleTag.builder()
                .tagLabel(tagLabel)
                .build();
        return tag;
    }

    @Override
    public String deleteTag(String tagLabel) throws Exception {
        Optional<ArticleTag> optional = tagRepository.findByTagLabel(tagLabel);
        if(optional.isEmpty()){
            throw new Exception("Tag not Exist");
        }
        tagRepository.deleteById(optional.get().getTagId());
        return "Tag deleted successfully";
    }
}
