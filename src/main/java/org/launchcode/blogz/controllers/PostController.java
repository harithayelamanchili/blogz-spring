package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {

		// TODO - implement newPost

		//get request parameters
		String newTitle = request.getParameter("title");
		String newBody = request.getParameter("body");
		User author = getUserFromSession(request.getSession());

		// validate parameters
		// if not valid, send back to form with error message
		//if valid,create new Post
		
		if (newTitle.equals("") || newTitle.equals(null)){
			String error = "Hey turd, you need a title";
			model.addAttribute("error", error);
			return "newpost";
		}

		
		if (newBody.equals("") || newBody.equals(null)){
			String error = "Hey turd, you need a body";
			model.addAttribute("error", error);
			return "newpost";
		}
		
			Post newPost = new Post(newTitle, newBody, author);
			postDao.save(newPost);

			String username = author.getUsername();
			int uid = newPost.getUid();
			return singlePost(username, uid, model);
			


		//return "redirect:index"; // TODO - this redirect should go to the new post's page  		
	}

	// handle requests like /blog/chris/5

	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {

		// TODO - implement singlePost

		//get given post
		Post newPost = postDao.findByUid(uid);

		// pass post into the template
		model.addAttribute("post", newPost);

		return "post";
	}

	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {

		// TODO - implement userPosts

		// get all user posts
		User user = userDao.findByUsername(username);
		List<Post> userPosts = user.getPosts();

		// pass the posts into the template
		model.addAttribute("posts", userPosts);

		return "blog";
	}

}