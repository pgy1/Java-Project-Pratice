package com.journaldev.spring.web;

import com.journaldev.spring.dao.JdbcService;
import jodd.datetime.JDateTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class PersonController {
	Logger logger = Logger.getLogger(PersonController.class.getName());

	@Autowired(required=true)
	@Qualifier(value="personService")
	private PersonService personService;

	@Resource(name = "jdbcService")
	JdbcService jdbcService;

	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public String login(Model model){

		Map map = new HashMap();
		map.put("token","2222");
		map.put("headerName","2222");
		model.addAttribute("_csrf",map);
		return "login";
	}
	@RequestMapping(value = "/auth/denied", method = RequestMethod.POST)
	public String denied(Model model){
		return "denied";
	}
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
//		List<Person> persons = this.personService.listPersons();
		List<Person> persons = jdbcService.queryForList("select * from person",null,null);

		logger.info("jdt:" + new JDateTime().toString("YYYY-MM-DD hh:mm"));

//		Jedis redis = RedisPool.getInstance().getJedis();
//		if(redis!=null && persons!=null && persons.size()>0){
//			for(int i = 0; i < persons.size(); i++) {
//				redis.lpush("man", "pgy" + i);
//			}
//		}

		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", persons);
		return "person";
	}

	@RequestMapping(value = "/catch", method = RequestMethod.GET)
	public String catchHtml(Model model) {
		String text = null;
		try {
			Document doc = Jsoup.connect("http://www.tudou.com").timeout(10000).get();
//			Document doc = Jsoup.connect("http://maven.outofmemory.cn/").timeout(6000).get();
//			Document doc = Jsoup.connect("http://www.journaldev.com/2335/how-to-read-csv-file-using-java-scanner-class").timeout(6000).get();
//			text = doc.getElementById("gTop").html();
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");

			for (Element src : media) {
				if (src.tagName().equals("img"))
					text = text + String.format(" <%s src='%s' width='%s' height='%s' alt='%s' />",
							src.tagName(), src.attr("src"), src.attr("width"), src.attr("height"),
							src.attr("alt").trim());

				else
					text = text + String.format(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		model.addAttribute("text", text);
		return "jsoup";
	}


	@RequestMapping(value= "/json", method = RequestMethod.GET)
	public String addPerson(Model model){

		Map map = new HashMap();
		map.put("author","pgy");
		map.put("time","now");

		Person p = new Person();
		p.setId(1);
		p.setName("pgy");
		p.setCountry("china");
		JSONObject mapObject = JSONObject.fromObject(map);
//		XMLSerializer xmlSerializer = new XMLSerializer();
//		String text = xmlSerializer.write(mapObject);

		List<Map> list = new ArrayList<Map>();
		list.add(map);
		JSONArray listObject = JSONArray.fromObject(list);

//		model.addAttribute("text", mapObject);
		model.addAttribute("text", listObject);
//		model.addAttribute("text", text);
		return "jsoup";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
	
}
