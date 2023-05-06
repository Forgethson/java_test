package com.wjd.nowcoder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

// 配置索引名，类型，分片等
@Document(indexName = "discusspost", /*type = "_doc",*/ shards = 6, replicas = 3)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussPost {

    @Id // 表示主键
    private int id;

    @Field(type = FieldType.Integer)
    private int userId;

    // 配置解析器，将内容拆分关键词，并与之匹配，ik_max_word表示关联尽可能多的关键词；searchAnalyzer = ik_smart表示在搜索的时候使用的智能解析器
    // 其他需要被搜索的字段都这么写即可
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Integer)
    private int type;

    @Field(type = FieldType.Integer)
    private int status;

    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    private int commentCount;

    @Field(type = FieldType.Double)
    private double score;
}
