package io.admin.modules.train.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.utils.ShiroUtils;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.train.entity.TrainScoreEntity;
import io.admin.modules.train.service.TrainScoreService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author Wangcaner
 * @email wangcaner@outlook.com
 * @date 2019-03-10 19:57:02
 */
@RestController
@RequestMapping("train/myScore")
public class TrainMyScoreController {
    @Autowired
    private TrainScoreService trainScoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("train:myScore:myList")
    public R list(@RequestParam Map<String, Object> params) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        params.put("studentNum", sysUserEntity.getUserId());
        PageUtils page = trainScoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("train:score:info")
    public R info(@PathVariable("id") Long id) {
        TrainScoreEntity trainScore = trainScoreService.selectById(id);

        return R.ok().put("trainScore", trainScore);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("train:score:save")
    public R save(@RequestBody TrainScoreEntity trainScore) {
        trainScoreService.insert(trainScore);

        return R.ok();
    }

}
