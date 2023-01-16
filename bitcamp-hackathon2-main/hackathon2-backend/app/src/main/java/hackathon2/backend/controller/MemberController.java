package hackathon2.backend.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.MemberDao;
import hackathon2.backend.vo.Member;

@RestController
public class MemberController {

  MemberDao memberDao;

  public MemberController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @PostMapping("/members")
  public Object addMember(Member member) {

    this.memberDao.insert(member);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/members")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", members);

    return contentMap;
  }

  @GetMapping("/members/{no}")
  public Object getMember(@PathVariable int no) {

    Member m = this.memberDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", m);
    }

    return contentMap;
  }

  @PutMapping("/members/{no}")
  public Object updateMember(Member member) {

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(member.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");
      return contentMap;
    }

    member.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(member);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{no}")
  public Object deleteStudent(@PathVariable int no) {

    Member m = this.memberDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다.");

    } else {
      this.memberDao.delete(m);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

}
