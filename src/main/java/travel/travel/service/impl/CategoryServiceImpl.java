package travel.travel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel.exception.BadRequestExeption;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.CategoryRequest;
import travel.travel.model.dto.response.*;
import travel.travel.model.entity.Category;
import travel.travel.model.entity.Tour;
import travel.travel.model.entity.Travel;
import travel.travel.repository.CategoryRepository;
import travel.travel.repository.TravelRepository;
import travel.travel.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final TravelRepository travelRepository;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(TravelRepository travelRepository, CategoryRepository categoryRepository) {
        this.travelRepository = travelRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.day()<0){
            log.warn("Attempted to save category with negative day");
            throw  new BadRequestExeption("Category day should be greater than zero");
        }
        if (categoryRequest.dayTour().isEmpty()){
            log.warn("Attempted to save category with empty day of tour");
            throw new BadRequestExeption("Day of tour can not be empty");
        }
        Travel travel = travelRepository.findById(1L).orElseThrow(()->{
            log.error("Travel with id {} not found",1);
            return new NotFoundException(String.format("Travel with id %s not found",1));
        });
        Category category= new Category();
        category.setDay(categoryRequest.day());
        category.setImage(categoryRequest.image());
        category.setDayTour(categoryRequest.dayTour());
        category.setTravel(travel);
        categoryRepository.save(category);
        log.info("Category successfully saved");
        return SimpleResponse
                .builder()
                .message("Successfully created")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @Override
    public CategoryPagination getAllCategory(int currentPage, int pageSize) {
        log.info("Fetching all categories");
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<CategoryResponseForPagination> allCategory = categoryRepository.getAllCategory(pageable);
        return CategoryPagination
                .builder()
                .categories(allCategory.getContent())
                .currentPage(allCategory.getNumber()+1)
                .pageSize(allCategory.getTotalPages())
                .build();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category with id {} not found", id);
            return new NotFoundException(String.format("Category with id %s not found", id));
        });
        log.info("Fetching Category by id {}",id);
        List<Tour> tour = category.getTour();
        return CategoryResponse
                .builder()
                .id(category.getId())
                .dayTour(category.getDayTour())
                .image(category.getImage())
                .tours(tour.stream().map(tour1 -> TourResponseCategoryPage.builder()
                        .id(tour1.getId())
                        .tourName(tour1.getTourName())
                        .category(tour1.getCategory().getDayTour())
                        .aboutTour(tour1.getAboutTour())
                        .days(tour1.getDays())
                        .nights(tour1.getNights())
                        .price(tour1.getPrice())
                        .pax(tour1.getPax())
                        .dateFrom(tour1.getDateFrom())
                        .dateTo(tour1.getDateTo()).build()).toList()).build();

    }

    @Override
    public SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category with id: {} not found", id);
            return new NotFoundException(String.format("Category with id %s not found", id));
        });
        if (categoryRequest.day()<0){
            log.warn("Attempted to save category, with negative day");
            throw  new BadRequestExeption("Category day should be greater than zero");
        }
        if (categoryRequest.dayTour().isEmpty()){
            log.warn("Attempted to save category, with empty day of tour");
            throw new BadRequestExeption("Day of tour can not be empty");
        }
        category.setDay(categoryRequest.day());
        category.setImage(categoryRequest.image());
        category.setDayTour(categoryRequest.dayTour());
        categoryRepository.save(category);
        log.info("Category with id {} is successfully updated",id);
        return SimpleResponse
                .builder()
                .message("Successfully updated")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public SimpleResponse deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category with id: {} not found to delete", id);
            return new NotFoundException(String.format("Category with id %s not found", id));
        });
        List<Tour> tour = category.getTour();
        for(Tour tour1:tour){
            tour1.setCategory(null);
        }
        category.setTravel(null);
        categoryRepository.delete(category);
        log.info("Category with id {} is successfully deleted",id);
        return SimpleResponse
                .builder()
                .message("Successfully deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
