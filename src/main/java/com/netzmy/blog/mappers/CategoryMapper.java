package com.netzmy.blog.mappers;

import com.netzmy.blog.domain.PostStatus;
import com.netzmy.blog.domain.dto.CategoryDto;
import com.netzmy.blog.domain.dto.CreateCategoryRequest;
import com.netzmy.blog.domain.entities.Category;
import com.netzmy.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    //calculates the category total count
    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        //count always sets to 0 if something went wrong
        if(null == posts) {
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
