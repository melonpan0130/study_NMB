package com.example.url_board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/url")
public class UrlController {
// 페이지당 게시물 개수
	private Integer DEFAULT_PAGE_COUNT = 3;
	@Autowired
	private UrlRepository repository;

// 게시글 작성 폼
	@GetMapping("/write")
	public String getWriteArticle() {
		return "write";
	}

	@PostMapping("")
	public String postArticle(URL url) {
		repository.save(url);
		return "redirect:/url";
	}


	@GetMapping("/{id}")
	public String getArticle(@PathVariable("id") Integer id, Model m) {
		Optional<URL> url = repository.findById(id);
		m.addAttribute("url", url.get().getUrl());
		
		int count = url.get().getCount()+1;
		url.get().setCount(count);
		repository.save(url.get());
		return "url";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") Integer id, Model m) {
		Optional<URL> url = repository.findById(id);
		
		return "rewrite";
	}
	
	@PostMapping("/rewrite/{id}")
	public String rewrite(@PathVariable("id") Integer id, URL url) {
		Optional<URL> original = repository.findById(id);
		original.get().setUrl(url.getUrl());
		
		repository.save(original.get());
		return "redirect:/url";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model m) {
		Optional<URL> url = repository.findById(id);
		
		repository.deleteById(id);
		return "redirect:/url";
	}

//게시글 리스팅
	@GetMapping(value = "")
	public String getArticleList(Model m, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "order", defaultValue = "asc") String order) {
//PageRequest의 of 메소드를 사용하여 PageRequest 객체 생성
//전달 인자 : (0부터 시작하는) 페이지 번호, 페이지당 게시글 개수, 정렬 방법(DESC, ASC), 정렬
		m.addAttribute("urls", repository.findAll(PageRequest.of(page - 1, DEFAULT_PAGE_COUNT,
				order.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "id")));
		m.addAttribute("count", (int) Math.ceil(repository.count() / DEFAULT_PAGE_COUNT.doubleValue()));
		m.addAttribute("order", order);
		return "list";
	}
}