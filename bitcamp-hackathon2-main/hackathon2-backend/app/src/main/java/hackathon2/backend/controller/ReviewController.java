package hackathon2.backend.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.ReviewDao;
import hackathon2.backend.vo.Review;

@RestController
public class ReviewController {

  ReviewDao reviewDao = new ReviewDao();

  public ReviewController(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @PostMapping("/reviews")
  public Object addReview(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password){

    Review r = new Review();
    r.setContent(content);
    r.setPassword(password);
    r.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.reviewDao.insert(r);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/reviews")
  public Object getReviews() {

    Review[] reviews = this.reviewDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", reviews);

    return contentMap;
  }

  @GetMapping("/reviews/{reviewNo}")
  public Object getReview(@PathVariable int reviewNo) {

    Review r = this.reviewDao.findByNo(reviewNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (r == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", r);
    }

    return contentMap;
  }

  @PutMapping("/reviews/{reviewNo}")
  public Object updateReview(
      @PathVariable int reviewNo,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String password){

    Map<String,Object> contentMap = new HashMap<>();

    Review old = this.reviewDao.findByNo(reviewNo);
    if (old == null || !old.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
      return contentMap;
    }

    Review r = new Review();
    r.setNo(reviewNo);
    r.setContent(content);
    r.setPassword(password);
    r.setCreatedDate(old.getCreatedDate());

    this.reviewDao.update(r);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/reviews/{reviewNo}")
  public Object deleteReview(
      @PathVariable int reviewNo,
      @RequestParam(required = false) String password) {

    Review r = this.reviewDao.findByNo(reviewNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (r == null || !r.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");

    } else {
      this.reviewDao.delete(r);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
