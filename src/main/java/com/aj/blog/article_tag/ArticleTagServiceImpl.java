package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.article_tag.ArticleTagRepository;
import com.aj.blog.article_tag.ArticleTagService;
import com.aj.blog.response.TagsDTO;
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
        tag = optional.orElseGet(() -> ArticleTag.builder()
                .tagLabel(tagLabel)
                .build());
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

    @Override
    public ArticleTag getTagByTagLabel(String tagLabel) throws Exception {
        Optional<ArticleTag> optional = tagRepository.findByTagLabel(tagLabel);
        if(optional.isEmpty()){
            throw new Exception("Tag not Exist");
        }
        return optional.get();
    }

    @Override
    public List<TagsDTO> getAllTagsV2() {
        return tagRepository.findAllV2();
    }

    @Override
    public ArticleTag getTagAssociatedArticlesByTagId(Long tagId) {
        ArticleTag tag = null;
        try{
            Optional<ArticleTag> ops = tagRepository.findById(tagId);
            if(ops.isEmpty()){
                throw new Exception("Tag not found");
            }
            tag = ops.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }
}
