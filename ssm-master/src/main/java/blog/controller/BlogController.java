package blog.controller;

import blog.dao.Blog;
import blog.dto.output.BlogDetails;
import blog.pojo.blog;
import blog.service.IBlogService;
import blog.service.IUserService;
import core.ajaxResult.AjaxResult;
import core.controller.BaseController;
import core.exception.MyException;
import core.utils.JsonUitl;
import core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;

    @ResponseBody
	@RequestMapping(value="/writeBlog",method=RequestMethod.POST)
	public AjaxResult createBlog(@RequestBody Blog blog,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		if(StringUtil.isEmpty(blog.getTitle())||StringUtil.isEmpty(blog.getDes())||StringUtil.isEmpty(blog.getContext())){
			throw new MyException("信息不全");
		}
		blogService.createBlog(blog, userId);
		return AjaxResult.getOK();
	}

    @ResponseBody
	@RequestMapping(value="/updateBlog",method=RequestMethod.POST)
	public AjaxResult updateBlog(@RequestBody Blog blog,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		if(StringUtil.isEmpty(blog.getTitle())||StringUtil.isEmpty(blog.getDes())||StringUtil.isEmpty(blog.getContext())||blog.getUserid()==null){
			throw new MyException("信息不全");
		}
		blogService.updateBlog(blog, userId);
		return AjaxResult.getOK();
	}
	/**
	 * 删除博客
	 * @param blogId
	 * @param request
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/deleteBlog",method=RequestMethod.POST)
	public AjaxResult deleteBlog(Integer blogId,HttpServletRequest request){
		Integer userId=getNotNullUserId(request);
		
		blogService.deleteBlog(blogId, userId);
		return AjaxResult.getOK();
	}

    @ResponseBody
	@RequestMapping(value="/getBlogList",method=RequestMethod.POST)
	public AjaxResult getBlogList(@RequestParam(value="userId",required=false)Integer userId,HttpServletRequest request){
		Integer id=getNotNullUserId(request);
		List<BlogDetails> blogDetailsList=new ArrayList<BlogDetails>();
		if(userService.getUserRoleList(id).contains(1)){
			if(userId==null){
				blogDetailsList=blogService.getUserBlogList(id);
			}else {
				blogDetailsList=blogService.getUserBlogList(userId);
			}
		}else {
			blogDetailsList=blogService.getUserBlogList(id);
		};
		
		return AjaxResult.getOK(blogDetailsList);
	}

    @ResponseBody
	@RequestMapping(value="/getBlogDetails",method=RequestMethod.POST)
	public AjaxResult getBlogDetails(Integer blogId){
		Blog blog= blogService.selectByPrimaryKey(blogId);
		return AjaxResult.getOK(blog);
	}

	@RequestMapping(value = "/edit")
    public String editView(String title,ModelMap modelMap){
        blog blog = JsonUitl.fetch(title);
        modelMap.addAttribute("ablog",blog);
        return "edit";
    }

    @RequestMapping(value = "/doEdit")
    public String doEdit(blog blog,ModelMap modelMap){
        JsonUitl.edit(blog);
        return display(modelMap);
    }


	@RequestMapping(value = "/write",method = RequestMethod.GET)
	public String write(){

		return "write";
	}

	@RequestMapping(value = "/dowrite",method = RequestMethod.POST)
	public String dowrite(blog blog,ModelMap modelMap){

        JsonUitl.write(blog);

        return "redirect:/blog/display";
//		return  display(modelMap);
	}

	@RequestMapping(value = "/display")
    public String display(ModelMap model){
		List<blog>  blogs= JsonUitl.read();
        model.addAttribute("blogs",blogs);
        return  "show";
    }

}

