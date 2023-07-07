package com.example.demo.repository;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.ThongKe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
 @Query(value = " select new com.example.demo.model.ThongKe( P.name, SUM(OD.quantity) ) \n" +
         "         FROM Sac P\n" +
         "         JOIN OrderDetail OD ON P.id = OD.product.id " +
         "WHERE OD.order.createdate =CURRENT_DATE() \n" +
         "         GROUP BY P.id, P.name \n" +
         "         ORDER BY SUM(OD.quantity) desc ")
    List<ThongKe> getListByNgay( Pageable pageable);
   @Query(value = "SELECT new com.example.demo.model.ThongKe(P.name, SUM(OD.quantity)) " +
           "FROM Sac P " +
           "JOIN OrderDetail OD ON P.id = OD.product.id " +
           "WHERE YEAR(OD.order.createdate) = YEAR(CURRENT_DATE()) " +
           "     AND MONTH(OD.order.createdate) = MONTH(CURRENT_DATE()) " +
           "GROUP BY P.name " +
           "ORDER BY SUM(OD.quantity) DESC")
   List<ThongKe> getListByThang(Pageable pageable);

    @Query(value = " select new com.example.demo.model.ThongKe( P.name, SUM(OD.quantity) ) \n" +
            "         FROM Sac P\n" +
            "         JOIN OrderDetail OD ON P.id = OD.product.id " +
            "WHERE YEAR(OD.order.createdate) = YEAR(CURRENT_DATE()) \n" +
            "         GROUP BY P.id, P.name \n" +
            "         ORDER BY SUM(OD.quantity) desc ")
    List<ThongKe> getListByNam( Pageable pageable);
    @Query(value = "SELECT new com.example.demo.model.ThongKe(P.name, SUM(OD.quantity)) " +
            "         FROM Sac P\n" +
            "         JOIN OrderDetail OD ON P.id = OD.product.id " +
            "WHERE YEAR(OD.order.createdate) = YEAR(CURRENT_DATE()) \n" +
            "GROUP BY P.name \n " +
            "ORDER BY SUM(OD.quantity) DESC")
    List<ThongKe> getListByTuan(Pageable pageable);



    @Query(value = " select new com.example.demo.model.ThongKe( P.name, SUM(OD.quantity) ) \n" +
            "         FROM Sac P\n" +
            "         JOIN OrderDetail OD ON P.id = OD.product.id " +
            " \n" +
            "         GROUP BY P.id, P.name \n" +
            "         ORDER BY SUM(OD.quantity) desc ")
    List<ThongKe> getList( Pageable pageable);
    @Query(value = "SELECT new com.example.demo.model.ThongKe(P.id,P.name, SUM(OD.quantity)) " +
            "FROM Sac P LEFT JOIN OrderDetail OD ON P.id = OD.product.id " +
            "WHERE P.id NOT IN (SELECT OD.product.id FROM OrderDetail OD) " +
            "GROUP BY P.id,P.name " +
            "ORDER BY SUM(OD.quantity) ASC")
    List<ThongKe> getListTon(Pageable pageable);


    @Query("select d from OrderDetail d where d.order.id=?1")
 List<OrderDetail> findAllByOrderId(Integer id);
}

