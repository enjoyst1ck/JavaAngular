package EventHub.mappers;

import EventHub.dtos.ReviewDto;
import EventHub.models.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements IMapper<Review, ReviewDto> {
    @Override
    public Review toModel(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setComment(reviewDto.getComment());
        review.setDate(reviewDto.getDate());
        review.setUserFrom(reviewDto.getUserFrom());
        review.setEvent(reviewDto.getEvent());

        return review;
    }
    @Override
    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setComment(review.getComment());
        reviewDto.setDate(review.getDate());
        reviewDto.setUserFrom(review.getUserFrom());
        reviewDto.setEvent(review.getEvent());

        return reviewDto;
    }
}
