package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






//接收来自前端用户提交的卡牌

@CrossOrigin(origins = "http://localhost:3030")  
@RequestMapping("/api")
@RestController
public class cardController {
	
	@Autowired	public com.example.demo.service.duplicate dup ;
 
	@PostMapping("/cards")
	public ResponseEntity<String> receiveCards(@RequestBody List<String> cards){
	
	 // 给到service 的class 类去处理
		dup.delete(cards);
		 return ResponseEntity.ok("成功");
	}
}
