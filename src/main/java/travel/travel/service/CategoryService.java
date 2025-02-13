package travel.travel.service;

import travel.travel.model.dto.request.CategoryRequest;
import travel.travel.model.dto.response.CategoryPagination;
import travel.travel.model.dto.response.CategoryResponse;
import travel.travel.model.dto.response.CategoryResponseForGetAll;
import travel.travel.model.dto.response.SimpleResponse;

import java.util.List;

public interface CategoryService {

    SimpleResponse saveCategory(CategoryRequest categoryRequest);

    CategoryPagination getAllCategory(int currentPage,int pageSize);

    CategoryResponse getById(Long id);

    SimpleResponse updateCategory(Long id,CategoryRequest categoryRequest);

    SimpleResponse deleteCategory(Long id);

    List<CategoryResponseForGetAll> getAllCategories();
}
