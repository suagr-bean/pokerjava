package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pojo.Post;

@Repository//接口 
public interface jpaRepository  extends JpaRepository<Post, Long>{ //post 实体类 和主键类型
//增删改查的方法都不需要写
}
