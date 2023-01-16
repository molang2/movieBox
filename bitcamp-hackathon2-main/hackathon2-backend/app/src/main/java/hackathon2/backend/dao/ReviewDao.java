package hackathon2.backend.dao;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon2.backend.vo.Review;

@Repository
public class ReviewDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Review[] reviews = new Review[SIZE];

  public void insert(Review review) {
    review.setNo(++no);
    review.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.reviews[this.count++] = review;
  }

  public Review[] findAll() {
    return Arrays.copyOf(reviews, count);
  }

  public Review findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.reviews[i].getNo() == no) {
        return this.reviews[i];
      }
    }
    return null;
  }

  public void update(Review review) {
    this.reviews[this.indexOf(review)] = review;
  }

  public void delete(Review review) {
    for (int i = this.indexOf(review) + 1; i < this.count; i++) {
      this.reviews[i - 1] = this.reviews[i];
    }
    this.reviews[--this.count] = null;
  }

  private int indexOf(Review r) {
    for (int i = 0; i < this.count; i++) {
      if (this.reviews[i].getNo() == r.getNo()) {
        return i;
      }
    }
    return -1;
  }
}
