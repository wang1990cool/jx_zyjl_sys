package io.admin.modules.train.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainScoreEntity;
import io.admin.modules.train.service.TrainScoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-03-10 19:57:02
 */
@RestController
@RequestMapping("train/scoreSearch")
public class TrainScoreSearchController {
    @Autowired
    private TrainScoreService trainScoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:scoreSearch:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = trainScoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("train:scoreSearch:info")
    public R info(@PathVariable("id") Long id) {
        TrainScoreEntity trainScore = trainScoreService.selectById(id);

        return R.ok().put("trainScore", trainScore);
    }


}
