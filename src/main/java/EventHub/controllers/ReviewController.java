package EventHub.controllers;

import EventHub.models.Review;
import EventHub.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController extends GenericController<Review, ReviewService> {
}