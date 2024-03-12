package com.softdev.system.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.softdev.system.demo.entity.Article;
import com.softdev.system.demo.repository.ArticleRepository;
import com.softdev.system.demo.util.ApiReturnUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ArticleRepository articleRepository ;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate ;

    private ElasticsearchOperations elasticsearchOperations ;

    private final RestHighLevelClient restHighLevelClient ;

    @Autowired
    public DemoController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * 新增文档
     * @return
     */
    @GetMapping("/save")
    public Object add(@RequestParam(defaultValue = "titleDefaultValue") String title ,
                      @RequestParam(defaultValue = "textDefaultValue") String text){
        //构建并保存Article
        Article article = new Article();
        article.setId(RandomUtil.randomInt(10000,99999));//随机id
        article.setTitle(title);
        article.setText(text);
        article.setCreatetime(new Date());
        article.setAuthor("MadLife");
        articleRepository.save(article);
        return ApiReturnUtil.success(article);
    }

    /**
     * 根据_id删除文档
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestParam() Integer id ){
        articleRepository.deleteById(id);
//        Article article =  articleRepository.findById(1).get();
        return ApiReturnUtil.success();
    }

//    /**
//     * 根据title删除文档
//     * @param id
//     * @return
//     */
//    @PostMapping("/deleteByTitle")
//    public Object deleteByArticle(@RequestParam() String title ){
//        Article article = new Article() ;
//        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title.keyword", title);
//        BoolQueryBuilder must = QueryBuilders.boolQuery().must(queryBuilder);
//        Page<Article> queryResult =  articleRepository.search(must,pageable);
//        articleRepository.delete(id);
//        return ApiReturnUtil.success();
//    }





}
