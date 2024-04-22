package EventHub.services;

import EventHub.models.Review;
import EventHub.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends GenericService<Review, ReviewRepository> {

    @Autowired
    private ReviewRepository repo;
    @Override
    public Review insert(Review review) {
        var user = review.getUserFrom();
        var event = review.getEvent();
        if (event != null && user != null) {
            int idEvent = event.getId();
            //sprawdzic czy juz sie znajduje opinia od tego uzytkowika dla tego eventu
            //jesli sie nie znajduje to mozna wrzucic

        }

        return repo.save(review);
    }
}
