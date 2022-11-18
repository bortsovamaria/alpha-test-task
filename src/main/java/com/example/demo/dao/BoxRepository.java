package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends CrudRepository<Box, Long> {

//    @Query(value = "with recursive subordinates as ("
//            + " select e1.id as id, e1.parent_id as parent from employee e1 where e1.parent_id = :parentId"
//            + " union"
//            + " select e2.id, e2.parent_id from employee e2"
//            + " inner join subordinates s on (s.id = e2.parent_id)"
//            + " ) select * from subordinates", nativeQuery = true)

    @Query(value = "with recursive cte as (" +
            "select :id as id " +
            "union all\n" +
            "select b.id " +
            "from cte c " +
            "inner join Box b on c.id = b.contained_in" +
            ") " +
            "select id from cte",
            nativeQuery = true)
    List<Integer> getBoxes(@Param("id") Integer id);
}
