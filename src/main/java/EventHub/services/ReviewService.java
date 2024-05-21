package EventHub.services;

import EventHub.dtos.ReviewDto;
import EventHub.mappers.ReviewMapper;
import EventHub.models.Review;
import EventHub.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends GenericService<Review, ReviewDto, ReviewRepository, ReviewMapper> {
    @Autowired
    private ReviewRepository repo;
    @Autowired
    private ReviewMapper mapper;

    @Override
    public ReviewDto insert(ReviewDto reviewDto) {
        var user = reviewDto.getUserFrom();
        var event = reviewDto.getEvent();
        if (event != null && user != null) {
            int idEvent = event.getId();
            //sprawdzic czy juz sie znajduje opinia od tego uzytkowika dla tego eventu
            //jesli sie nie znajduje to mozna wrzucic

        }
        else if (event != null && user == null) {
            //ma byc error ze nie ma uzytkownika takiego
        }

        var entity = mapper.toModel(reviewDto);
        var savedEntity = repo.save(entity);
        return mapper.toDto(savedEntity);
    }
}
