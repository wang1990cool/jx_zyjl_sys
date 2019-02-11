package io.admin.modules.zsgl.controller;

import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.zsgl.entity.XsZsxxbEntity;
import io.admin.modules.zsgl.service.XsZsxxbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static io.admin.common.utils.ShiroUtils.getUserEntity;


/**
 * 学生证书信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-27 10:14:57
 */
@RestController
@RequestMapping("zsgl/wdzs")
public class WdzsController {
    @Autowired
    private XsZsxxbService xsZsxxbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("zsgl:wdzs:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("xsxh",getUserEntity().getUsername());
        PageUtils page = xsZsxxbService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     *
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("zsgl:wdzs:info")
    public R info(@PathVariable("id") Long id){
			XsZsxxbEntity xsZsxxb = xsZsxxbService.selectById(id);

        return R.ok().put("xsZsxxb", xsZsxxb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("zsgl:wdzs:save")
    public R save(@RequestBody XsZsxxbEntity xsZsxxb) throws IOException{
            xsZsxxb.setXsxm(getUserEntity().getUsercname());
            xsZsxxb.setXsxh(getUserEntity().getUsername());
            xsZsxxb.setZt("待班级审核");
            FileInputStream fis = new FileInputStream(xsZsxxb.getZppath());
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            xsZsxxb.setZszp(buffer);
            xsZsxxb.setCreateTime(new Date());
			xsZsxxbService.insert(xsZsxxb);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("zsgl:wdzs:update")
    public R update(@RequestBody XsZsxxbEntity xsZsxxb) throws IOException{
            xsZsxxb.setXsxm(getUserEntity().getUsercname());
            xsZsxxb.setXsxh(getUserEntity().getUsername());
            xsZsxxb.setZt("待班级审核");
            FileInputStream fis = new FileInputStream(xsZsxxb.getZppath());
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            xsZsxxb.setZszp(buffer);
            xsZsxxb.setCreateTime(new Date());
			xsZsxxbService.updateById(xsZsxxb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("zsgl:wdzs:delete")
    public R delete(@RequestBody Long[] ids){
			xsZsxxbService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传文件目录
     * @param file 上传对象
     * @throws Exception
     */
/*
    private String executeUpload(String uploadDir,MultipartFile file) throws Exception
    {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String filename = UUID.randomUUID() + suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        return filename;
    }
*/

    /**
     * 上传文件方法
     * @param file 前台上传的文件对象
     * @return
     */
/*
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @RequiresPermissions("zsgl:wdzs:upload")
    @ResponseBody
    public R upload(HttpServletResponse response, HttpServletRequest request, MultipartFile file)
    {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        String filename=null;
        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") +"upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists())
            {
                dir.mkdir();
            }
            //调用上传方法
            filename=executeUpload(uploadDir,file);
            filename=dir + "\\" + filename;
        }catch (Exception e)
        {
            //打印错误堆栈信息
            e.printStackTrace();
            return R.error("上传失败");
        }

        return R.ok().put("filename", filename);
    }
*/

}
