package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Api(tags = "管理端订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单分页查询
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("订单分页查询")
    public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("订单分页查询：{}",ordersPageQueryDTO);
        PageResult pageResult = orderService.pageQuery(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @ApiOperation("订单详情")
    public Result<OrderVO> details(@PathVariable Long id){
        log.info("订单详情：{}",id);
        OrderVO orderVO = orderService.getById(id);
        return Result.success(orderVO);
    }

    /**
     * 取消订单
     *
     * @param ordersCancelDTO
     */
    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception{
        log.info("取消订单：{}",ordersCancelDTO);
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 拒单
     *
     * @return
     */
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 接单
     *
     * @return
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        orderService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 派送订单
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    /**
     * 各个状态的订单数量统计
     *
     * @return
     */
    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 完成订单
     *
     * @return
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result complete(@PathVariable("id") Long id) {
        orderService.complete(id);
        return Result.success();
    }
}
